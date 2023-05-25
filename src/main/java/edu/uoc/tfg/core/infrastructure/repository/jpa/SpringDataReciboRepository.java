package edu.uoc.tfg.core.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataReciboRepository extends JpaRepository<ReciboEntity, Long> {
    @Query("from ReciboEntity r where r.pagado = true and r.comunidad.id = ?1 and year(r.fechaRecibo) = ?2")
    Optional<List<ReciboEntity>> getRecibosPagadosPorAnualidadyComunidad(Long comunidadId, Integer anualidad);
}
