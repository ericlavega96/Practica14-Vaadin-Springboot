package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Rol;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioRol;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class ServicioRol {
    @Autowired
    RepositorioRol repositorioRol;

    public void insertarRoles(){
        String[] roles = {"ROLE_ADMIN","ROLE_GERENTE"};
        for (String rol :roles){
            repositorioRol.saveAndFlush(new Rol(rol));
            System.out.println(rol + " creado");
        }
    }
}
