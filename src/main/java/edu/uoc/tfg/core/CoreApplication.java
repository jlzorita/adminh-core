package edu.uoc.tfg.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties(SubirFicheroProperties.class)
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
        /*
        String nivel[] = new String[3];
        nivel[0] = "hola";
        nivel[1] = "0";
        nivel[2] = "1";
        Cliente.addUsuario("jzorita",nivel);
        */
    }
}
