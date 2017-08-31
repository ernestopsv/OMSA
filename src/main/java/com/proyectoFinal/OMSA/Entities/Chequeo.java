package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Min(1483228800)
    private Long fechaRegistrada;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Autobus autobus;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)  // to be thought
    private Parada parada;
    @NotNull
    private Boolean esEntrada;

    public Chequeo(Long fechaRegistrada, Autobus autobus, Parada parada, Boolean esEntrada) {
        this.fechaRegistrada = fechaRegistrada;
        this.autobus = autobus;
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

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
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
