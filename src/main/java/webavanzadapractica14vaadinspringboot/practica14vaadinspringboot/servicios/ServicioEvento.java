package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.calendar.CalendarItemTheme;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Evento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioEvento;

import java.util.Date;
import java.util.List;

@Service
public class ServicioEvento {

    @Autowired
    private RepositorioEvento repositorioEvento;

    public List<Evento> listarEventos() {
        return repositorioEvento.findAll();
    }

    public Evento registrar(String nombre, Date fecha) {
        return repositorioEvento.save(new Evento(nombre, fecha));
    }

    public void crearPrueba(){
        if(repositorioEvento.findAll().isEmpty()) {
            Evento prueba = new Evento();
            prueba.setNombre("prueba");
            prueba.setFecha(new Date());
            repositorioEvento.save(prueba);
        }
    }

    public Evento crear(String nombre, Date fecha){
        try {
            Evento nuevo = new Evento();
            nuevo.setNombre(nombre);
            nuevo.setFecha(fecha);
            repositorioEvento.save(nuevo);
            return nuevo;
        }catch (Exception e){
            System.out.println("    - Error al crear el evento: " + e);
            return null;
        }

    }

    public Evento modificar(Evento evento){
        try{
            Evento modificado = repositorioEvento.findById(evento.getIdEvento()).get();
            modificado.setNombre(evento.getNombre());
            modificado.setFecha(evento.getFecha());
            modificado.setEnviado(evento.isEnviado());
            repositorioEvento.save(modificado);
            return repositorioEvento.findById(modificado.getIdEvento()).get();
        } catch (Exception e){
            System.out.println("    - Error al modificar el evento: " + e);
            return null;
        }
    }

    public boolean eliminar(Evento evento){
        try{
            Evento eliminar = repositorioEvento.findById(evento.getIdEvento()).get();
            repositorioEvento.delete(eliminar);
            return true;
        } catch (Exception e){
            System.out.println("    - Error al eliminar el evento: " + e);
            return false;
        }
    }

    public Evento findById(long id){
        try{
            return repositorioEvento.findById(id).get();
        } catch(Exception e){
            System.out.println("    - Error al modificar el evento: " + e);
            return null;
        }
    }
}
