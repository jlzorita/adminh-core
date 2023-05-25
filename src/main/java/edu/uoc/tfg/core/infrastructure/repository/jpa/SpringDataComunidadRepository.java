package edu.uoc.tfg.core.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataComunidadRepository extends JpaRepository<ComunidadEntity, Long> {
    List<ComunidadEntity> findAllByAdministradorEquals(String administrador);


}
