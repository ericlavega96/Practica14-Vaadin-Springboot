package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.VaadinSession;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.LogIn;



public class MenuUI extends VerticalLayout implements BeforeEnterObserver {


    public MenuUI () {

        HorizontalLayout menuLayout = new HorizontalLayout();

        Button calendarioItem = new Button("Calendario", event -> {
            VaadinSession.getCurrent().close();
            UI.getCurrent().navigate("calendario");

        });

        calendarioItem.setIcon(new Icon(VaadinIcon.CALENDAR));

        Button logoutItem = new Button("Salir", event -> {
            VaadinSession.getCurrent().close();
            UI.getCurrent().navigate("login");

        });

        logoutItem.setIcon(new Icon(VaadinIcon.EXIT_O));

        menuLayout.add(calendarioItem,logoutItem);
        menuLayout.setSizeFull();
        menuLayout.setAlignItems(Alignment.CENTER);
        menuLayout.setSpacing(true);

        add(menuLayout);
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if(VaadinSession.getCurrent().getAttribute("username") == null){
            event.rerouteTo("login");
        }
    }
}
