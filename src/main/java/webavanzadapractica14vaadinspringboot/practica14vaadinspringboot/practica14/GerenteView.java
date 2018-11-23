package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
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
    ServicioUsuario servicioUsuario;

    VerticalLayout gerenteFormLayout;
    VerticalLayout infoPersonalLayout;
    VerticalLayout infoUsuarioLayout;
    HorizontalLayout h1;
    HorizontalLayout buttonsLayout;

    Button btnCrear;
    Button btnCancelar;
    Binder<Gerente> gerenteBinder;
    Binder<Usuario> usuarioBinder;

    public GerenteView(@Autowired ServicioGerente servicioGerente,@Autowired ServicioUsuario servicioUsuario) {

        this.menuUI = new MenuUI();
        this.servicioGerente = servicioGerente;
        this.servicioUsuario = servicioUsuario;


        if(VaadinSession.getCurrent().getAttribute("username") != null){
            Usuario logUser = servicioUsuario.getUserByUsername(VaadinSession.getCurrent().getAttribute("username").toString());
            System.out.println(logUser.toString());

            if(servicioUsuario.isAdmin(logUser)){
                Button gerentesItem = new Button("Gerentes", event -> {
                    UI.getCurrent().navigate("gerente");
                });
                gerentesItem.setIcon(new Icon(VaadinIcon.USERS));
                HorizontalLayout h1 = (HorizontalLayout) menuUI.getComponentAt(0);
                h1.add(gerentesItem);
            }
        }

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

            System.out.println(gerente.toString());

            gerente.setUsuario(usuario);
            servicioGerente.crearGerente(gerente);

            Notification notification = new Notification();
            notification.setText("El gerente ha sido creado con éxito");

            if(!notification.isOpened())
                UI.getCurrent().getPage().reload();

        } catch (ValidationException e) {
            Notification.show("Error al crear el gerente");
        }

        });

        btnCancelar = new Button("Cancelar",event -> {
            UI.getCurrent().getPage().reload();
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
                .bind(Usuario::getPassword,Usuario::setPassword);

        usuarioBinder.forField(repeatPassword)
                .asRequired("Por favor, inserte la contraseña repetida")
                .withValidator( rpassword -> password.getValue().equals(rpassword),"Las contraseñas deben de ser idénticas")
                .bind(Usuario::getPassword,Usuario::setPassword);


        formLayout = new FormLayout();
        h1 = new HorizontalLayout();

        infoPersonalLayout = new VerticalLayout();
        infoUsuarioLayout = new VerticalLayout();

        infoPersonalLayout.add(infoGerenteLbl,nombres,apellidos,correo);
        infoUsuarioLayout.add(infoUsuarioLbl,username,password,repeatPassword);
        h1.add(infoPersonalLayout,infoUsuarioLayout);
        buttonsLayout.add(btnCrear,btnCancelar);
        formLayout.add(h1);
        gerenteFormLayout.add(formLayout,buttonsLayout);
        add(menuUI,gerentLbl,gerenteFormLayout);
        setSizeFull();
    }
}

