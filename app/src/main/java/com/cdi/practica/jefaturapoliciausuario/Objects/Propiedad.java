package com.cdi.practica.jefaturapoliciausuario.Objects;

/**
 * Created by Sergio on 11/04/2018.
 */

public class Propiedad {

    private String tipo;
    private String direccion;

    public Propiedad(){}
    public Propiedad(String tipo, String direccion) {
        this.tipo = tipo;
        this.direccion = direccion;
    }

    //Getters and Setters
    public String getTipo() {
        return tipo;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setTipo(String type) {
        this.tipo = tipo;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


}
