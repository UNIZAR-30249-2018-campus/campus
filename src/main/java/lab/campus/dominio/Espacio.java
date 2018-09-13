package lab.campus.dominio;

import javax.persistence.Embedded;
import javax.persistence.OneToOne;

import javax.persistence.*;

public class Espacio {

    private String id;
    @OneToOne
    private String edificio;
    private String nombre;
    @Embedded
    private Localizacion localizacion;
    private int plantaEspacio;


    public Espacio(){}
    public Espacio(String edificio, String tipoDeUso, Localizacion localizacion,int planta,String id){
        this.edificio=edificio;
        this.nombre=tipoDeUso;
        this.localizacion=localizacion;
        this.plantaEspacio=planta;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPlantaEspacio() {
        return plantaEspacio;
    }

    public String getEdificio() {
        return edificio;
    }
}
