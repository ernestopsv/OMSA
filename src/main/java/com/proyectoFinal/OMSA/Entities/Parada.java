package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name = "parada")
public class Parada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    private Ruta ruta;

    private Long paradaAnterior;
    @OneToOne
    private Long paradaSiguiente;
    @OneToOne
    private Coordenada coordenada;


    public Parada(){

    }

    public Parada(String nombre, Ruta ruta, Long paradaAnterior, Long paradaSiguiente, Coordenada coordenada) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.paradaAnterior = paradaAnterior;
        this.paradaSiguiente = paradaSiguiente;
        this.coordenada = coordenada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Long getParadaAnterior() {
        return paradaAnterior;
    }

    public void setParadaAnterior(Long paradaAnterior) {
        this.paradaAnterior = paradaAnterior;
    }

    public Long getParadaSiguiente() {
        return this.paradaSiguiente;
    }

    public void setParadaSiguiente(Long paradaSiguiente) {
        this.paradaSiguiente = paradaSiguiente;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
}
