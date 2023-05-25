package edu.uoc.tfg.core.domain.service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface SubirFicheroService {

    Boolean guardar(MultipartFile fichero, String nombre);
    Resource cargar(String nombreFichero);

}