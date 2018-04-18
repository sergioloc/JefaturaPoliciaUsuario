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
    private String hora;

    public Predenuncia(){}

    public Predenuncia(String tipo, String nombre, String apellidos, String ubicacion, String dni, String hora) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ubicacion = ubicacion;
        this.dni = dni;
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
