package edu.uoc.tfg.core.domain.service;

import edu.uoc.tfg.core.application.request.FacturaRequest;
import edu.uoc.tfg.core.application.request.PublicacionRequest;
import edu.uoc.tfg.core.application.request.ReciboRequest;
import edu.uoc.tfg.core.domain.*;
import edu.uoc.tfg.core.domain.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CoreServiceImpl implements CoreService {

    private final ComunidadRepository comunidadRepository;
    private final EntidadRepository entidadRepository;
    private final PublicacionRepository publicacionRepository;
    private final FacturaRepository facturaRepository;
    private final ReciboRepository reciboRepository;
    private final PresupuestoRepository presupuestoRepository;

    @Override
    public Optional<Comunidad> buscaComunidad(Long comunidad) {
        return comunidadRepository.buscaComunidad(comunidad);

    }

    @Override
    public List<EntidadPropietario> buscaEntidadesPorCliente(Long cliente) {
        return entidadRepository.buscaEntidadesPorCliente(cliente);
    }

    @Override
    public List<Entidad> buscaEntidadesComunidad(Long comunidad) {
        return entidadRepository.buscaEntidadesComunidad(comunidad);
    }

    @Override
    public List<Publicacion> buscaPublicacionesCom(Long comunidadId) {
        return publicacionRepository.buscaPublicaciones(comunidadId);
    }

    @Override
    public Boolean crearPublicacion(PublicacionRequest publicacionRequest) {
        return publicacionRepository.crearPublicacion(publicacionRequest);
    }

    @Override
    public Boolean eliminarPublicacion(Long id) {
        return publicacionRepository.eliminarPublicacion(id);
    }

    @Override
    public List<Factura> buscaFacturasPagadas(Long partidaId) {
        return facturaRepository.buscaFacturasPagadas(partidaId);
    }

    @Override
    public List<Factura> buscaFacturasPorPartida(Long partidaId) {
        return facturaRepository.buscaFacturasPorPartida(partidaId);
    }

    @Override
    public List<Factura> buscaFacturasPendientes(Long comunidadId) {
        return facturaRepository.buscaFacturasPendientes(comunidadId);
    }

    @Override
    public Boolean crearFactura(FacturaRequest facturaRequest, Boolean autorizada) {
        return facturaRepository.crearFactura(facturaRequest, autorizada);
    }

    @Override
    public Boolean autorizarFactura(Long id) {
        return facturaRepository.autorizarFactura(id);
    }

    @Override
    public List<Factura> facturasPendientesAutCom(Long comunidadId) {
        return facturaRepository.facturasPendientesAutCom(comunidadId);
    }

    @Override
    public Boolean crearRecibo(ReciboRequest reciboRequest) {
        return reciboRepository.crearRecibo(reciboRequest);
    }

    @Override
    public List<Recibo> buscaRecibosPagadosCom(Long comunidadId, String anualidad) {
        return reciboRepository.buscaRecibosPagadosCom(comunidadId, anualidad);
    }

    @Override
    public List<Recibo> buscaRecibosPendientes(Long comunidadId) {
        return reciboRepository.buscaRecibosPendientes(comunidadId);
    }

    @Override
    public List<Recibo> buscaRecibosPendEntidad(Long entidadId) {
        return reciboRepository.buscaRecibosPendEntidad(entidadId);
    }

    @Override
    public List<Presupuesto> buscaDatosPresupuesto(Long comunidadId) {
        return presupuestoRepository.buscaDatosPresupuesto(comunidadId);
    }

    @Override
    public List<Partida> buscaPartidasPresupuesto(Long presupuestoId) {
        return presupuestoRepository.buscaPartidasPresupuesto(presupuestoId);
    }

    @Override
    public Long buscaComunidadPorEntidadId(Long entidadId) {
        return entidadRepository.buscaComunidadPorEntidadId(entidadId);
    }

    @Override
    public List<Comunidad> buscaComunidadesAdministradas(String administrador) {
        return comunidadRepository.buscaComunidadesAdministradas(administrador);
    }

    @Override
    public List<EntidadPropietario> buscaClientesComunidad(Long comunidadId) {
        return entidadRepository.buscaClientesComunidad(comunidadId);
    }

    @Override
    public Boolean esVecino(Long clienteId, Long comunidadId) {
        return entidadRepository.esVecino(clienteId, comunidadId);
    }

    @Override
    public Long buscaComunidadPartida(Long partidaId) {
        return presupuestoRepository.buscaComunidadPartida(partidaId);
    }

    @Override
    public Long buscaComunidadFactura(Long id) {
        return facturaRepository.buscaComunidadFactura(id);
    }

    @Override
    public Boolean esPresidente(Long clienteId, Long comunidadId) {
        return comunidadRepository.esPresidente(clienteId, comunidadId);
    }

    @Override
    public Boolean esPropietario(Long clienteId, Long entidadId) {
        return entidadRepository.esPropietario(clienteId, entidadId);
    }

    @Override
    public Long buscaComunidadPresupuesto(Long presupuestoId) {
        return presupuestoRepository.buscaComunidadPresupuesto(presupuestoId);
    }

    @Override
    public Boolean subirFactura(Long id) {
        return facturaRepository.subirFactura(id);
    }

    @Override
    public Boolean buscaPartidaReqAut(Long partidaId) {
        return presupuestoRepository.buscaPartidaReqAut(partidaId);
    }

    @Override
    public Factura buscaDetalleFactura(Long facturaId) {
        return facturaRepository.buscaDetalleFactura(facturaId);
    }
}
