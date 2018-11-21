package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.calendar.CalendarItemTheme;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Evento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioEvento;

import java.util.Date;
import java.util.List;

public class ServicioEvento {

    @Autowired
    private RepositorioEvento repositorioEvento;

    public List<Evento> listarEventos() {
        return repositorioEvento.findAll();
    }

    public Evento registrar(String nombre, Date fecha) {
        return repositorioEvento.save(new Evento(nombre, fecha));
    }
}
