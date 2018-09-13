package lab.campus.infraestructura;

import lab.campus.dominio.Espacio;
import lab.campus.dominio.Localizacion;
import lab.campus.dominio.RepositorioEspacios;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class RepoEspacioDominio implements RepositorioEspacios {

    @Autowired
    RepoEspacioSpring repoEspacioSpring;

    @Override
    public Optional<Espacio> ObtenerEspacioPorLoca(Localizacion localizacion) {

        Optional<Espacio> resultado;
        EspacioPostGis resultadoQuery = null;
        switch (localizacion.getPlanta()){
            case 0:
                resultadoQuery = repoEspacioSpring.findByCoordinatesPlanta0(localizacion.getLongitud(),
                        localizacion.getLatitud());
                break;
            case 1:
                resultadoQuery = repoEspacioSpring.findByCoordinatesPlanta1(localizacion.getLongitud(),
                        localizacion.getLatitud());
                break;
            case 2:
                resultadoQuery = repoEspacioSpring.findByCoordinatesPlanta2(localizacion.getLongitud(),
                        localizacion.getLatitud());
                break;
            case 3:
                resultadoQuery = repoEspacioSpring.findByCoordinatesPlanta3(localizacion.getLongitud(),
                        localizacion.getLatitud());
                break;
            case 4:
                resultadoQuery = repoEspacioSpring.findByCoordinatesPlanta4(localizacion.getLongitud(),
                        localizacion.getLatitud());
                break;
            case -1:
                resultadoQuery = repoEspacioSpring.findByCoordinatesPlantaSot(localizacion.getLongitud(),
                        localizacion.getLatitud());
                break;
        }
        if(resultadoQuery == null){
            resultado = Optional.empty();
        }else{
            resultado = Optional.of(resultadoQuery.extraeEspacio());
        }
        return resultado;
    }
}

