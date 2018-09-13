package lab.campus.infraestructura;

import lab.campus.aplicacion.CuentaAdministrador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RepositorioAdministrador extends CrudRepository<CuentaAdministrador,Integer> {
    Optional<CuentaAdministrador> findByUsuario(String usuario);
    @Transactional
    void deleteByUsuario(String usuario);
}
