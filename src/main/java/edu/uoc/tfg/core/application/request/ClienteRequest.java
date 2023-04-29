package edu.uoc.tfg.core.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uoc.tfg.core.domain.Cliente;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class ClienteRequest {

    @Getter
    @NotNull
    private final Cliente cliente;

    @JsonCreator
    public ClienteRequest(@JsonProperty("clienteData") @NotNull final Cliente cliente) {
        this.cliente = cliente;
    }
}