package com.cdi.practica.jefaturapoliciausuario.Objects;

/**
 * Created by Sergio on 17/04/2018.
 */

public class Emergencia {

    private String ubicacion;
    private String idUsuario;
    private String hora;

    public Emergencia() {
    }

    public Emergencia(String ubicacion, String idUsuario, String hora) {
        this.ubicacion = ubicacion;
        this.idUsuario = idUsuario;
        this.hora = hora;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
