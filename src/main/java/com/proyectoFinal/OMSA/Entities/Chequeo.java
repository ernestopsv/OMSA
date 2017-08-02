package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name = "chequeo")
public class Chequeo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id; //to be thinking
    private Long fechaRegistrada;
    @ManyToOne(cascade = CascadeType.ALL)
    private RaspberryPiAPI raspberryPiAPI;
    @ManyToOne(cascade = CascadeType.ALL)  // to be thought
    private Parada parada;
    private Boolean esEntrada;

    public Chequeo(Long fechaRegistrada, RaspberryPiAPI raspberryPiAPI, Parada parada, Boolean esEntrada) {
        this.fechaRegistrada = fechaRegistrada;
        this.raspberryPiAPI = raspberryPiAPI;
        this.parada = parada;
        this.esEntrada = esEntrada;
    }

    public Chequeo(){

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Long getFechaRegistrada() {
        return fechaRegistrada;
    }

    public void setFechaRegistrada(Long fechaRegistrada) {
        this.fechaRegistrada = fechaRegistrada;
    }

    public RaspberryPiAPI getRaspberryPiAPI() {
        return raspberryPiAPI;
    }

    public void setRaspberryPiAPI(RaspberryPiAPI raspberryPiAPI) {
        this.raspberryPiAPI = raspberryPiAPI;
    }

    public Parada getParada() {
        return parada;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }

    public Boolean getEsEntrada() {
        return esEntrada;
    }

    public void setEsEntrada(Boolean esEntrada) {
        this.esEntrada = esEntrada;
    }
}
