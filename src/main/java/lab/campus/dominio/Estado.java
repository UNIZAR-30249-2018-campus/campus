package lab.campus.dominio;

import javax.persistence.Embeddable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class Estado {

    /*Posibles estados:
            Pendiente
            Cancelada
            Aceptada
            Completada
         */
    private String nombreEstado;
    private Date horaFechaCompletada;

    public Estado(){}
    public Estado(String nombre){
        assert(nombre.equals("Pendiente") ||
                nombre.equals("Cancelada") ||
                nombre.equals("Aceptada") ||
                nombre.equals("Completada"));
        this.nombreEstado =nombre;
    }

    public void finalizar(Date horaFechaCompletada){
        this.horaFechaCompletada=horaFechaCompletada;
    }
    @Override
    public String toString() {
        return nombreEstado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(nombreEstado, estado.nombreEstado);
    }

}
