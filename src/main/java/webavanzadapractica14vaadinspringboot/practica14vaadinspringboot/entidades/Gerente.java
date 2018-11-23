package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Gerente implements Serializable {
    @Id
    @GeneratedValue
    private long idGerente;

    @NotNull
    private String nombres;

    @NotNull
    private String apellidos;

    @NotNull
    @Column(unique = true)
    private String correo;


    @OneToOne
    private Usuario usuario;

    public Gerente() {
    }

    public Gerente(String nombres, String apellidos, String correo, Usuario usuario) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.usuario = usuario;
    }

    public long getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(long idGerente) {
        this.idGerente = idGerente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Gerente{" +
                "id=" + idGerente +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                 (usuario == null ?", No hay username":usuario.getUsername())+ '\''+
                (usuario == null ?", No hay password":usuario.getPassword())+
                '}';
    }
}
