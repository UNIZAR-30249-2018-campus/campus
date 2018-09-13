package lab.campus;

import lab.campus.dominio.Incidencia;
import lab.campus.dominio.Localizacion;
import lab.campus.dominio.RepositorioIncidencias;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.OptimisticLockException;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestConcurrency {

    @Autowired
    RepositorioIncidencias repositorioIncidencias;

    @Test(expected=ObjectOptimisticLockingFailureException.class)
    public void test() {

        Localizacion l = new Localizacion(4616764.08981723, 675833.029774504, 0);
        Incidencia i = new Incidencia("descripcion","nombre",false, new Date(),l,"00.020","Torres Quevedo",0);
        repositorioIncidencias.anyadirIncidencia(i);

        Incidencia incidencia1 = repositorioIncidencias.buscarIncidenciaId(i.getId());
        Incidencia incidencia2 = repositorioIncidencias.buscarIncidenciaId(i.getId());

        incidencia1.aceptar();
        incidencia2.cancelar();

        repositorioIncidencias.actualizarIncidencia(incidencia1);
        repositorioIncidencias.actualizarIncidencia(incidencia2);

    }

}
