package edu.uoc.tfg.core.domain.repository;

import edu.uoc.tfg.core.application.request.ReciboRequest;
import edu.uoc.tfg.core.domain.Recibo;

import java.util.List;

public interface ReciboRepository {
    Boolean crearRecibo(ReciboRequest reciboRequest);

    List<Recibo> buscaRecibosPagadosCom(Long comunidadId, String anualidad);

    List<Recibo> buscaRecibosPendientes(Long comunidadId);

    List<Recibo> buscaRecibosPendEntidad(Long entidadId);
}
