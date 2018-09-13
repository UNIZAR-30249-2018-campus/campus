package lab.campus.infraestructura;

import lab.campus.dominio.Incidencia;
import lab.campus.dominio.Localizacion;
import lab.campus.dominio.RepositorioIncidencias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public class RepoIncidenciasImpl implements RepositorioIncidencias {

    @Autowired
    RepoIncidenciasSpring repoIncidenciasSpring;

    @Override
    @Transactional
    public void anyadirIncidencia(Incidencia incidencia) {
        repoIncidenciasSpring.save(incidencia);

    }

    @Override
    @Transactional
    public void actualizarIncidencia(Incidencia incidencia) {
        repoIncidenciasSpring.save(incidencia);
    }

    @Override
    @Transactional
    public void borrarIncidencia(Incidencia incidencia) {
        repoIncidenciasSpring.delete(incidencia);
    }

    @Override
    public Incidencia buscarIncidenciaNombre(String nombre) {
        return repoIncidenciasSpring.findByNombreIncidencia(nombre);
    }

    @Override
    public Incidencia buscarIncidenciaId(String nombre) {
        return repoIncidenciasSpring.findById(nombre).get();
    }

   @Override
    public ArrayList<Incidencia> IncidenciasPorFecha() {
        return repoIncidenciasSpring.findAllByOrderByHoraFechaCreadaDesc();
    }

    @Override
    public Localizacion localizarIncidencia(String nombre) {
        return null;
    }
}
