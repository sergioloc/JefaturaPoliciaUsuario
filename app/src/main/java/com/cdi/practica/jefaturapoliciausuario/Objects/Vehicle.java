package com.cdi.practica.jefaturapoliciausuario.Objects;

/**
 * Created by Sergio on 10/04/2018.
 */

public class Vehicle {
    private String tipo;
    private String matricula;
    private String modelo;
    private String seguro;
    private String lastITV;

    public Vehicle(){
    }

    public Vehicle(String tipo, String matricula, String modelo, String seguro, String lastITV) {
        this.tipo = tipo;
        this.matricula = matricula;
        this.modelo = modelo;
        this.seguro = seguro;
        this.lastITV = lastITV;
    }

    // Getters and Setters

    public String getTipo() {
        return tipo;
    }
    public String getMatricula() {
        return matricula;
    }
    public String getModelo() {
        return modelo;
    }
    public String getSeguro() {
        return seguro;
    }
    public String getLastITV() {
        return lastITV;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }
    public void setLastITV(String lastITV) {
        this.lastITV = lastITV;
    }


}
