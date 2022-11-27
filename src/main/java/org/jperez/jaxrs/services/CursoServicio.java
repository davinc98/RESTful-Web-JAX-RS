package org.jperez.jaxrs.services;


import jakarta.ejb.Local;
import jakarta.jws.WebService;
import org.jperez.jaxrs.models.Curso;

import java.util.List;
import java.util.Optional;

@Local
@WebService
public interface CursoServicio {
    List<Curso> listar();
    Curso crear(Curso curso);
    Optional<Curso> porId(Long id);
    void eliminar(Long id);
}
