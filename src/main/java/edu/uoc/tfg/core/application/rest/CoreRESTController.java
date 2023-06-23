package edu.uoc.tfg.core.application.rest;

import edu.uoc.tfg.core.application.request.FacturaRequest;
import edu.uoc.tfg.core.application.request.PublicacionRequest;
import edu.uoc.tfg.core.application.request.ReciboRequest;
import edu.uoc.tfg.core.domain.*;
import edu.uoc.tfg.core.domain.service.CoreService;
import edu.uoc.tfg.core.domain.service.SubirFicheroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
@RestController
public class    CoreRESTController {

    private final CoreService coreService;
    private final SubirFicheroService subirFicheroService;

    @GetMapping("/factura/ver/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> verFactura(@PathVariable String filename, @RequestParam String comunidadId,
                                    @RequestParam String sesion) {
        log.trace("verFactura");

        //Solo usuario administrador y vecino de la comunidad

        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));

        if(Cliente.comprobarNivelUsuario(sesion).equals("2") || coreService.esVecino(clienteId,Long.parseLong(comunidadId))) {
            Resource file = subirFicheroService.cargar(filename);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/factura/subir")
    public ResponseEntity subirFactura(@RequestParam("file") MultipartFile factura,
                                       @RequestParam String id, @RequestParam String sesion) {
        log.trace("subirFactura");

        //Se comprueba que el usuario sea administrador
        String nivel = Cliente.comprobarNivelUsuario(sesion);
        if(nivel == null || !nivel.equals("2")) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        coreService.subirFactura(Long.parseLong(id));
        subirFicheroService.guardar(factura, id + ".pdf");

        return ResponseEntity.ok().body("Fichero subido correctamente");
    }

    @GetMapping("/comunidades/{clienteId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EntidadPropietario>> getEntidadesPorCliente(@PathVariable Long clienteId, @RequestParam String sesion) {
        log.trace("getEntidadesPorCliente");
        String cId = Cliente.getCliente(sesion);

        //El usuario que lo solicita tiene que ser el mismo
        if(cId == null || !Long.toString(clienteId).equals(cId)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<EntidadPropietario> entidades = coreService.buscaEntidadesPorCliente(clienteId);
        if(entidades.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            return ResponseEntity.ok().body(entidades);
        }
    }

    @GetMapping("/comunidades/administrador/{administrador}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Comunidad>> getComunidadesAdministradas(@PathVariable String administrador, @RequestParam String sesion) {
        log.trace("getComunidadesAdministradas");

        //Se comprueba que el usuario sea administrador
        String nivel = Cliente.comprobarNivelUsuario(sesion);
        if(nivel == null || !nivel.equals("2")) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<Comunidad> comunidades = coreService.buscaComunidadesAdministradas(administrador);
        if(comunidades.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            return ResponseEntity.ok().body(comunidades);
        }
    }

    @GetMapping("/comunidad/entidades/{comunidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Entidad>> getEntidadesComunidad(@PathVariable Long comunidadId, @RequestParam String sesion) {
        log.trace("getEntidadesComunidad");
        //Puede consultar un usuario administrador o un vecino de la comunidad
        String nivel = Cliente.comprobarNivelUsuario(sesion);

        if(nivel == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
        if(nivel.equals("2") || coreService.esVecino(clienteId, comunidadId))
            return ResponseEntity.ok().body(coreService.buscaEntidadesComunidad(comunidadId));
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/comunidad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Comunidad> getDatosComunidad(@PathVariable Long id, @RequestParam String sesion) {
        log.trace("getDatosComunidad");

        //Puede consultar un usuario administrador o un vecino de la comunidad
        String nivel = Cliente.comprobarNivelUsuario(sesion);
        if(nivel == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
        if(nivel.equals("2") || coreService.esVecino(clienteId, id))
            return coreService.buscaComunidad(id).map(comunidad -> ResponseEntity.ok().body(comunidad))
                    .orElse(ResponseEntity.notFound().build());
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/comunidadEntidad/{entidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> getComunidadIdPorEntidad(@PathVariable Long entidadId, @RequestParam String sesion) {
        log.trace("getIdComunidadPorEntidad");

        //Para consultarlo se debe ser vecino de la comunidad o ser usuario administrador
        String nivel = Cliente.comprobarNivelUsuario(sesion);

        if(nivel == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
        Long comunidadId = coreService.buscaComunidadPorEntidadId(entidadId);

        if(nivel.equals("2") || coreService.esVecino(clienteId, comunidadId))
            return ResponseEntity.ok(comunidadId);
        else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/publicacion/{comunidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Publicacion>> getPublicacionesComunidad(@PathVariable Long comunidadId, @RequestParam String sesion) {
        log.trace("getPublicacionesComunidad");

        //Para consultarlo se debe ser vecino de la comunidad o ser usuario administrador

        String nivel = Cliente.comprobarNivelUsuario(sesion);
        if(nivel == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));

        if(nivel.equals("2") || coreService.esVecino(clienteId, comunidadId)){
            List<Publicacion> publicaciones = coreService.buscaPublicacionesCom(comunidadId);
            if(publicaciones.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else return ResponseEntity.ok().body(publicaciones);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/publicacion/crear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity crearPublicacion(@RequestBody PublicacionRequest publicacionRequest, @RequestParam String sesion) {
        log.trace("crearPublicacion");

        //Solo usuario administrador
        String nivel = Cliente.comprobarNivelUsuario(sesion);
        if(nivel == null || !nivel.equals("2")) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(coreService.crearPublicacion(publicacionRequest)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/publicacion/eliminar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity eliminarPublicacion(@PathVariable Long id, @RequestParam String sesion) {
        log.trace("eliminarPublicacion");

        //Solo usuario administrador
        String nivel = Cliente.comprobarNivelUsuario(sesion);
        if(nivel == null || !nivel.equals("2")) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(coreService.eliminarPublicacion(id)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/factura/{partidaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Factura>> getFacturasPorPartida(@PathVariable Long partidaId, @RequestParam String pendientes,
                                                               @RequestParam String sesion) {
        log.trace("getFacturasPorPartida");

        //Solo usuario administrador y vecinos de la comunidad
        List<Factura> facturas;

        String nivel = Cliente.comprobarNivelUsuario(sesion);
        if(nivel == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
        Long comunidadId = coreService.buscaComunidadPartida(partidaId);

        if(pendientes.equals("no"))  facturas =  coreService.buscaFacturasPagadas(partidaId);
        else if(pendientes.equals("si")) facturas = coreService.buscaFacturasPorPartida(partidaId);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        if(facturas.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            if(nivel.equals("2") || coreService.esVecino(clienteId,comunidadId))
                return ResponseEntity.ok().body(facturas);
            else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/factura/pendientes/{comunidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Factura>> getFacturasPendientesPorComunidad(@PathVariable Long comunidadId, @RequestParam String sesion) {
        log.trace("getFacturasPendientesPorComunidad");

        //Solo usuario administrador y vecino de la comunidad

        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<Factura> facturas = coreService.buscaFacturasPendientes(comunidadId);
        if(facturas.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
            if(Cliente.comprobarNivelUsuario(sesion).equals("2") || coreService.esVecino(clienteId,comunidadId))
                return ResponseEntity.ok().body(facturas);
            else
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/factura/detalle/{facturaId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Factura> getDetalleFactura(@PathVariable Long facturaId,
                                            @RequestParam Long comunidadId, @RequestParam String sesion) {
        log.trace("getDetalleFactura");

        //Solo usuario administrador y vecino de la comunidad
        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Factura factura = coreService.buscaDetalleFactura(facturaId);

        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
        if(Cliente.comprobarNivelUsuario(sesion).equals("2") || coreService.esVecino(clienteId,comunidadId))
            return ResponseEntity.ok().body(factura);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/factura/crear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity crearfactura(@RequestBody FacturaRequest facturaRequest, @RequestParam String sesion){
        log.trace("crearFactura");

        // Solo usuario administrador
        if(Cliente.comprobarNivelUsuario(sesion) != null && Cliente.comprobarNivelUsuario(sesion).equals("2")) {
            Boolean requiereAut = coreService.buscaPartidaReqAut(facturaRequest.getPartidaId());
            Boolean autorizada = true;
            if(requiereAut) autorizada = false;
            if(coreService.crearFactura(facturaRequest, autorizada)) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/factura/autorizar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity autorizarFactura(@PathVariable Long id, @RequestParam String sesion) {
        log.trace("autorizarFactura");

        //Solo usuario presidente
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
        Long comunidadId = coreService.buscaComunidadFactura(id);
        Boolean esPresidente = coreService.esPresidente(clienteId, comunidadId);

        if(esPresidente) {
            if(coreService.autorizarFactura(id)) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/factura/autorizar/pendientes/{comunidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Factura>> getFacturasAutorizarPorComunidad(@PathVariable Long comunidadId, @RequestParam String sesion) {
        log.trace("getFacturasPendientesAutorizarPorComunidad");

        //Solo presidente y vecinos comunidad
        List<Factura> facturas = coreService.facturasPendientesAutCom(comunidadId);
        if (facturas.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
            if(Cliente.comprobarNivelUsuario(sesion).equals("2") || coreService.esVecino(clienteId, comunidadId))
                return ResponseEntity.ok().body(facturas);
            else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/recibo/crear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity crearRecibo(@RequestBody ReciboRequest reciboRequest, @RequestParam String sesion) {
        log.trace("crearRecibo");

        //Solo usuario administrador
        if(Cliente.comprobarNivelUsuario(sesion) != null && Cliente.comprobarNivelUsuario(sesion).equals("2")) {
            if(coreService.crearRecibo(reciboRequest)) return new ResponseEntity<>(HttpStatus.OK);
            else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/recibo/{comunidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Recibo>> getRecibosPagadosPorFecha(@PathVariable Long comunidadId, @RequestParam String anualidad, @RequestParam String sesion) {
        log.trace("getRecibosPagadosPorFecha");

        //Solo usuario administrador y vecino comunidad
        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<Recibo> recibos = coreService.buscaRecibosPagadosCom(comunidadId, anualidad);

        if(recibos.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
            if(Cliente.comprobarNivelUsuario(sesion).equals("2") || coreService.esVecino(clienteId, comunidadId))
                return ResponseEntity.ok().body(recibos);
            else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/recibo/pendientes/{comunidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Recibo>> getRecibosPendientes(@PathVariable Long comunidadId, @RequestParam String sesion) {
        log.trace("getRecibosPendientes");

        //Solo administrador
        List<Recibo> recibos = coreService.buscaRecibosPendientes(comunidadId);
        if(recibos.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            if(Cliente.comprobarNivelUsuario(sesion) != null && Cliente.comprobarNivelUsuario(sesion).equals("2"))
                return ResponseEntity.ok().body(recibos);
            else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/recibo/pendientes/entidad/{entidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Recibo>> getRecibosPendientesEntidad(@PathVariable Long entidadId, @RequestParam String sesion) {
        log.trace("getRecibosPendientesEntidad");

        //Solo administrador y propietario
        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        List<Recibo> recibos = coreService.buscaRecibosPendEntidad(entidadId);
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));

        Boolean esPropietario = coreService.esPropietario(clienteId, entidadId);

        if(Cliente.comprobarNivelUsuario(sesion).equals("2") || esPropietario) {
            if (recibos.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else return ResponseEntity.ok().body(recibos);
        } else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/presupuesto/{comunidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Presupuesto>> getPresupuestosComunidad(@PathVariable Long comunidadId, @RequestParam String sesion) {
        log.trace("getPresupuestosComunidad");

        //Solo administrador o vecino comunidad

        List<Presupuesto> presupuestos = coreService.buscaDatosPresupuesto(comunidadId);

        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));

        if(presupuestos.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            if(Cliente.comprobarNivelUsuario(sesion).equals("2") || coreService.esVecino(clienteId, comunidadId))
                return ResponseEntity.ok().body(presupuestos);
            else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/presupuesto/partidas/{presupuestoId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Partida>> getPartidasPresupuestoComunidad(@PathVariable Long presupuestoId, @RequestParam String sesion) {
        log.trace("getPartidasPresupuestoComunidad");

        //Solo administrador y vecino comunidad
        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));
        Long comunidadId = coreService.buscaComunidadPresupuesto(presupuestoId);

        List<Partida> partidas = coreService.buscaPartidasPresupuesto(presupuestoId);
        if(partidas.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            if(Cliente.comprobarNivelUsuario(sesion).equals("2") || coreService.esVecino(clienteId, comunidadId))
                return ResponseEntity.ok().body(partidas);
            else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/comunidad/clientes/{comunidadId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EntidadPropietario>> getClientesComunidad(@PathVariable Long comunidadId, @RequestParam String sesion) {
        log.trace("getClientesComunidad");

        //Solo administrador o vecino comunidad
        if(Cliente.comprobarNivelUsuario(sesion) == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long clienteId = Long.parseLong(Cliente.getCliente(sesion));

        List<EntidadPropietario> clientes = coreService.buscaClientesComunidad(comunidadId);

        if(clientes.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            if(Cliente.comprobarNivelUsuario(sesion).equals("2") || coreService.esVecino(clienteId, comunidadId))
                return ResponseEntity.ok().body(clientes);
            else return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
