package com.reunion.dominio;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String numeroEmpleado;
    private String nombre;
    private String apellidos;

    @ManyToMany
    private Set<Reunion> reuniones;

    public Set<Reunion> getReuniones() {
        return reuniones;
    }

    public void addReunion(Reunion reunion){
        reuniones.add(reunion);
        if(!reunion.getParticipantes().contains(this)){
            reunion.addPaticipantes(this);
        }

    }

    public Persona(String numeroEmpleado, String nombre, String apellidos) {
        this();
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Persona() {
        reuniones = new HashSet();
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
