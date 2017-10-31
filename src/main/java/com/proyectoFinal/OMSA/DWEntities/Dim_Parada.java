package com.proyectoFinal.OMSA.DWEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Dany on 30/10/2017.
 */
@Entity
@Table(name = "dim_parada")
public class Dim_Parada implements Serializable {
    @Id
    @Column(unique=true, nullable = false, updatable = false)
    private long idParada ;
    private String nombre;
    private String ruta;
    private String direccionRuta;

    public Dim_Parada() {
    }

    public long getIdParada() {
        return idParada;
    }

    public void setIdParada(long idParada) {
        this.idParada = idParada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDireccionRuta() {
        return direccionRuta;
    }

    public void setDireccionRuta(String direccionRuta) {
        this.direccionRuta = direccionRuta;
    }
}
