package edu.uoc.tfg.core.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Recibo {
    private Long id;
    private String concepto;
    private Date fechaRecibo;
    private Float importe;
    private Date fechaPago;
    private Boolean pagado;
    private Entidad entidad;
}
