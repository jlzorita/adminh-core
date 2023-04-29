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
public class Comunidad {
    private Long id;
    private String nombre;
    private String direccion;
    private String cif;
    private String cp;
    private String municipio;
    private String provincia;
    private Long presidenteId;

    @Builder.Default
    @JsonIgnore
    private Set<Publicacion> publicaciones = new HashSet<>();

    @Builder.Default
    @JsonIgnore
    private Set<Presupuesto> presupuestos = new HashSet<>();

    @Builder.Default
    @JsonIgnore
    private Set<Entidad> entidades = new HashSet<>();

    @Builder.Default
    @JsonIgnore
    private Set<Factura> facturas = new HashSet<>();
}
