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
public class Publicacion {
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
    private String titulo;
    private String mensaje;
    private Date fechaEvento;
    @JsonIgnore
    private Comunidad comunidad;
}
