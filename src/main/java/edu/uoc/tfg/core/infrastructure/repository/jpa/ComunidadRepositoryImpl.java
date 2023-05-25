package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Cliente;
import edu.uoc.tfg.core.domain.Comunidad;
import edu.uoc.tfg.core.domain.Entidad;
import edu.uoc.tfg.core.domain.EntidadPropietario;
import edu.uoc.tfg.core.domain.repository.ComunidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComunidadRepositoryImpl implements ComunidadRepository {

    private final SpringDataComunidadRepository cJpaRepository;

    @Override
    public Optional<Comunidad> buscaComunidad(Long comunidad) {
        return cJpaRepository.findById(comunidad).map(ComunidadEntity::toDomain);
    }

    @Override
    public List<Comunidad> buscaComunidadesAdministradas(String administrador) {
        return cJpaRepository.findAllByAdministradorEquals(administrador).stream().map(ComunidadEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean esPresidente(Long clienteId, Long comunidadId) {

        ComunidadEntity ce = cJpaRepository.getById(comunidadId);
        return(ce.getPresidenteId().equals(clienteId));
    }
}
