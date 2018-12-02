package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Rol;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Usuario;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioRol;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.repositorios.RepositorioUsuario;

import java.util.Set;

@Service
public class ServicioUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioRol repositorioRol;

    //private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

    public String encryptPassword(String rawPassword){
        return rawPassword;//bCryptPasswordEncoder.encode(rawPassword);
    }

    public void crearUsuarioAdmin(){
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword("1234");//bCryptPasswordEncoder.encode("1234"));
        admin.getRoles().add(repositorioRol.getOne("ROLE_ADMIN"));
        repositorioUsuario.save(admin);
        System.out.println("El usuario administrador ha sido creado satisfactoriamente");
    }


    public boolean validarUsuario(String username,String password){
        Usuario usuario = repositorioUsuario.findByUsername(username);
        return usuario != null && password.equals(usuario.getPassword());
    }

    public Usuario getUserByUsername(String username){
        return repositorioUsuario.findByUsername(username);
    }

    public boolean isAdmin(Usuario usuario){
        for (Rol rol: usuario.getRoles()) {
            if(rol.getRol().equals("ROLE_ADMIN"))
                return true;
        }
        return false;
    }

}
