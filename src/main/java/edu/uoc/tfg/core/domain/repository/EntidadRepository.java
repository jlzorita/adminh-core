package edu.uoc.tfg.core.domain.repository;

import edu.uoc.tfg.core.domain.Entidad;
import edu.uoc.tfg.core.domain.EntidadPropietario;

import java.util.List;

public interface EntidadRepository {

    List<EntidadPropietario> buscaClientesComunidad(Long presupuestoId);

    List<EntidadPropietario> buscaEntidadesPorCliente(Long cliente);

    List<Entidad> buscaEntidadesComunidad(Long comunidad);
    Long buscaComunidadPorEntidadId(Long entidadId);

    Boolean esVecino(Long clienteId, Long comunidadId);

    Boolean esPropietario( Long clienteId, Long entidadId);
}
