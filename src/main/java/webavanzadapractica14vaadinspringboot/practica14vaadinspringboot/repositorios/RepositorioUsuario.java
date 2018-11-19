package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Usuario;

import java.util.List;

public interface RepositorioUsuario extends JpaRepository<Usuario,String> {
    @Override
    List<Usuario> findAll();

    Usuario findByUsernameAndPassword(String username, String password);

    Usuario findByUsername(String username);
}
