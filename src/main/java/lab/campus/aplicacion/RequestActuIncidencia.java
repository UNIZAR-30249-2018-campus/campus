package lab.campus.aplicacion;

public class RequestActuIncidencia {

    private String id;
    private String estado;

    public RequestActuIncidencia(){}
    public RequestActuIncidencia(String id,String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }
}