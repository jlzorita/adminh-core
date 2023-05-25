package edu.uoc.tfg.core.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ReciboRequest {
    @Getter
    private String concepto;
    @Getter
    private Date fechaRecibo;
    @Getter
    private Float importe;
    @Getter
    private Long entidadId;
    @Getter
    private Long comunidadId;

    @JsonCreator
    public ReciboRequest(@NotNull String concepto, @NotNull Date fechaRecibo, @NotNull Float importe,
                         @NotNull Long entidadId, @NotNull Long comunidadId) {
        this.concepto = concepto;
        this.fechaRecibo = fechaRecibo;
        this.importe = importe;
        this.entidadId = entidadId;
        this.comunidadId = comunidadId;
    }
}
