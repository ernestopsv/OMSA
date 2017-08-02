package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.DataInput;
import java.io.Serializable;
import java.sql.Date;

//import javax.persistence.*;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name = "autobus")
public class Autobus implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 35)
    private String modelo;
    @Max(100)
    private Integer cantidadDeAsientos;
    private Float peso;
    @ManyToOne
    private Ruta ruta; //Updatable
    @ManyToOne
    private Parada ultimaParada; // Updatable
    private Integer anoFabricacion;
    private Boolean activo;  //Updatable
    private String conductor;
    private Long fechaCreada; //fecha agregada en la base de datos
    private Long ultimaFechaModificada; // Updatable
    @Min(10)
    private Integer precio;
    private Boolean tieneAireAcondicionado;
    @Max(100)
    private Integer cantidadDePasajerosActual; //Updatable
    @OneToOne(cascade = CascadeType.ALL)
    private Coordenada coordenada; //Updatable
    @OneToOne(cascade = CascadeType.ALL)
    private RaspberryPiAPI raspberryPi;

    public Autobus() {

    }

    public Autobus(String modelo, Integer cantidadDeAsientos, Float peso, Ruta ruta, Parada ultimaParada, Integer anoFabricacion, Boolean activo, String conductor, Long fechaCreada, Long ultimaFechaModificada, Integer precio, Boolean tieneAireAcondicionado, Integer cantidadDePasajerosActual, Coordenada coordenada, RaspberryPiAPI raspberryPi) {
        this.modelo = modelo;
        this.cantidadDeAsientos = cantidadDeAsientos;
        this.peso = peso;
        this.ruta = ruta;
        this.ultimaParada = ultimaParada;
        this.anoFabricacion = anoFabricacion;
        this.activo = activo;
        this.conductor = conductor;
        this.fechaCreada = fechaCreada;
        this.ultimaFechaModificada = ultimaFechaModificada;
        this.precio = precio;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.cantidadDePasajerosActual = cantidadDePasajerosActual;
        this.coordenada = coordenada;
        this.raspberryPi = raspberryPi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCantidadDeAsientos() {
        return cantidadDeAsientos;
    }

    public void setCantidadDeAsientos(Integer cantidadDeAsientos) {
        this.cantidadDeAsientos = cantidadDeAsientos;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Parada getUltimaParada() {
        return ultimaParada;
    }

    public void setUltimaParada(Parada ultimaParada) {
        this.ultimaParada = ultimaParada;
    }

    public Integer getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(Integer anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public Long getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(Long fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public Long getUltimaFechaModificada() {
        return ultimaFechaModificada;
    }

    public void setUltimaFechaModificada(Long ultimaFechaModificada) {
        this.ultimaFechaModificada = ultimaFechaModificada;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Boolean getTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(Boolean tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public Integer getCantidadDePasajerosActual() {
        return cantidadDePasajerosActual;
    }

    public void setCantidadDePasajerosActual(Integer cantidadDePasajerosActual) {
        this.cantidadDePasajerosActual = cantidadDePasajerosActual;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public RaspberryPiAPI getRaspberryPi() {
        return raspberryPi;
    }

    public void setRaspberryPi(RaspberryPiAPI raspberryPi) {
        this.raspberryPi = raspberryPi;
    }


}
