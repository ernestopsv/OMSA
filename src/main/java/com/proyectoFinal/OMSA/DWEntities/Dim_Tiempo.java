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
@Table(name = "dim_tiempo")
public class Dim_Tiempo implements Serializable {
    @Id
    @Column(unique=true, nullable = false, updatable = false)
    private int idTiempo;
    private String tanda;
    private boolean esTarde;
    private int cuartoDeHora;
    private int mediaHora;
    private String horaDelDia;
    private String hora;
    private String minuto;
    private String segundo;

    public Dim_Tiempo() {
    }

    public int getIdTiempo() {
        return idTiempo;
    }

    public void setIdTiempo(int idTiempo) {
        this.idTiempo = idTiempo;
    }

    public String getTanda() {
        return tanda;
    }

    public void setTanda(String tanda) {
        this.tanda = tanda;
    }

    public boolean isEsTarde() {
        return esTarde;
    }

    public void setEsTarde(boolean esTarde) {
        this.esTarde = esTarde;
    }

    public int getCuartoDeHora() {
        return cuartoDeHora;
    }

    public void setCuartoDeHora(int cuartoDeHora) {
        this.cuartoDeHora = cuartoDeHora;
    }

    public int getMediaHora() {
        return mediaHora;
    }

    public void setMediaHora(int mediaHora) {
        this.mediaHora = mediaHora;
    }

    public String getHoraDelDia() {
        return horaDelDia;
    }

    public void setHoraDelDia(String horaDelDia) {
        this.horaDelDia = horaDelDia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setMinuto(String minuto) {
        this.minuto = minuto;
    }

    public String getSegundo() {
        return segundo;
    }

    public void setSegundo(String segundo) {
        this.segundo = segundo;
    }
}
