package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades;

import org.vaadin.calendar.CalendarItemTheme;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Evento implements Serializable {
    @Id
    @GeneratedValue
    private Long idEvento;
    @NotNull
    private String nombre;
    @NotNull
    private Date fecha;
    @NotNull
    private boolean enviado;

    public Evento() {
    }

    public Evento(@NotNull String nombre, @NotNull Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.enviado = false;
    }

    public Long getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Long idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CalendarItemTheme getTema(){
        return CalendarItemTheme.Blue;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }
}
