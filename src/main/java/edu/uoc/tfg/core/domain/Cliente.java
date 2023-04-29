package edu.uoc.tfg.core.domain;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor

public class Cliente {
    protected static Map<String,String> sesiones = new HashMap<>();
    public static String getSesion(String usuario){
        if(sesiones.containsKey(usuario))
            return sesiones.get(usuario);
        else return null;
    }

    public static void addUsuario(String usuario, String sesion){
        sesiones.put(usuario,sesion);
    }
    public static void removeUsuario(String usuario){
        if(sesiones.containsKey(usuario))
            sesiones.remove(usuario);
    }
}
