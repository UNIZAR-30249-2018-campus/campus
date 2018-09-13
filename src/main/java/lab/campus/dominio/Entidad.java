package lab.campus.dominio;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class Entidad {

    @Id
    protected String id;

    public Entidad(){
        id = UUID.randomUUID().toString();
    }

}

