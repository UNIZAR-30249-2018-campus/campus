package lab.campus.infraestructura;

import lab.campus.dominio.Espacio;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EspacioPostGis {

    @Id
    private String layer;
    private String id_utc;
    private String id_centro;
    private Double tipo_de_us;
    private String id_edifici;

    public EspacioPostGis(){}
    public EspacioPostGis(String layer, String id_utc, String id_centro, Double tipo_de_us, String id_edifici) {
        this.layer = layer;
        this.id_utc = id_utc;
        this.id_centro = id_centro;
        this.tipo_de_us = tipo_de_us;
        this.id_edifici = id_edifici;

    }

    public Espacio extraeEspacio(){
        String edificio="";
        switch (this.id_edifici){
            case "CRE.1200.":
                edificio = "Ada Byron";
                break;
            case "CRE.1201.":
                edificio = "Betancourt";
                break;
            case "CRE.1065.":
                edificio = "Torres Quevedo";
                break;
        }
        String sSubCadena = id_utc.substring(0,2);
        int planta=0;
        switch (sSubCadena){
            case "S1":
                planta=-1;
                break;
            case "00":
                planta=0;
                break;
            case "01":
                planta=1;
                break;
            case "02":
                planta=2;
                break;
            case "03":
                planta=3;
                break;
            case "04":
                planta=4;
                break;
        }
        return new Espacio(edificio,id_centro,null,planta,id_utc);
    }


}

