package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioUsuario;


@Route("login")
public class LogIn extends VerticalLayout {
         H1 h1;
        FormLayout formLayout;
        TextField username;
        PasswordField password;
        Button logInBtn;

        @Autowired
        ServicioUsuario servicioUsuario;

        public LogIn() {
            formLayout = new FormLayout();
            h1 = new H1("Iniciar Sesión");

            username = new TextField("Nombre de Usuario");
            username.setRequired(true);
            username.setErrorMessage("Por favor, inserte un nombre de usuario");

            password = new PasswordField("Contraseña");
            password.setRequired(true);
            password.setErrorMessage("Por favor, inserte la contraseña");



            logInBtn = new Button("Iniciar Sesión",event -> {
                if(servicioUsuario.validarUsuario(username.getValue(),password.getValue())){
                    VaadinSession.getCurrent().setAttribute("username", username.getValue());
                    UI.getCurrent().navigate("index");
                    System.out.println("El usuario se ha logueado satisfactoriamente");
                }else{
                    Div content = new Div();
                    content.getStyle().set("color","red");
                    content.setText("ERROR: Las credenciales insertadas no son válidas");

                    Notification notification = new Notification(content);
                    notification.setDuration(3000);
                    notification.open();

                }
            });

            formLayout.add(username,password,logInBtn);
            setAlignItems(Alignment.CENTER);
            add(h1,formLayout);
        }
}


