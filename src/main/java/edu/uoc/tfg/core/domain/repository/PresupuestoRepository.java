package edu.uoc.tfg.core.domain.repository;

import edu.uoc.tfg.core.domain.Partida;
import edu.uoc.tfg.core.domain.Presupuesto;

import java.util.List;

public interface PresupuestoRepository {
    List<Presupuesto> buscaDatosPresupuesto(Long comunidadId);

    List<Partida> buscaPartidasPresupuesto(Long presupuestoId);

    Long buscaComunidadPartida(Long partidaId);

    Long buscaComunidadPresupuesto(Long presupuestoId);

    Boolean buscaPartidaReqAut(Long partidaId);
}
