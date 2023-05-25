package edu.uoc.tfg.core.application.kafka;

import edu.uoc.tfg.core.SesionData;
import edu.uoc.tfg.core.domain.service.ClienteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaClassListener {

    @Autowired
    private ClienteService clienteService;

    @KafkaListener(topics = KafkaConstants.TOPIC_SESSION, groupId = "group-1")
    void setSession(SesionData sesion) {
        log.trace("SessionSet");

        clienteService.setSession(sesion);
    }
}