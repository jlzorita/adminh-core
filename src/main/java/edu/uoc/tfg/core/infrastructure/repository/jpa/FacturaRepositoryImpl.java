package edu.uoc.tfg.core.infrastructure.repository.jpa;


import edu.uoc.tfg.core.application.request.FacturaRequest;
import edu.uoc.tfg.core.domain.Comunidad;
import edu.uoc.tfg.core.domain.Factura;
import edu.uoc.tfg.core.domain.Partida;
import edu.uoc.tfg.core.domain.repository.FacturaRepository;
import edu.uoc.tfg.core.domain.repository.PublicacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FacturaRepositoryImpl implements FacturaRepository {

    private final SpringDataFacturaRepository jpaRepository;
    @Override
    public List<Factura> buscaFacturasPendientes(Long comunidadId) {
        Comunidad comunidad = new Comunidad();
        comunidad.setId(comunidadId);
        Factura f = Factura.builder().comunidad(comunidad).build();
        f.setPagada(false);
        return jpaRepository.findAll(Example.of(FacturaEntity.fromDomain(f))).stream().map(FacturaEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean crearFactura(FacturaRequest facturaRequest, Boolean autorizada) {
        log.trace("crearFactura");
        Date fechaFactura = facturaRequest.getFechaFactura();
        String numeroFactura = facturaRequest.getNumeroFactura();
        String descripcion = facturaRequest.getDescripcion();
        Float importe = facturaRequest.getImporte();
        Long proveedorId = facturaRequest.getProveedorId();
        Long comunidadId = facturaRequest.getComunidadId();
        Long partidaId = facturaRequest.getPartidaId();

        if(proveedorId == 0 || comunidadId == 0 ||partidaId == 0) {
            return false;
        }else{
            FacturaEntity fe = new FacturaEntity();
            ComunidadEntity ce = new ComunidadEntity();
            ce.setId(comunidadId);
            PartidaEntity pe = new PartidaEntity();
            pe.setId(partidaId);
            fe.setPagada(false);
            fe.setAutorizada(autorizada);
            fe.setFechaFactura(fechaFactura);
            fe.setComunidad(ce);
            fe.setDescripcion(descripcion);
            fe.setImporte(importe);
            fe.setNumeroFactura(numeroFactura);
            fe.setPartida(pe);
            fe.setProveedorId(proveedorId);
            jpaRepository.save(fe);
            return true;
        }
    }

    @Override
    public Boolean autorizarFactura(Long id) {
        FacturaEntity f = jpaRepository.getById(id);
        if(f == null)
            return false;
        else{
            f.setAutorizada(true);
            jpaRepository.save(f);
            return true;
        }
    }

    @Override
    public List<Factura> facturasPendientesAutCom(Long comunidadId) {
        Comunidad comunidad = new Comunidad();
        comunidad.setId(comunidadId);
        Factura f = Factura.builder().comunidad(comunidad).build();
        f.setPagada(false);
        f.setAutorizada(false);
        return jpaRepository.findAll(Example.of(FacturaEntity.fromDomain(f))).stream().map(FacturaEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Factura> buscaFacturasPagadas(Long partidaId) {
        PartidaEntity partida = new PartidaEntity();
        partida.setId(partidaId);
        return jpaRepository.findAllByPagadaEqualsAndPartidaEquals(true,partida).stream().map(FacturaEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Factura> buscaFacturasPorPartida(Long partidaId) {
        Partida partida = new Partida();
        partida.setId(partidaId);
        Factura f = Factura.builder().partida(partida).build();
        return jpaRepository.findAll(Example.of(FacturaEntity.fromDomain(f))).stream().map(FacturaEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Long buscaComunidadFactura(Long id) {
        Optional<FacturaEntity> fe = jpaRepository.findById(id);
        return fe.get().getComunidad().getId();
    }

    @Override
    public Boolean subirFactura(Long id) {
        FacturaEntity f = jpaRepository.getById(id);
        f.setPdf(true);
        jpaRepository.save(f);
        return true;
    }

    @Override
    public Factura buscaDetalleFactura(Long facturaId) {
        FacturaEntity f = jpaRepository.getById(facturaId);
        return f.toDomain();
    }
}
