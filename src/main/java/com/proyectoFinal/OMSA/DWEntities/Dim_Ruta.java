package com.proyectoFinal.OMSA.DWEntities;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Dany on 30/10/2017.
 */
@Entity
@Table(name = "dim_ruta")
public class Dim_Ruta implements Serializable {
    @Id
    @Column(unique=true, nullable = false, updatable = false)
    private String idRuta;
    private  String ciudad;
    private String direccion;
    private String nombreCorredor;

    public Dim_Ruta() {
    }

    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreCorredor() {
        return nombreCorredor;
    }

    public void setNombreCorredor(String nombreCorredor) {
        this.nombreCorredor = nombreCorredor;
    }
}
