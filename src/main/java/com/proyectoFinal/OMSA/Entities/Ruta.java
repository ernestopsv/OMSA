package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
@Table(name ="ruta")
public class Ruta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float distanciaTotal;
    private Long fechaCreada;
    private Long fechaUltimaModificacion;
    private String ciudad;
    private String nombreCorredor;
    private Boolean esDireccionSubida;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List <Coordenada> coordenadas;

    private Ruta(){

    }

    public Ruta(Float distanciaTotal, Long fechaCreada, Long fechaUltimaModificacion, String ciudad, String nombreCorredor, Boolean esDireccionSubida, List<Coordenada> coordenadas) {
        this.distanciaTotal = distanciaTotal;
        this.fechaCreada = fechaCreada;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.ciudad = ciudad;
        this.nombreCorredor = nombreCorredor;
        this.esDireccionSubida = esDireccionSubida;
        this.coordenadas = coordenadas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(Float distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public Long getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(Long fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public Long getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Long fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreCorredor() {
        return nombreCorredor;
    }

    public void setNombreCorredor(String nombreCorredor) {
        this.nombreCorredor = nombreCorredor;
    }

    public Boolean getEsDireccionSubida() {
        return esDireccionSubida;
    }

    public void setEsDireccionSubida(Boolean esDireccionSubida) {
        this.esDireccionSubida = esDireccionSubida;
    }

    public List<Coordenada> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;
    }
}
