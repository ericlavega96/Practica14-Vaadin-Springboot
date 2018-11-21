package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.ui.MenuUI;

@Route("index")
public class MainView extends VerticalLayout {

    public MainView() {
        MenuUI menuUI = new MenuUI();
        Label label = new Label("Bienvenido a la página principal");
        /*HorizontalLayout menu = new HorizontalLayout();
        menu.setSizeFull();

        Button gerentesView = new Button("GerenteView", e -> getUI().get().navigate("GerenteView"));
        gerentesView.setIcon(new Icon(VaadinIcon.USER));
        Button view2 = new Button("View 2", e -> getUI().get().navigate(""));
        menu.add(gerentesView,view2);*/
        add(menuUI,label);

    }
}
