package edu.uoc.tfg.core.domain;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntidadPropietario {
    private Long clienteId;
    private Float porcentajePropiedad;
}
