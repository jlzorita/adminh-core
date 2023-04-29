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
public class Entidad {
    private Long id;
    private String nombre;
    private Float coeficiente;

    @Builder.Default
    @JsonIgnore
    private Set<Recibo> recibos = new HashSet<>();
    @Builder.Default
    @JsonIgnore
    private Set<EntidadPropietario> propietarios = new HashSet<>();

    private Comunidad comunidad;



}
