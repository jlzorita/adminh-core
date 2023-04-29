package edu.uoc.tfg.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Partida {
    private Long id;
    private String nombre;
    private Boolean autorizacion;
    @Builder.Default
    @JsonIgnore
    private Set<Factura> facturas = new HashSet<>();
}
