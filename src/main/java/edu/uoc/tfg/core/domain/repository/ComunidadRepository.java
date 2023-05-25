package edu.uoc.tfg.core.domain.repository;

import edu.uoc.tfg.core.domain.Comunidad;

import java.util.List;
import java.util.Optional;

public interface ComunidadRepository {

    Optional<Comunidad> buscaComunidad(Long comunidad);


    List<Comunidad> buscaComunidadesAdministradas(String administrador);

    Boolean esPresidente(Long clienteId, Long comunidadId);
}
