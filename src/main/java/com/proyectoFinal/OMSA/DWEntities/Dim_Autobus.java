package com.proyectoFinal.OMSA.DWEntities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Dany on 30/10/2017.
 */
@Entity
@Table(name = "dim_autobus")
public class Dim_Autobus implements Serializable{
    @Id
    private Long idAutobus;
    private String matricula;
    private int precio;
    private int cantidadDeAsientos;
    private String conductor;
    private String modelo;
    private int anoFabricacion;
    private boolean tieneAireAcondicionado;

    public Dim_Autobus() {
    }

    public int getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(int anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public boolean isTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(boolean tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public Long getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(Long idAutobus) {
        this.idAutobus = idAutobus;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidadDeAsientos() {
        return cantidadDeAsientos;
    }

    public void setCantidadDeAsientos(int cantidadDeAsientos) {
        this.cantidadDeAsientos = cantidadDeAsientos;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
