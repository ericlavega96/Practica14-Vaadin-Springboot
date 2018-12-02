package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Gerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Usuario;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.ui.MenuUI;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioGerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioUsuario;

@Route("perfil")
public class PerfilView extends VerticalLayout {

    FormLayout formLayout;
    TextField nombresTextField;
    TextField apellidosTextField;
    TextField correoTextField;
    HorizontalLayout buttonsLayout;
    Button editarBtn;
    Button cancelarBtn;
    Button guardarBtn;
    Gerente gerenteActual;
    ServicioGerente servicioGerente;
    ServicioUsuario servicioUsuario;
    MenuUI menuUI;
    Binder<Gerente> gerenteBinder;

    public PerfilView(@Autowired ServicioUsuario servicioUsuario, @Autowired ServicioGerente servicioGerente) {

        menuUI = new MenuUI();
        this.servicioUsuario = servicioUsuario;
        this.servicioGerente = servicioGerente;

        if(VaadinSession.getCurrent().getAttribute("username") != null){
            Usuario logUser = servicioUsuario.getUserByUsername(VaadinSession.getCurrent().getAttribute("username").toString());
            gerenteActual = servicioGerente.findByUsername(VaadinSession.getCurrent().getAttribute("username").toString());


            Button logoutItem = new Button("Salir", event -> {
                VaadinSession.getCurrent().close();
                UI.getCurrent().navigate("login");

            });

            logoutItem.setIcon(new Icon(VaadinIcon.EXIT_O));

            if(servicioUsuario.isAdmin(logUser)){
                Button gerentesItem = new Button("Gerentes", event -> {
                    UI.getCurrent().navigate("gerente");
                });
                gerentesItem.setIcon(new Icon(VaadinIcon.USERS));
                HorizontalLayout h1 = (HorizontalLayout) menuUI.getComponentAt(0);
                h1.add(gerentesItem,logoutItem);
            }else{
                Button perfilItem = new Button("Perfil", event -> {
                    UI.getCurrent().navigate("perfil");

                });
                perfilItem.setIcon(VaadinIcon.USER.create());
                HorizontalLayout h1 = (HorizontalLayout) menuUI.getComponentAt(0);
                h1.add(perfilItem,logoutItem);
            }
        }

        formLayout = new FormLayout();
        nombresTextField = new TextField("Nombres");
        apellidosTextField = new TextField("Apellidos");
        correoTextField = new TextField("Correo");
        gerenteBinder = new Binder<>();

       if(gerenteActual != null){
            nombresTextField.setValue(gerenteActual.getNombres());
            apellidosTextField.setValue(gerenteActual.getApellidos());
            correoTextField.setValue(gerenteActual.getCorreo());
        }

        editarBtn = new Button("Editar",event -> {
            nombresTextField.setEnabled(true);
            apellidosTextField.setEnabled(true);
            correoTextField.setEnabled(true);
            guardarBtn.setVisible(true);
            cancelarBtn.setVisible(true);
            editarBtn.setVisible(false);
        });
        cancelarBtn = new Button("Cancelar",event -> {
            nombresTextField.setEnabled(false);
            apellidosTextField.setEnabled(false);
            correoTextField.setEnabled(false);
            editarBtn.setVisible(true);
            editarBtn.setEnabled(true);
            guardarBtn.setVisible(false);
            cancelarBtn.setVisible(false);
        });

        guardarBtn = new Button("Guardar",event -> {

            try {
                gerenteActual.setNombres(nombresTextField.getValue());
                gerenteActual.setApellidos(apellidosTextField.getValue());
                gerenteActual.setCorreo(correoTextField.getValue());

                servicioGerente.guardarGerente(gerenteActual);

                Notification.show("El gerente ha sido actualizado con éxito");

                nombresTextField.setValue(gerenteActual.getNombres());
                apellidosTextField.setValue(gerenteActual.getApellidos());
                correoTextField.setValue(gerenteActual.getCorreo());

                nombresTextField.setEnabled(false);
                apellidosTextField.setEnabled(false);
                correoTextField.setEnabled(false);
                editarBtn.setVisible(true);
                editarBtn.setEnabled(true);
                guardarBtn.setVisible(false);
                cancelarBtn.setVisible(false);

            }catch (Exception e){
                Notification.show("Error al editar el gerente");
            }
        });
        guardarBtn.setVisible(false);
        cancelarBtn.setVisible(false);

        gerenteBinder.forField(nombresTextField)
                .asRequired("Por favor, inserte un nombre")
                .bind(Gerente::getNombres,Gerente::setNombres);
        gerenteBinder.forField(apellidosTextField)
                .asRequired("Por favor,inserte un apellido")
                .bind(Gerente::getApellidos,Gerente::setApellidos);
        gerenteBinder.forField(correoTextField)
                .asRequired("Por favor, inserte un correo")
                .withValidator(correo-> !servicioGerente.emailExists(correo),"El correo insertado ya existe")
                .bind(Gerente::getCorreo,Gerente::setCorreo);

        formLayout.add(nombresTextField,apellidosTextField);
        buttonsLayout = new HorizontalLayout(editarBtn,guardarBtn,cancelarBtn);
        nombresTextField.setEnabled(false);
        apellidosTextField.setEnabled(false);
        correoTextField.setEnabled(false);
        formLayout.add(nombresTextField,apellidosTextField,correoTextField);
        add(menuUI,formLayout,buttonsLayout);
    }

}
