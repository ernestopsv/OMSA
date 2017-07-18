package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name = "coordenada")
public class Coordenada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitud;

    public Coordenada(){

    }

    public Coordenada(double latitude, double longitud) {
        this.latitude = latitude;
        this.longitud = longitud;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
