package org.jperez.jaxrs.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jperez.jaxrs.models.Curso;

import java.util.List;

@RequestScoped
public class CursoRepositoryImpl implements CursoRepository{

    @Inject
    private EntityManager em;

    @Override
    public List<Curso> listar() {
        return em.createQuery("SELECT c FROM Curso c LEFT OUTER JOIN FETCH c.instructor", Curso.class).getResultList();
    }

    @Override
    public Curso guardar(Curso curso) {
        if(curso.getId()!=null && curso.getId()>0) {
            em.merge(curso);
        }else{
            em.persist(curso);
        }
        return curso;
    }

    @Override
    public Curso porId(Long id) {
        return em.createQuery("SELECT c FROM Curso c LEFT OUTER JOIN FETCH c.instructor WHERE c.id=:id", Curso.class)
                .setParameter("id", id)
                .getSingleResult();
//        return em.find(Curso.class, id);
    }

    @Override
    public void eliminar(Long id) {
        Curso c = porId(id);
        em.remove(c);
    }
}
