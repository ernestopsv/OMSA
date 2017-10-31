package com.proyectoFinal.OMSA.DWEntities;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Dany on 30/10/2017.
 */
@Entity
@Table(name = "dim_ruta")
public class Dim_Ruta {
    @Id
    private long id;
    private  String ciudad;
    private String direccion;
    private String nombreCorredor;

    public Dim_Ruta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
