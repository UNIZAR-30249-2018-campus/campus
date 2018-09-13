package lab.campus.aplicacion;

import com.google.gson.Gson;
import lab.campus.dominio.Incidencia;
import lab.campus.dominio.IncidenciaFactory;
import lab.campus.dominio.RepositorioEspacios;
import lab.campus.dominio.RepositorioIncidencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControladorIncidencia {

    @Autowired
    RepositorioIncidencias repositorioIncidencias;
    @Autowired
    RepositorioEspacios repositorioEspacios;
    @Autowired
    IncidenciaFactory incidenciaFactory;


    @Transactional
    @RequestMapping(value="/crearincidencia", method = RequestMethod.POST)
    public String crearIncidencia(@RequestBody RequestIncidencia incidenciaEntrante){
        Incidencia nuevaIncidencia = incidenciaFactory.crearIncidencia(incidenciaEntrante.getLatitud(),incidenciaEntrante.getLongitud(),
                incidenciaEntrante.getNombre(),incidenciaEntrante.getDescripcion(),incidenciaEntrante.getPlanta());
        repositorioIncidencias.anyadirIncidencia(nuevaIncidencia);


        return new String("Success");
    }

    @RequestMapping(value = "/registro", method = RequestMethod.GET)
    public  @ResponseBody
    String obtenerRegistroIncidencias(){
        ArrayList<Incidencia> resultado = repositorioIncidencias.IncidenciasPorFecha();
        ArrayList<InfoIncidencia> registroDevuelto = new ArrayList<>();
        Gson gson = new Gson();
        if(resultado.isEmpty()){
            return gson.toJson(registroDevuelto);
        }else {
            for(Incidencia i : resultado){
                registroDevuelto.add(InfoIncidencia.crearInfoDeIncidencia(i));

            }
            return gson.toJson(registroDevuelto);
        }

    }

    @RequestMapping(value = "/cancelarinci", method = RequestMethod.POST)
    public  @ResponseBody
    String cancelarIncidencia(@RequestBody RequestActuIncidencia incidenciaEntrante){
        String id = incidenciaEntrante.getId();
        String estado = incidenciaEntrante.getEstado();

        Incidencia incidencia = repositorioIncidencias.buscarIncidenciaId(id);

        incidencia.cancelar();

        Gson gson = new Gson();

        try{
            repositorioIncidencias.actualizarIncidencia(incidencia);
        } catch (ObjectOptimisticLockingFailureException o){
            return gson.toJson("admin.html");
        }

        return gson.toJson("admin.html");

    }

    @RequestMapping(value = "/aceptarinci", method = RequestMethod.POST)
    public  @ResponseBody
    String aceptarIncidencia(@RequestBody RequestActuIncidencia incidenciaEntrante){
        String id = incidenciaEntrante.getId();
        String estado = incidenciaEntrante.getEstado();

        Incidencia incidencia = repositorioIncidencias.buscarIncidenciaId(id);

        incidencia.aceptar();

        repositorioIncidencias.actualizarIncidencia(incidencia);

        Gson gson = new Gson();

        try{
            repositorioIncidencias.actualizarIncidencia(incidencia);
        } catch (ObjectOptimisticLockingFailureException o){
            return gson.toJson("admin.html");
        }

        return gson.toJson("admin.html");
    }

    @RequestMapping(value = "/completarinci", method = RequestMethod.POST)
    public  @ResponseBody
    String completarIncidencia(@RequestBody RequestActuIncidencia incidenciaEntrante){
        String id = incidenciaEntrante.getId();
        String estado = incidenciaEntrante.getEstado();

        Incidencia incidencia = repositorioIncidencias.buscarIncidenciaId(id);

        incidencia.completar();

        repositorioIncidencias.actualizarIncidencia(incidencia);

        Gson gson = new Gson();

        try{
            repositorioIncidencias.actualizarIncidencia(incidencia);
        } catch (ObjectOptimisticLockingFailureException o){
            return gson.toJson("admin.html");
        }

        return gson.toJson("admin.html");

    }


}