package lab.campus.aplicacion;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "administrador")
public class CuentaAdministrador {

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=2, allocationSize=12)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
    private int id;

    private String pass;
    private String usuario;

    public CuentaAdministrador(){}
    public CuentaAdministrador(String usuario, String pass) throws NoSuchAlgorithmException {
        this.usuario = usuario;
        this.pass = pass;
    }
    public CuentaAdministrador(FormularioAdministrador cuenta) throws NoSuchAlgorithmException {
        this.usuario = cuenta.nombreUsuario;
        this.pass=cuenta.password;

    }

    public boolean loginCorrecto(String pass){
        return this.pass.equals(pass);
    }

    @Override
    public String toString() {
        return "CuentaAdministrador{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
