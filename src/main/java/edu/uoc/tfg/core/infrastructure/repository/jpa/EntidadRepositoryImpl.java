package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Comunidad;
import edu.uoc.tfg.core.domain.Entidad;
import edu.uoc.tfg.core.domain.EntidadPropietario;
import edu.uoc.tfg.core.domain.repository.EntidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EntidadRepositoryImpl implements EntidadRepository {

    private final SpringDataEntidadPropietarioRepository epJpaRepository;
    private final SpringDataEntidadRepository eJpaRepository;

    @Override
    public List<EntidadPropietario> buscaClientesComunidad(Long presupuestoId) {
        Entidad e = new Entidad();
        Comunidad c = new Comunidad();
        c.setId(presupuestoId);
        e.setComunidad(c);

        EntidadPropietario ep = EntidadPropietario.builder().entidad(e).build();
        return epJpaRepository.findAll(Example.of(EntidadPropietarioEntity.fromDomain(ep))).stream().map(EntidadPropietarioEntity::toDomain).collect(Collectors.toList());

    }

    @Override
    public List<EntidadPropietario> buscaEntidadesPorCliente(Long cliente) {
        EntidadPropietario ep = EntidadPropietario.builder().clienteId(cliente).build();
        return epJpaRepository.findAll(Example.of(EntidadPropietarioEntity.fromDomain(ep))).stream().map(EntidadPropietarioEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Entidad> buscaEntidadesComunidad(Long comunidad) {
        Comunidad c = new Comunidad();
        c.setId(comunidad);
        Entidad ep = Entidad.builder().comunidad(c).build();
        return eJpaRepository.findAll(Example.of(EntidadEntity.fromDomain(ep))).stream().map(EntidadEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Long buscaComunidadPorEntidadId(Long entidadId) {
        EntidadEntity e = eJpaRepository.findById(entidadId).get();
        return e.getComunidad().getId();
    }

    @Override
    public Boolean esVecino(Long clienteId, Long comunidadId) {
        List<EntidadPropietario> ep = buscaClientesComunidad(comunidadId);
        for(EntidadPropietario entidad : ep){
            if(entidad.getId() == clienteId)
                return true;
        }
        return false;
    }

    @Override
    public Boolean esPropietario(Long clienteId, Long entidadId) {
        Optional<EntidadEntity> ee = eJpaRepository.findById(entidadId);

        Set<EntidadPropietarioEntity> propietarios = ee.get().getPropietarios();
        Iterator<EntidadPropietarioEntity> it = propietarios.iterator();
        while(it.hasNext()){
            EntidadPropietarioEntity ep = it.next();
            if(ep.getClienteId().equals(clienteId))
                return true;
        }
        return false;
    }
}
