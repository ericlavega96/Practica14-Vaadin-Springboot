package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Gerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Rol;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Usuario;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioGerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioRol;


@Service
public class ServicioGerente {

    @Autowired
    private RepositorioGerente repositorioGerente;

    @Autowired
    private RepositorioRol repositorioRol;

    public void crearGerente(Gerente gerente){

        Rol gerenteRol = repositorioRol.getOne("ROLE_GERENTE");
        if(gerenteRol.equals(null)){
            gerenteRol = new Rol("ROLE_GERENTE");
        }
        gerente.getUsuario().getRoles().add(gerenteRol);
        repositorioGerente.save(gerente);
        System.out.println("El gerente ha sido creado satisfactoriamente!");

    }


}
