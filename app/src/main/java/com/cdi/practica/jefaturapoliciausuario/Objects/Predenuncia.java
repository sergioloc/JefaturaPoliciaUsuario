package com.cdi.practica.jefaturapoliciausuario.Objects;

/**
 * Created by aired on 12/04/2018.
 */

public class Predenuncia {
    private String fact;
    private String ubicacion;
    private String nombre;
    private String dni;

    public Predenuncia(){}
    public Predenuncia(String fact, String ubicacion, String nombre, String dni, String descripcion) {
        this.fact = fact;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getFact() {
        return fact;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDni() {
        return dni;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
}
