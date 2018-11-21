package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.calendar.CalendarComponent;
import org.vaadin.calendar.data.AbstractCalendarDataProvider;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Evento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioEvento;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Route("calendario")
public class Calendario extends VerticalLayout {
    public static CalendarComponent<Evento> calendar= new CalendarComponent<Evento>()
            .withItemDateGenerator(Evento::getFecha)
            .withItemLabelGenerator(Evento::getNombre)
            .withItemThemeGenerator(Evento::getTema);

    @Autowired
    public static ServicioEvento servicioEvento;

    @Autowired
    public Calendario() {


        setAlignItems(Alignment.CENTER);

        HorizontalLayout layoutBotones = new HorizontalLayout();
        layoutBotones.setSpacing(true);


        H1 titulo = new H1("Sistema de Calendarios EA");
        H2 subtitulo = new H2("Calendario");

        setAlignItems(Alignment.CENTER);

        add(titulo, subtitulo, layoutBotones, calendar);


        Button add = new Button("Agregar");
        add.setIcon(new Icon(VaadinIcon.PLUS));
        add.getElement().setAttribute("theme", "primary");
    }

    private void abrirPantalla(VerticalLayout form) {
        Dialog vistaPantalla = new Dialog();
        vistaPantalla.add(form);

        vistaPantalla.open();
    }

    private void configuraBotonPantalla(Button boton, VerticalLayout formulario) {
        boton.addClickListener((e) -> {
            abrirPantalla(formulario);
        });
    }
}

@SpringComponent
@UIScope
class CustomDataProvider extends AbstractCalendarDataProvider<Evento> {
    @Override
    public Collection<Evento> getItems(Date fromDate, Date toDate) {
        List<Evento> eventos = Calendario.servicioEvento.listarEventos();
        return eventos;
    }
}

