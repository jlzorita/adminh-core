package edu.uoc.tfg.core.domain;

import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EntidadPropietario {
    private Long id;
    private Long clienteId;
    @NotNull
    private Entidad entidad;
    private Float porcentajePropiedad;
}
