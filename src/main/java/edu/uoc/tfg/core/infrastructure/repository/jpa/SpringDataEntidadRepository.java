package edu.uoc.tfg.core.infrastructure.repository.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataEntidadRepository extends JpaRepository<EntidadEntity, Long> {
}
