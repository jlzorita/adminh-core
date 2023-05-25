package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Comunidad;
import edu.uoc.tfg.core.domain.Partida;
import edu.uoc.tfg.core.domain.Presupuesto;
import edu.uoc.tfg.core.domain.repository.PresupuestoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PresupuestoRepositoryImpl implements PresupuestoRepository {
    private final SpringDataPresupuestoRepository jpaRepository;
    private final SpringDataPartidaRepository partidaJpaRepository;
    @Override
    public List<Presupuesto> buscaDatosPresupuesto(Long comunidadId) {
        ComunidadEntity comunidad = new ComunidadEntity();
        comunidad.setId(comunidadId);

        Date date = new Date();
        return jpaRepository.findAllByFechaInicialLessThanEqualAndFechaFinalGreaterThanEqualAndComunidadEquals(date, date, comunidad)
                .stream().map(PresupuestoEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Partida> buscaPartidasPresupuesto(Long presupuestoId) {
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setId(presupuestoId);

        Partida p = Partida.builder().presupuesto(presupuesto).build();
        return partidaJpaRepository.findAll(Example.of(PartidaEntity.fromDomain(p))).stream().map(PartidaEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Long buscaComunidadPartida(Long partidaId) {
        Optional<PartidaEntity> partida = partidaJpaRepository.findById(partidaId);
        return partida.get().getPresupuesto().getComunidad().getId();
    }

    @Override
    public Long buscaComunidadPresupuesto(Long presupuestoId) {
        Optional<PresupuestoEntity> pe = jpaRepository.findById(presupuestoId);
        return pe.get().getComunidad().getId();
    }

    @Override
    public Boolean buscaPartidaReqAut(Long partidaId) {
        Optional<PartidaEntity> partida = partidaJpaRepository.findById(partidaId);
        return partida.get().getAutorizacion();
    }
}
