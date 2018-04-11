package com.cdi.practica.jefaturapoliciausuario.Objects;

/**
 * Created by Sergio on 11/04/2018.
 */

public class Propertie {
    private String type;
    private String address;

    public Propertie(){
    }
    public Propertie(String type, String address) {
        this.type = type;
        this.address = address;
    }

    //Getters and Setters

    public String getType() {
        return type;
    }
    public String getAddress() {
        return address;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setAddress(String address) {
        this.address = address;
    }


}
