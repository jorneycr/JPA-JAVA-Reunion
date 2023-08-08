package com.reunion.dominio;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reunion")
public class Reunion {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "asunto")
    private String asunto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sala sala;

    @OneToOne(mappedBy = "reunion")
    private Acta acta;

    @ManyToMany(mappedBy = "reuniones", cascade = CascadeType.ALL)
    private Set<Persona> participantes;

    public Set<Persona> getParticipantes() {
        return participantes;
    }

    public void addPaticipantes(Persona participante) {
        if(participantes == null){
            participantes = new HashSet();
        }
        participantes.add(participante);
        participante.addReunion(this);
    }

    public Reunion(LocalDateTime fecha, String asunto) {
        this();
        this.fecha = fecha;
        this.asunto = asunto;
    }

    public Reunion() {
        participantes = new HashSet();
    }

    public Acta getActa() {
        return acta;
    }

    public void setActa(Acta acta) {
        this.acta = acta;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void addParticipante(Persona participante){
        participantes.add(participante);
        if(!participante.getReuniones().contains(this)){
            participante.addReunion(this);
        }
    }

    @Override
    public String toString() {
        return "Reunion{" +
                "fecha=" + fecha +
                ", asunto='" + asunto + '\'' +
                '}';
    }
}
