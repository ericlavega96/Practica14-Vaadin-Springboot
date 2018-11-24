package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Gerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Rol;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Usuario;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioGerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioRol;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioUsuario;

import java.util.List;


@Service
public class ServicioGerente {

    @Autowired
    private RepositorioGerente repositorioGerente;

    @Autowired
    private RepositorioRol repositorioRol;

    @Autowired
    private RepositorioUsuario repositorioUsuario;



    public void crearGerente(Gerente gerente){

        gerente.getUsuario().getRoles().add(repositorioRol.getOne("ROLE_GERENTE"));
        repositorioUsuario.save(gerente.getUsuario());
        repositorioGerente.save(gerente);

        System.out.println("El gerente ha sido creado satisfactoriamente!");

    }


    public List<Gerente> listaGerentes(int offset, int limit) {
        return repositorioGerente.findAll();
    }

    public long cantidadGerentes() {
        return repositorioGerente.count();
    }

    public void eliminar(Gerente gerente) {
        repositorioGerente.delete(gerente);
    }

    public Gerente findGerenteById(Long id){
        return repositorioGerente.getOne(id);
    }

    public void guardarGerente(Gerente gerente){
        repositorioGerente.save(gerente);
    }
}


