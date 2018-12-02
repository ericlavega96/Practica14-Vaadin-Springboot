package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Gerente;

public interface RepositorioGerente extends JpaRepository<Gerente,Long> {

    Gerente findByUsuario_Username(String username);
}