package lab.campus.aplicacion;

import lab.campus.dominio.Estado;
import lab.campus.dominio.Incidencia;
import lab.campus.dominio.Localizacion;

import java.util.Date;

public class InfoIncidencia {
    private Localizacion localizacion;
    private String nombre;
    private String descripcion;
    private Date fechaCreada;
    private String estado;
    private String idespacio;
    private String id;
    private String nombreEdificio;

    public InfoIncidencia(Localizacion localizacion, String nombre, String descripcion, Date fechaCreada, Estado estado, String idespacio, String id,
                            String nombreEdificio) {
        this.localizacion = localizacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreada = fechaCreada;
        this.estado = estado.toString();
        this.idespacio=idespacio;
        this.id = id;
        this.nombreEdificio = nombreEdificio;
    }

    public static InfoIncidencia crearInfoDeIncidencia(Incidencia incidencia){
        return new InfoIncidencia(incidencia.getLocalizacion(),incidencia.getNombreIncidencia(),
                incidencia.getDescripcion(),incidencia.getHoraFechaCreada(),incidencia.getEstado(),incidencia.getIdespacio(), incidencia.getId(),
                incidencia.getEdificioDeIncidencia());
    }


}
