package edu.uoc.tfg.core.application.rest;

import edu.uoc.tfg.core.domain.Cliente;
import edu.uoc.tfg.core.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
@RestController
public class ClienteRESTController {

    private final ClienteService ClienteService;

    @GetMapping("/user/sesion/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public String[] getClienteSesion(@PathVariable String usuario) {
        return Cliente.getSesion(usuario);
    }
}