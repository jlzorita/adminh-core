package edu.uoc.tfg.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    private Long id;
    private Date fechaFactura;
    private String numeroFactura;
    private String descripcion;
    private Float importe;
    private Boolean pagada;
    private Boolean autorizada;
    private Date fechaPago;
    //private String pdf;
    private Boolean pdf;
    private Long proveedorId;
    @JsonIgnore
    private Comunidad comunidad;
    @JsonIgnore
    private Partida partida;

}
