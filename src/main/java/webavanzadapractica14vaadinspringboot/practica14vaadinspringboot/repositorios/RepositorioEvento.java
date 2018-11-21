package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Evento;

public interface RepositorioEvento extends JpaRepository<Evento,Long> {
}
