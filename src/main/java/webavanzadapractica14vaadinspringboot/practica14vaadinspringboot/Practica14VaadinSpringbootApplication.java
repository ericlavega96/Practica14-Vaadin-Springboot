package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.MailSender;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioEvento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioRol;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioUsuario;

import java.io.IOException;

@SpringBootApplication
public class Practica14VaadinSpringbootApplication {



    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = SpringApplication.run(Practica14VaadinSpringbootApplication.class, args);

        ServicioUsuario servicioUsuario = (ServicioUsuario) applicationContext.getBean("servicioUsuario");
        ServicioEvento servicioEvento = (ServicioEvento) applicationContext.getBean("servicioEvento");
        ServicioRol servicioRol = (ServicioRol) applicationContext.getBean("servicioRol");
        servicioRol.insertarRoles();
        servicioUsuario.crearUsuarioAdmin();
        servicioEvento.crearPrueba();
        new MailSender().sendNotificacionEvento();
    }
}
