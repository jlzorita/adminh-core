package edu.uoc.tfg.core.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPartidaRepository extends JpaRepository<PartidaEntity, Long> {
}

