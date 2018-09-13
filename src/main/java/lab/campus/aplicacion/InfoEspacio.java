package lab.campus.aplicacion;

public class InfoEspacio {

    private int planta;
    private String nombre;
    private String edificio;
    private boolean exterior;
    private String idE;

    public InfoEspacio(int planta, String nombre, String edificio, boolean exterior,String idE) {
        this.planta = planta;
        this.nombre = nombre;
        this.edificio = edificio;
        this.exterior = exterior;
        this.idE=idE;
    }

    public int getPlanta() {
        return planta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdificio() {
        return edificio;
    }

    public boolean isExterior() {
        return exterior;
    }

    public String getIdE(){return idE;}
}
