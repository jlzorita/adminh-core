package edu.uoc.tfg.core.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpringDataPresupuestoRepository extends JpaRepository<PresupuestoEntity, Long> {
    List<PresupuestoEntity> findAllByFechaInicialLessThanEqualAndFechaFinalGreaterThanEqualAndComunidadEquals(Date date1, Date date2, ComunidadEntity comunidad);

}

