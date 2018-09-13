package lab.campus.dominio;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RepositorioIncidencias {

    void anyadirIncidencia(Incidencia incidencia);

    void actualizarIncidencia(Incidencia incidencia);

    void borrarIncidencia(Incidencia incidencia);

    Incidencia buscarIncidenciaNombre(String nombre);

    Incidencia buscarIncidenciaId(String id);

    ArrayList<Incidencia> IncidenciasPorFecha();

    Localizacion localizarIncidencia(String nombre);
}
