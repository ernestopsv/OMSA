package com.proyectoFinal.OMSA.Entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by anyderre on 11/08/17.
 */
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 100)
    private String name;
    @Size(min = 4, max = 30)
    @Column(unique=true)
    private String username;
    @Size(min = 6, max = 30)
    private String password;
    private Boolean admin;

    public Usuario() {
    }

    public Usuario(Long id, String name, String username, String password, Boolean admin) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
