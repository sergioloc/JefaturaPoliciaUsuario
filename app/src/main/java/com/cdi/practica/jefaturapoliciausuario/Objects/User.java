package com.cdi.practica.jefaturapoliciausuario.Objects;

/**
 * Created by Sergio on 10/04/2018.
 */

public class User {
    private String name;
    private String lastName;
    private String dni;
    private String phone;
    private String email;

    public User() {
    }

    public User(String name, String lastName, String dni, String phone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;
        this.email = email;
    }


    // Getters and Setters

    public String getName() {
        return name;
    }
    public String getLastName() {
        return lastName;
    }
    public String getDni() {
        return dni;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
