package edu.uoc.tfg.core.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uoc.tfg.core.domain.Cliente;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PublicacionRequest {
    @Getter
    private Date fechaInicio;
    @Getter
    private Date fechaFin;
    @Getter
    private String titulo;
    @Getter
    private String mensaje;
    @Getter
    private Date fechaEvento;
    @Getter
    private Long comunidadId;

    @JsonCreator
    public PublicacionRequest(@NotNull Date fechaInicio, @NotNull Date fechaFin, @NotNull String titulo,
                          @NotNull String mensaje, @NotNull Date fechaEvento, @NotNull Long comunidadId) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaEvento = fechaEvento;
        this.comunidadId = comunidadId;
    }
}

