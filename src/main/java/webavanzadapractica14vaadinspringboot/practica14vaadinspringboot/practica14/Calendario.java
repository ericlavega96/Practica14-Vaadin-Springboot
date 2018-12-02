package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.calendar.CalendarComponent;
import org.vaadin.calendar.data.AbstractCalendarDataProvider;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Evento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Usuario;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.ui.MenuUI;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioEvento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioUsuario;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Route("calendario")
public class Calendario extends VerticalLayout {

    //@Autowired
    //private static ServicioEvento servicioEvento;
    private ServicioEvento servicioEvento;

    private ServicioUsuario servicioUsuario;

    private MenuUI menuUI;


    public Calendario(@Autowired ServicioUsuario servicioUsuario,@Autowired ServicioEvento servicioEvento) {
        this.servicioEvento = servicioEvento;
        this.servicioUsuario = servicioUsuario;
        this.menuUI = new MenuUI();


        if(VaadinSession.getCurrent().getAttribute("username") != null){
            Usuario logUser = servicioUsuario.getUserByUsername(VaadinSession.getCurrent().getAttribute("username").toString());

            if(servicioUsuario.isAdmin(logUser)){
                Button gerentesItem = new Button("Gerentes", event -> {
                    UI.getCurrent().navigate("gerente");
                });
                gerentesItem.setIcon(new Icon(VaadinIcon.USERS));
                HorizontalLayout h1 = (HorizontalLayout) menuUI.getComponentAt(0);
                h1.add(gerentesItem);
            }else{
                Button perfilItem = new Button("Perfil", event -> {
                    UI.getCurrent().navigate("perfil");

                });
                perfilItem.setIcon(VaadinIcon.USER.create());
                HorizontalLayout h1 = (HorizontalLayout) menuUI.getComponentAt(0);
                h1.add(perfilItem);
            }
        }

        CalendarComponent<Evento> calendar= new CalendarComponent<Evento>()
                .withItemDateGenerator(Evento::getFecha)
                .withItemLabelGenerator(Evento::getNombre)
                .withItemThemeGenerator(Evento::getTema);
        HorizontalLayout botones = new HorizontalLayout();

        Button nuevoEvento = new Button("Añadir Evento", new Icon(VaadinIcon.PLUS));

        Button actualizar = new Button("Actualizar", new Icon(VaadinIcon.REFRESH));

        botones.add(nuevoEvento,actualizar);
        actualizar.addClickListener(evt -> {
            try{
                calendar.setItems(servicioEvento.listarEventos());
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        Dialog nuevoEventoDialogo = new Dialog();

        TextField nombreField = new TextField(" Nombre del evento: ");
        DatePicker fechaPicker = new DatePicker(" Fecha del evento: ");

        Button crearBtn = new Button("Crear");
        crearBtn.addClickListener(ent ->{
            try {
                servicioEvento.crear(nombreField.getValue(), Date.from(fechaPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                calendar.setItems(servicioEvento.listarEventos());
                nombreField.clear();
                fechaPicker.clear();
                nuevoEventoDialogo.close();
            }catch (Exception e){
                System.out.println("    Error al crear el evento: " + e);

            }
        });

        VerticalLayout nuevoEventoLayout = new VerticalLayout();
        nuevoEventoLayout.add(new H3("Registrar Evento"),nombreField,fechaPicker,crearBtn);

        nuevoEventoDialogo.add(nuevoEventoLayout);

        nuevoEvento.addClickListener(evt -> {
           try{
               if(!nuevoEventoDialogo.isOpened())
                    nuevoEventoDialogo.open();
               else
                   nuevoEventoDialogo.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
        });



        calendar.addEventClickListener(evt ->{
            // Editar
            Dialog editEventoDialogo = new Dialog();
            Label idEditLbl = new Label();
            HorizontalLayout editIdLayout = new HorizontalLayout(new Label("ID: "),idEditLbl);
            TextField nombreEditField = new TextField(" Nombre del evento: ");
            DatePicker fechaEditPicker = new DatePicker(" Fecha del evento: ");

            Button editEventoBtn = new Button("Editar");
            editEventoBtn.addClickListener(evtEdit ->{
                try {
                    Evento editado = servicioEvento.findById(Long.parseLong(idEditLbl.getText()));
                    editado.setNombre(nombreEditField.getValue());
                    editado.setFecha(Date.from(fechaEditPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    servicioEvento.modificar(editado);
                    calendar.setItems(servicioEvento.listarEventos());
                    nombreEditField.clear();
                    fechaEditPicker.clear();
                    editEventoDialogo.close();
                }catch (Exception e){
                    System.out.println("    Error al editar el evento: " + e);
                }
            });
            Button eliminarEventoBtn = new Button("Eliminar");
            eliminarEventoBtn.addClickListener(evtDelete ->{
                servicioEvento.eliminar(evt.getDetail());
                calendar.setItems(servicioEvento.listarEventos());
                nombreEditField.clear();
                fechaEditPicker.clear();
                editEventoDialogo.close();
            });

            HorizontalLayout botonesEditLayout = new HorizontalLayout(editEventoBtn,eliminarEventoBtn);

            VerticalLayout editEventoLayout = new VerticalLayout();
            editEventoLayout.add(new H3("Editar Evento"),editIdLayout, nombreEditField,fechaEditPicker,botonesEditLayout);
            editEventoDialogo.add(editEventoLayout);


            if(!editEventoDialogo.isOpened()){
            idEditLbl.setText(evt.getDetail().getIdEvento().toString());
            nombreEditField.setValue(evt.getDetail().getNombre());
            fechaEditPicker.setValue(evt.getDetail().getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            editEventoDialogo.open();
            }
            else
            editEventoDialogo.close();
        });


        calendar.setItems(servicioEvento.listarEventos());
        setAlignItems(Alignment.CENTER);

        HorizontalLayout layoutHorizontal = new HorizontalLayout();
        layoutHorizontal.setSpacing(true);
        setAlignItems(Alignment.CENTER);

        H1 tituloPoryecto = new H1("Agenda Empresarial - EA");
        H2 tituloCelendario = new H2("Calendario");


        add(menuUI,tituloPoryecto, tituloCelendario, layoutHorizontal,botones, calendar);

    }


}



