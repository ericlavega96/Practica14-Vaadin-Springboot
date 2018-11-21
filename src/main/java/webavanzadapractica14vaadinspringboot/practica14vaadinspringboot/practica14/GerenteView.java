package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Gerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Usuario;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.ui.MenuUI;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioGerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioUsuario;

@Route("gerente")
public class GerenteView extends VerticalLayout {

    MenuUI menuUI;
    FormLayout formLayout;
    Label gerentLbl;
    Label infoGerenteLbl;
    Label infoUsuarioLbl;
    TextField nombres;
    TextField apellidos;
    TextField correo;
    TextField username;
    PasswordField password;
    PasswordField repeatPassword;
    ServicioGerente servicioGerente;
    VerticalLayout gerenteFormLayout;
    HorizontalLayout buttonsLayout;

    Button btnCrear;
    Button btnCancelar;
    Binder<Gerente> gerenteBinder;
    Binder<Usuario> usuarioBinder;

    public GerenteView(@Autowired ServicioGerente servicioGerente) {

        this.servicioGerente = servicioGerente;

        menuUI = new MenuUI();
        infoGerenteLbl = new Label("Información personal");
        infoUsuarioLbl = new Label("Información de usuario");
        gerentLbl = new Label("Gerentes");
        nombres = new TextField("Nombres");
        apellidos = new TextField("Apellidos");
        correo = new TextField("Correo");
        username = new TextField("Nombre de usuario");
        password = new PasswordField("Contraseña");
        repeatPassword = new PasswordField("Repetir contraseña");
        gerenteFormLayout = new VerticalLayout();
        buttonsLayout = new HorizontalLayout();

        gerenteBinder = new Binder<Gerente>();
        usuarioBinder = new Binder<Usuario>();

        btnCrear = new Button("Crear",event -> {
        try {
            Gerente gerente = new Gerente();
            Usuario usuario = new Usuario();

            usuarioBinder.writeBean(usuario);
            gerenteBinder.writeBean(gerente);
            System.out.println(usuario.toString());
            System.out.println(gerente.toString());
            gerente.setUsuario(usuario);
            System.out.println(gerente.toString());
            servicioGerente.crearGerente(gerente);
            Notification.show("El gerente ha sido creado con éxito!");

        } catch (ValidationException e) {
            Notification.show("Error al crear el gerente");
        }

        });

        btnCancelar = new Button("Cancelar",event -> {
            nombres.setValue("");
            correo.setValue("");
        });

        gerenteBinder.forField(nombres).asRequired("Por favor, inserte los nombres")
        .bind(Gerente::getNombres,Gerente::setNombres);
        gerenteBinder.forField(apellidos).asRequired("Por favor, inserte los apellidos")
                .bind(Gerente::getApellidos,Gerente::setApellidos);
        gerenteBinder.forField(correo).asRequired("Por favor, inserte un correo")
                .withValidator(new EmailValidator("Por favor,inserte un correo válido"))
                .bind(Gerente::getCorreo,Gerente::setCorreo);

        usuarioBinder.forField(username).asRequired("Por favor, inserte un nombre de usuario")
                .bind(Usuario::getUsername,Usuario::setUsername);

        usuarioBinder.forField(password).asRequired("Por favor, inserte una contraseña")
                .bind(Usuario::getPassword,Usuario::setPassword)
                .validate(password.equals(repeatPassword.getValue()));


        formLayout = new FormLayout();

        formLayout.add(infoGerenteLbl,nombres,apellidos,correo,infoUsuarioLbl,username,password,repeatPassword);
        buttonsLayout.add(btnCrear,btnCancelar);
        gerenteFormLayout.add(formLayout,buttonsLayout);
        add(menuUI,gerentLbl,gerenteFormLayout);
        setSizeFull();
    }
}

