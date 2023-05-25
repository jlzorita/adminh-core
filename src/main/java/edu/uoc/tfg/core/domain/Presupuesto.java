package edu.uoc.tfg.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Presupuesto {
    private Long id;
    private Date fechaInicial;
    private Date fechaFinal;
    private String nombre;
    private Float saldoInicial;
    @Builder.Default
    @JsonIgnore
    private Set<Partida> partidas = new HashSet<>();

    @Builder.Default
    @JsonIgnore
    private Set<Recibo> recibos = new HashSet<>();
    @JsonIgnore
    private Comunidad comunidad;
}
