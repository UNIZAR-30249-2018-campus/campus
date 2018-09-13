package lab.campus.aplicacion;

public class FormularioAdministrador {
    public String nombreUsuario;
    public String password;

    public FormularioAdministrador(){}

    public FormularioAdministrador(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
}