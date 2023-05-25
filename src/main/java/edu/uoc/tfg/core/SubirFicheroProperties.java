package edu.uoc.tfg.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("guardarficheros")
public class SubirFicheroProperties {

	@Getter
	@Setter
	private String directorioRaiz = "";

}
