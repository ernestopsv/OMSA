package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by anyderre on 28/06/17.
 */
@Entity
public class  RaspberryPiAPI implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroSerial;
    private String macAddress;

    @OneToOne
    private Autobus autobus;

    public RaspberryPiAPI() {

    }

    public RaspberryPiAPI(String numeroSerial, String macAddress, Autobus autobus) {
        this.numeroSerial = numeroSerial;
        this.macAddress = macAddress;
        this.autobus = autobus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroSerial() {
        return numeroSerial;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }
}
