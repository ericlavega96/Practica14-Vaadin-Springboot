package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;



@Route("login")
public class LogIn extends VerticalLayout {
        Label label;
        FormLayout formLayout;
        TextField username;
        PasswordField password;
        Button logInBtn;

        public LogIn() {
            formLayout = new FormLayout();
            label = new Label("Iniciar Sesión");
            username = new TextField("Nombre de Usuario");
            password = new PasswordField("Contraseña");
            logInBtn = new Button("Iniciar Sesión");

            formLayout.add(label,username,password,logInBtn);
            add(formLayout);
        }
}


