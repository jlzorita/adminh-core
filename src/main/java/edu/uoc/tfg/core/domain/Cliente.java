package edu.uoc.tfg.core.domain;

import edu.uoc.tfg.core.SesionData;
import lombok.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor

public class Cliente {
    protected static Map<String,String[]> sesiones = new HashMap<>();
    public static String[] getSesion(String usuario){
        if(sesiones.containsKey(usuario))
            return sesiones.get(usuario);
        else return null;
    }

    public static void addUsuario(String usuario, String value[]){
        sesiones.put(usuario,value);
    }
    public static void removeUsuario(String usuario){
        if(sesiones.containsKey(usuario))
            sesiones.remove(usuario);
    }

    public static String comprobarNivelUsuario(String sesion){

        String encontrado = null;
        Iterator<String> it = sesiones.keySet().iterator();

        while(it.hasNext()){
            String clave = it.next();
            String[] valor = sesiones.get(clave);
            if(valor[0].equals(sesion)) encontrado = valor[1];
        }

        return encontrado;
    }

    public static String getCliente(String sesion){

        String encontrado = null;
        Iterator<String> it = sesiones.keySet().iterator();

        while(it.hasNext()){
            String clave = it.next();
            String[] valor = sesiones.get(clave);
            if(valor[0].equals(sesion)) encontrado = valor[2];
        }

        return encontrado;
    }

    public static void setSesion(SesionData sesion) {
        Cliente.removeUsuario(sesion.getUsuario());
        if(sesion.isAlta())
            Cliente.addUsuario(sesion.getUsuario(),sesion.getSesion());
    }
}
