package edu.uoc.tfg.core.domain.repository;

import edu.uoc.tfg.core.application.request.FacturaRequest;
import edu.uoc.tfg.core.domain.Factura;

import java.util.List;

public interface FacturaRepository {
    List<Factura> buscaFacturasPendientes(Long comunidadId);

    Boolean crearFactura(FacturaRequest facturaRequest, Boolean autorizada);

    Boolean autorizarFactura(Long id);

    List<Factura> facturasPendientesAutCom(Long comunidadId);

    List<Factura> buscaFacturasPagadas(Long partidaId);

    List<Factura> buscaFacturasPorPartida(Long partidaId);

    Long buscaComunidadFactura(Long id);

    Boolean subirFactura(Long id);

    Factura buscaDetalleFactura(Long facturaId);
}
