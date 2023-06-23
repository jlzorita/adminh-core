package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.application.request.ReciboRequest;
import edu.uoc.tfg.core.domain.Comunidad;
import edu.uoc.tfg.core.domain.Entidad;
import edu.uoc.tfg.core.domain.Recibo;
import edu.uoc.tfg.core.domain.repository.ReciboRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReciboRepositoryImpl implements ReciboRepository {

    private final SpringDataReciboRepository jpaRepository;
    @Override
    public Boolean crearRecibo(ReciboRequest reciboRequest) {
        log.trace("crearRecibo");
        Date fechaRecibo = reciboRequest.getFechaRecibo();
        String concepto = reciboRequest.getConcepto();
        Float importe = reciboRequest.getImporte();
        Long entidadId = reciboRequest.getEntidadId();
        Long comunidadId = reciboRequest.getComunidadId();

        if(entidadId <= 0) return false;
        else{
            EntidadEntity entidad = new EntidadEntity();
            entidad.setId(entidadId);
            ComunidadEntity comunidad = new ComunidadEntity();
            comunidad.setId(comunidadId);
            ReciboEntity recibo = new ReciboEntity();
            recibo.setFechaRecibo(fechaRecibo);
            recibo.setConcepto(concepto);
            recibo.setImporte(importe);
            recibo.setEntidad(entidad);
            recibo.setComunidad(comunidad);
            recibo.setPagado(false);
            jpaRepository.save(recibo);
            return true;
        }
    }

    @Override
    public List<Recibo> buscaRecibosPagadosCom(Long comunidadId, String anualidad) {
        List<ReciboEntity> re = jpaRepository.getRecibosPagadosPorAnualidadyComunidad(comunidadId, Integer.parseInt(anualidad)).get();
        return re.stream().map(ReciboEntity::toDomain).collect(Collectors.toList());

    }
    @Override
    public List<Recibo> buscaRecibosPendientes(Long comunidadId) {
        Comunidad comunidad = new Comunidad();
        comunidad.setId(comunidadId);
        Recibo r = Recibo.builder().comunidad(comunidad).build();
        r.setPagado(false);
        return jpaRepository.findAll(Example.of(ReciboEntity.fromDomain(r))).stream().map(ReciboEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Recibo> buscaRecibosPendEntidad(Long entidadId) {
        Entidad entidad = new Entidad();
        entidad.setId(entidadId);
        Recibo r = Recibo.builder().entidad(entidad).build();
        r.setPagado(false);
        return jpaRepository.findAll(Example.of(ReciboEntity.fromDomain(r))).stream().map(ReciboEntity::toDomain).collect(Collectors.toList());
    }
}
