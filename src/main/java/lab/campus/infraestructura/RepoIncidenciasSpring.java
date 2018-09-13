package lab.campus.infraestructura;

import lab.campus.dominio.Incidencia;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RepoIncidenciasSpring extends CrudRepository<Incidencia,String> {

    Incidencia findByNombreIncidencia(String nombre);
    void deleteByNombreIncidencia(String nombre);
    ArrayList<Incidencia> findAllByOrderByHoraFechaCreadaDesc();
}

