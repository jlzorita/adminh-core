package edu.uoc.tfg.core.domain.service;

import edu.uoc.tfg.core.application.request.FacturaRequest;
import edu.uoc.tfg.core.application.request.PublicacionRequest;
import edu.uoc.tfg.core.application.request.ReciboRequest;
import edu.uoc.tfg.core.domain.*;

import java.util.List;
import java.util.Optional;

public interface CoreService {
    Optional<Comunidad> buscaComunidad(Long comunidad);
    List<EntidadPropietario> buscaEntidadesPorCliente(Long cliente);
    List<Recibo> buscaRecibosPagadosCom(Long comunidadId, String anualidad);
    List<Entidad> buscaEntidadesComunidad(Long comunidad);
    List<Publicacion> buscaPublicacionesCom(Long comunidadId);
    Boolean crearPublicacion(PublicacionRequest publicacionRequest);
    Boolean eliminarPublicacion(Long id);
    Boolean crearRecibo(ReciboRequest reciboRequest);
    Boolean crearFactura(FacturaRequest facturaRequest, Boolean autorizada);
    Boolean autorizarFactura(Long id);
    Boolean subirFactura(Long id);
    List<Factura> buscaFacturasPorPartida(Long partidaId);
    List<Factura> buscaFacturasPagadas(Long partidaId);
    List<Factura> buscaFacturasPendientes(Long comunidadId);
    List<Factura> facturasPendientesAutCom(Long comunidadId);
    List<Recibo> buscaRecibosPendientes(Long comunidadId);
    List<Recibo> buscaRecibosPendEntidad(Long entidadId);
    List<Presupuesto> buscaDatosPresupuesto(Long comunidadId);
    List<Partida> buscaPartidasPresupuesto(Long presupuestoId);
    Long buscaComunidadPorEntidadId(Long entidadId);
    List<Comunidad> buscaComunidadesAdministradas(String administrador);
    List<EntidadPropietario> buscaClientesComunidad(Long comunidadId);
    Boolean esVecino(Long clienteId, Long comunidadId);
    Long buscaComunidadPartida(Long partidaId);
    Long buscaComunidadFactura(Long id);
    Boolean esPresidente(Long clienteId, Long comunidadId);
    Boolean esPropietario(Long clienteId, Long entidadId);
    Long buscaComunidadPresupuesto(Long presupuestoId);
    Boolean buscaPartidaReqAut(Long partidaId);
    Factura buscaDetalleFactura(Long facturaId);
}
