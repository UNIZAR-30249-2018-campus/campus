package lab.campus.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class IncidenciaFactory {

    @Autowired
    RepositorioEspacios repositorioEspacios;

    public Incidencia crearIncidencia(Double latitud, Double longitud, String nombre,String descripcion, int planta){
        Localizacion localizacion = new Localizacion(latitud,longitud,planta);
        Optional<Espacio> espacioResultado = repositorioEspacios.ObtenerEspacioPorLoca(localizacion);
        boolean exterior = true;
        String idespacio = "exterior";
        String edificio = "exterior del campus";
        if(espacioResultado.isPresent()){
            exterior=false;
            idespacio=espacioResultado.get().getId();
            edificio = espacioResultado.get().getEdificio();
        }
        Incidencia nuevaIncidencia =  new Incidencia(descripcion,nombre,false,new Date(),localizacion,idespacio,edificio,0);
        return nuevaIncidencia;
    }
}

