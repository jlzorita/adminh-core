package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.application.request.PublicacionRequest;
import edu.uoc.tfg.core.domain.Comunidad;
import edu.uoc.tfg.core.domain.Publicacion;
import edu.uoc.tfg.core.domain.repository.PublicacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PublicacionRepositoryImpl implements PublicacionRepository{

    private final SpringDataPublicacionRepository jpaRepository;
    @Override
    public List<Publicacion> buscaPublicaciones(Long comunidadId) {
        log.trace("getPublicacionesComunidad");
        ComunidadEntity comunidad = new ComunidadEntity();
        comunidad.setId(comunidadId);
        Date date = new Date();
        return jpaRepository.findByFechaEventoGreaterThanAndComunidadEquals(date, comunidad).stream().map(PublicacionEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean crearPublicacion(PublicacionRequest publicacionRequest) {
        log.trace("crearPublicacion");
        String titulo = publicacionRequest.getTitulo();
        String mensaje = publicacionRequest.getMensaje();
        Date fechaInicio = publicacionRequest.getFechaInicio();
        Date fechaFin = publicacionRequest.getFechaFin();
        Date fechaEvento = publicacionRequest.getFechaEvento();
        Long comunidadId = publicacionRequest.getComunidadId();

        PublicacionEntity pe = new PublicacionEntity();
        ComunidadEntity comunidad = new ComunidadEntity();
        comunidad.setId(comunidadId);

        pe.setId(25l);
        pe.setComunidad(comunidad);
        pe.setTitulo(titulo);
        pe.setMensaje(mensaje);
        pe.setFechaEvento(fechaEvento);
        pe.setFechaInicio(fechaInicio);
        pe.setFechaFin(fechaFin);
        jpaRepository.save(pe);
        return true;
    }

    @Override
    public Boolean eliminarPublicacion(Long id) {
        if(!jpaRepository.findById(id).isPresent()) return false;
        else{
            jpaRepository.delete(jpaRepository.findById(id).get());
            return true;
        }
    }
}
