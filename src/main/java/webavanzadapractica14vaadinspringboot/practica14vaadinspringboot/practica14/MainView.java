package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("index")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new Label("Bienvenido a la página principal"));
    }
}
