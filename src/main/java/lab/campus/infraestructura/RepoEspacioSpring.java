package lab.campus.infraestructura;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoEspacioSpring extends CrudRepository<EspacioPostGis,String> {

    @Query(value = "(SELECT * FROM ada_planta_0 a WHERE ST_Contains(a.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM betan_planta_0 b WHERE ST_Contains(b.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM torres_planta_0 t WHERE ST_Contains(t.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)",
            nativeQuery = true)
    EspacioPostGis findByCoordinatesPlanta0(Double lon,
                                            Double lat);

    @Query(value = "(SELECT * FROM ada_planta_1 a WHERE ST_Contains(a.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM betan_planta_1 b WHERE ST_Contains(b.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM torres_planta_1 t WHERE ST_Contains(t.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)",
            nativeQuery = true)
    EspacioPostGis findByCoordinatesPlanta1(Double lon,
                                            Double lat);

    @Query(value = "(SELECT * FROM ada_planta_2 a WHERE ST_Contains(a.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM betan_planta_2 b WHERE ST_Contains(b.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM torres_planta_2 t WHERE ST_Contains(t.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)",
            nativeQuery = true)
    EspacioPostGis findByCoordinatesPlanta2(Double lon,
                                            Double lat);


    @Query(value = "(SELECT * FROM ada_planta_3 a WHERE ST_Contains(a.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM betan_planta_3 b WHERE ST_Contains(b.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM torres_planta_3 t WHERE ST_Contains(t.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)",
            nativeQuery = true)
    EspacioPostGis findByCoordinatesPlanta3(Double lon,
                                            Double lat);

    @Query(value = "(SELECT * FROM ada_planta_4 a WHERE ST_Contains(a.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)",
            nativeQuery = true)
    EspacioPostGis findByCoordinatesPlanta4(Double lon,
                                            Double lat);

    @Query(value = "(SELECT * FROM ada_sotano a WHERE ST_Contains(a.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM betan_sotano b WHERE ST_Contains(b.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)\n" +
            "UNION\n" +
            "(SELECT * FROM torres_sotano t WHERE ST_Contains(t.the_geom, ST_SetSRID(ST_Point(?1,?2),25830)) LIMIT 1)",
            nativeQuery = true)
    EspacioPostGis findByCoordinatesPlantaSot(Double lon,
                                              Double lat);

}
