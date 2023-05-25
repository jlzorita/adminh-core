package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataFacturaRepository extends JpaRepository<FacturaEntity, Long> {
    List<FacturaEntity> findAllByPagadaEqualsAndPartidaEquals(Boolean valor, PartidaEntity partida);

}

