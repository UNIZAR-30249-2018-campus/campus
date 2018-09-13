package lab.campus.dominio;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;

@Entity
@Table(name="incidencia")
public class Incidencia extends Entidad {

    private String descripcion;
    private String nombreIncidencia;
    private Boolean exterior;
    private Date horaFechaCreada;
    private String idespacio;
    private String edificioDeIncidencia;
    @Embedded
    private Localizacion localizacion;

    @Embedded
    private Estado estado;

    @Version
    private long version;

    public Incidencia(){}
    //Crear nueva incidencia
    public Incidencia(String descripcion, String nombre, Boolean exterior,
                      Date horaFecha, Localizacion localizacion, String idespacio,String edificioDeIncidencia, long version){
        super();
        this.descripcion=descripcion;
        this.nombreIncidencia = nombre;
        this.exterior=exterior;
        this.horaFechaCreada =horaFecha;
        this.localizacion=localizacion;
        this.estado=new Estado("Pendiente");
        this.edificioDeIncidencia=edificioDeIncidencia;
        this.idespacio = idespacio;
        this.version = version;
    }
    public void aceptar() {
        //Estado a aceptado.
        assert(this.estado.equals(new Estado("Pendiente")));
        this.estado = new Estado("Aceptada");
    }

    public void cancelar() {
        assert(this.estado.equals(new Estado("Pendiente")));
        this.estado = new Estado("Cancelada");
    }

    public void completar() {
        assert (this.estado.equals(new Estado("Aceptada")));
        this.estado = new Estado("Completada");
        this.estado.finalizar(new Date());
    }

    public Estado getEstado() {
        return estado;
    }

    public String getId(){return id;}

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreIncidencia() {
        return nombreIncidencia;
    }

    public Date getHoraFechaCreada() {
        return horaFechaCreada;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public String getEdificioDeIncidencia() {
        return edificioDeIncidencia;
    }

    public String getIdespacio() {
        return idespacio;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incidencia that = (Incidencia) o;
        return this.id.equals(that.id);
    }
}