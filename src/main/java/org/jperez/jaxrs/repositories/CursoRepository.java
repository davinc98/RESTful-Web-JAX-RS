package org.jperez.jaxrs.repositories;

import org.jperez.jaxrs.models.Curso;

import java.util.List;

public interface CursoRepository {

    List<Curso> listar();
    Curso guardar(Curso curso);
    Curso porId(Long id);
    void eliminar(Long id);

}
