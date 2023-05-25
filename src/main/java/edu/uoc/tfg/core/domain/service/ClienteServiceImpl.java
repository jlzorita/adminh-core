package edu.uoc.tfg.core.domain.service;

import edu.uoc.tfg.core.SesionData;
import edu.uoc.tfg.core.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClienteServiceImpl implements ClienteService {

    //private final ClienteRepository clienteRepository;

    @Override
    public void setSession(SesionData sesion) {
        Cliente.removeUsuario(sesion.getUsuario());
        if(sesion.isAlta())
            Cliente.addUsuario(sesion.getUsuario(),sesion.getSesion());
    }

}