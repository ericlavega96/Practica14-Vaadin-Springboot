package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rol implements Serializable {
    @Id
    private String rol;

    public Rol() {
    }

    public Rol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
