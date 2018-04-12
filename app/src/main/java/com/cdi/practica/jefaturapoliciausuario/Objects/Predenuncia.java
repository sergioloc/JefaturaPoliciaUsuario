package com.cdi.practica.jefaturapoliciausuario.Objects;

/**
 * Created by aired on 12/04/2018.
 */

public class Predenuncia {
    private String tipo;
    private String nombre;
    private String apellidos;
    private String ubicacion;
    private String dni;

    public Predenuncia(){}

    public Predenuncia(String tipo, String nombre, String apellidos, String dni, String ubicacion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ubicacion = ubicacion;
        this.dni = dni;
    }

    public String getTipo() {
        return tipo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public String getDni() {
        return dni;
    }

    public void setTipo(String fact) {
        this.tipo = fact;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
}
