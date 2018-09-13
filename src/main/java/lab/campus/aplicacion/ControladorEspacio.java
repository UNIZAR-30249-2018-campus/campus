package lab.campus.aplicacion;

import lab.campus.dominio.Espacio;
import lab.campus.dominio.Localizacion;
import lab.campus.dominio.RepositorioEspacios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ControladorEspacio {
    @Autowired
    RepositorioEspacios repositorioEspacios;

    @RequestMapping(value="/espacio", method = RequestMethod.POST)
    public @ResponseBody
    InfoEspacio ObtenerInfoEspacio(@RequestBody CoordenadasMapa coord){
        System.out.println(coord);
        Double lat= coord.latitud;
        Double lon = coord.longitud;
        int planta=coord.planta;

        Optional<Espacio> resultado = repositorioEspacios.ObtenerEspacioPorLoca(new Localizacion(lat,lon,planta));
        if(resultado.isPresent()){
            InfoEspacio info= new InfoEspacio(resultado.get().getPlantaEspacio(),resultado.get().getNombre(),
                    resultado.get().getEdificio(),false,resultado.get().getId());
            return  info;
        }else {
            InfoEspacio info =new InfoEspacio(planta,"Exterior","Exterior",true,"Exterior");
            return  info;
        }


    }

}
