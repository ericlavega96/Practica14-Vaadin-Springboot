package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioEvento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioUsuario;

@SpringBootApplication
public class Practica14VaadinSpringbootApplication {



    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Practica14VaadinSpringbootApplication.class, args);

        ServicioUsuario servicioUsuario = (ServicioUsuario) applicationContext.getBean("servicioUsuario");
        servicioUsuario.crearUsuarioAdmin();

    }
}
