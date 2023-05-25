package edu.uoc.tfg.core.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import edu.uoc.tfg.core.SubirFicheroProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SubirFicheroServiceImpl implements SubirFicheroService {
	private final Path directorioRaiz;

	@Autowired
	public SubirFicheroServiceImpl(SubirFicheroProperties properties) {
		this.directorioRaiz = Paths.get(properties.getDirectorioRaiz());
	}

	@Override
	public Boolean guardar(MultipartFile file, String nombre) {
		try {
			if (file.isEmpty()) throw new RuntimeException("Error: fichero vacío");

			Path destinationFile = this.directorioRaiz.resolve(Paths.get(nombre)).normalize().toAbsolutePath();
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,StandardCopyOption.REPLACE_EXISTING);
				return true;
			}
		}catch (IOException e) {
			throw new RuntimeException("Error al guardar el fichero", e);
		}
	}

	@Override
	public Resource cargar(String nombreFichero) {
		try {
			Path file = directorioRaiz.resolve(nombreFichero);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) return resource;
			else throw new RuntimeException("No se puede leer el fichero");
		} catch (MalformedURLException e) {
			throw new RuntimeException("No se puede leer el fichero", e);
		}
	}
}
