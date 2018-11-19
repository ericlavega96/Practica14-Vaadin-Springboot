package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioUsuario;


@Route("login")
public class LogIn extends VerticalLayout{
        Label label;
        FormLayout formLayout;
        TextField username;
        PasswordField password;
        Button logInBtn;

        @Autowired
        ServicioUsuario servicioUsuario;

        public LogIn() {
            formLayout = new FormLayout();
            label = new Label("Iniciar Sesión");
            username = new TextField("Nombre de Usuario");
            password = new PasswordField("Contraseña");
            logInBtn = new Button("Iniciar Sesión",event -> {
                if(servicioUsuario.validarUsuario(username.getValue(),password.getValue())){
                    System.out.println("El usuario se ha logueado satisfactoriamente");
                }else{
                    System.out.println("Las credenciales introducidas no son válidas");
                }
            });
            formLayout.add(label,username,password,logInBtn);
            setAlignItems(Alignment.CENTER);
            add(formLayout);
        }
}


