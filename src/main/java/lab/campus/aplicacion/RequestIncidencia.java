package lab.campus.aplicacion;

public class RequestIncidencia {
    private String descripcion;
    private String nombre;
    private Double latitud;
    private Double longitud;
    private int planta;

    public RequestIncidencia(){}
    public RequestIncidencia(String descripcion,String nombre, Double latitud, Double longitud, int planta) {
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.planta = planta;
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public int getPlanta() {
        return planta;
    }

    public String getNombre() {
        return nombre;
    }

}