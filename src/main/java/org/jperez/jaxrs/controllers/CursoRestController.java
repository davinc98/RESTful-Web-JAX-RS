package org.jperez.jaxrs.controllers;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jperez.jaxrs.models.Curso;
import org.jperez.jaxrs.services.CursoServicio;

import java.util.Optional;

@RequestScoped
@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
public class CursoRestController {

    @Inject
    private CursoServicio service;


    @GET
    public Response listar(){
        return Response.ok(service.listar()).build();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id){
        Optional<Curso> cursoOptional = service.porId(id);
        if(cursoOptional.isPresent()){
            return Response.ok(cursoOptional.get()).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(Curso curso){
        try {
            Curso nuevo = service.crear(curso);
            return Response.ok(nuevo).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id, Curso curso){
        Optional<Curso> cursoOptional = service.porId(id);
        if(cursoOptional.isPresent()){
            Curso nuevo = cursoOptional.get();
            nuevo.setNombre(curso.getNombre());
            nuevo.setDescripcion(curso.getDescripcion());
            nuevo.setDuracion(curso.getDuracion());
            nuevo.setInstructor(curso.getInstructor());

            try{
                service.crear(nuevo);
                return Response.ok(nuevo).build();
            }catch (Exception e){
                e.printStackTrace();
                return Response.serverError().build();
            }
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id){
        Optional<Curso> cursoOptional = service.porId(id);
        if(cursoOptional.isPresent()){
            try {
                service.eliminar(cursoOptional.get().getId());
                return Response.noContent().build();
            }catch (Exception e){
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }


}
