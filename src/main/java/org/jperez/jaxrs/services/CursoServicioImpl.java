package org.jperez.jaxrs.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.jperez.jaxrs.models.Curso;
import org.jperez.jaxrs.repositories.CursoRepository;

import java.util.List;
import java.util.Optional;

@Stateless
@WebService(endpointInterface = "org.jperez.jaxrs.services.CursoServicio")
public class CursoServicioImpl implements CursoServicio {

    @Inject
    private CursoRepository repository;

    @Override
    @WebMethod
    public List<Curso> listar() {
        return repository.listar();
    }

    @Override
    @WebMethod
    public Curso crear(Curso curso) {
        return repository.guardar(curso);
    }

    @Override
    public Optional<Curso> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
