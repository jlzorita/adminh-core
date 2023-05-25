package edu.uoc.tfg.core.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class FacturaRequest {
    @Getter
    private Date fechaFactura;
    @Getter
    private String numeroFactura;
    @Getter
    private String descripcion;
    @Getter
    private Float importe;
    @Getter
    private Long proveedorId;
    @Getter
    private Long comunidadId;
    @Getter
    private Long partidaId;

    @JsonCreator
    public FacturaRequest(  @NotNull Date fechaFactura, @NotNull String numeroFactura, @NotNull String descripcion,
                            @NotNull Float importe, @NotNull Long proveedorId, @NotNull Long comunidadId,
                            @NotNull Long partidaId) {
        this.fechaFactura = fechaFactura;
        this.numeroFactura = numeroFactura;
        this.descripcion = descripcion;
        this.importe = importe;
        this.proveedorId = proveedorId;
        this.comunidadId = comunidadId;
        this.partidaId = partidaId;
    }
}