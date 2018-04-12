package com.cdi.practica.jefaturapoliciausuario.Objects;

/**
 * Created by aired on 12/04/2018.
 */

public class Predenuncia {
    private String fact;
    private String name;
    private String lastName;
    private String ubication;
    private String dni;

    public Predenuncia(){}

    public Predenuncia(String fact, String name, String lastName, String dni, String ubication) {
        this.fact = fact;
        this.name = name;
        this.lastName = lastName;
        this.ubication = ubication;
        this.dni = dni;
    }

    public String getFact() {
        return fact;
    }
    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUbication() {
        return ubication;
    }
    public String getDni() {
        return dni;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUbication(String ubication) {
        this.ubication = ubication;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
}
