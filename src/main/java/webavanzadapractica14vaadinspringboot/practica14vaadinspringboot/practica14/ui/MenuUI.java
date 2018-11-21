package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinRequest;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.LogIn;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.practica14.MainView;

import java.awt.font.LayoutPath;

public class MenuUI extends VerticalLayout {

        public MenuUI () {

        Button gerentesItem = new Button("Gerentes", event -> {
            getUI().get().navigate("gerente");
        });
        gerentesItem.setIcon(new Icon(VaadinIcon.USERS));

        Button logoutItem = new Button("Salir", event -> {
            getUI().get().navigate("login");
        });
        logoutItem.setIcon(new Icon(VaadinIcon.EXIT_O));

        HorizontalLayout menuLayout = new HorizontalLayout(gerentesItem,logoutItem);
        menuLayout.setSizeFull();
        menuLayout.setAlignItems(Alignment.CENTER);
        menuLayout.setSpacing(true);
        add(menuLayout);
    }
}
