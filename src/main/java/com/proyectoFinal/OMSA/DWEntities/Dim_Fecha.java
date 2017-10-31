package com.proyectoFinal.OMSA.DWEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dany on 30/10/2017.
 */
@Entity
@Table(name = "dim_fecha")
public class Dim_Fecha implements Serializable {
    @Id
    @Column(unique=true, nullable = false, updatable = false)
    private long idDia;
    private String anoSemana;
    private String nombreMes;
    private String nombreDiaSemana;
    private String fechaLarga;
    private Date fechaISO;
    private Date fecha;
    private int ano;
    private int diaEnAno;
    private int diaEnSemana;
    private int diaEnMes;
    private int numeroSemanaEnAno;
    private int numeroMesEnAno;
    private int anoISO;
    private int semanaISO;
    private boolean esFinDeSemana;
    private String diaFeriadoMovible;
    private String diaFeriadoFijo;
    private String nombreDiaFeriado;
    private boolean esFeriado;
    private boolean esFeriadoFijo;
    private boolean isEsFeriadoMovible;

    public Dim_Fecha() {
    }

    public long getIdDia() {
        return idDia;
    }

    public void setIdDia(long idDia) {
        this.idDia = idDia;
    }

    public String getAnoSemana() {
        return anoSemana;
    }

    public void setAnoSemana(String anoSemana) {
        this.anoSemana = anoSemana;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

    public String getNombreDiaSemana() {
        return nombreDiaSemana;
    }

    public void setNombreDiaSemana(String nombreDiaSemana) {
        this.nombreDiaSemana = nombreDiaSemana;
    }

    public String getFechaLarga() {
        return fechaLarga;
    }

    public void setFechaLarga(String fechaLarga) {
        this.fechaLarga = fechaLarga;
    }

    public Date getFechaISO() {
        return fechaISO;
    }

    public void setFechaISO(Date fechaISO) {
        this.fechaISO = fechaISO;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDiaEnAno() {
        return diaEnAno;
    }

    public void setDiaEnAno(int diaEnAno) {
        this.diaEnAno = diaEnAno;
    }

    public int getDiaEnSemana() {
        return diaEnSemana;
    }

    public void setDiaEnSemana(int diaEnSemana) {
        this.diaEnSemana = diaEnSemana;
    }

    public int getDiaEnMes() {
        return diaEnMes;
    }

    public void setDiaEnMes(int diaEnMes) {
        this.diaEnMes = diaEnMes;
    }

    public int getNumeroSemanaEnAno() {
        return numeroSemanaEnAno;
    }

    public void setNumeroSemanaEnAno(int numeroSemanaEnAno) {
        this.numeroSemanaEnAno = numeroSemanaEnAno;
    }

    public int getNumeroMesEnAno() {
        return numeroMesEnAno;
    }

    public void setNumeroMesEnAno(int numeroMesEnAno) {
        this.numeroMesEnAno = numeroMesEnAno;
    }

    public int getAnoISO() {
        return anoISO;
    }

    public void setAnoISO(int anoISO) {
        this.anoISO = anoISO;
    }

    public int getSemanaISO() {
        return semanaISO;
    }

    public void setSemanaISO(int semanaISO) {
        this.semanaISO = semanaISO;
    }

    public boolean isEsFinDeSemana() {
        return esFinDeSemana;
    }

    public void setEsFinDeSemana(boolean esFinDeSemana) {
        this.esFinDeSemana = esFinDeSemana;
    }

    public String getDiaFeriadoMovible() {
        return diaFeriadoMovible;
    }

    public void setDiaFeriadoMovible(String diaFeriadoMovible) {
        this.diaFeriadoMovible = diaFeriadoMovible;
    }

    public String getDiaFeriadoFijo() {
        return diaFeriadoFijo;
    }

    public void setDiaFeriadoFijo(String diaFeriadoFijo) {
        this.diaFeriadoFijo = diaFeriadoFijo;
    }

    public String getNombreDiaFeriado() {
        return nombreDiaFeriado;
    }

    public void setNombreDiaFeriado(String nombreDiaFeriado) {
        this.nombreDiaFeriado = nombreDiaFeriado;
    }

    public boolean isEsFeriado() {
        return esFeriado;
    }

    public void setEsFeriado(boolean esFeriado) {
        this.esFeriado = esFeriado;
    }

    public boolean isEsFeriadoFijo() {
        return esFeriadoFijo;
    }

    public void setEsFeriadoFijo(boolean esFeriadoFijo) {
        this.esFeriadoFijo = esFeriadoFijo;
    }

    public boolean isEsFeriadoMovible() {
        return isEsFeriadoMovible;
    }

    public void setEsFeriadoMovible(boolean esFeriadoMovible) {
        isEsFeriadoMovible = esFeriadoMovible;
    }
}
