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

        HorizontalLayout layoutHorizontal = new HorizontalLayout();
        layoutHorizontal.setSpacing(true);
        setAlignItems(Alignment.CENTER);

        H1 tituloPoryecto = new H1("Agenda Empresarial - EA");
        H2 tituloCelendario = new H2("Calendario");


        add(tituloPoryecto, tituloCelendario, layoutHorizontal, calendar);

    }


}



