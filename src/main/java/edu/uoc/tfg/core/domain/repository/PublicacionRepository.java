package edu.uoc.tfg.core.domain.repository;

import edu.uoc.tfg.core.application.request.PublicacionRequest;
import edu.uoc.tfg.core.domain.Publicacion;

import java.util.List;

public interface PublicacionRepository {
    public List<Publicacion> buscaPublicaciones(Long comunidadId);

    Boolean crearPublicacion(PublicacionRequest publicacionRequest);

    Boolean eliminarPublicacion(Long id);
}
