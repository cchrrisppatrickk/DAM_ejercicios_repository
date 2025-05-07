package com.example.helloword.entities;

public class Contact {

    public String id;
    public String nombre;
    public String apellidos;
    public String numeroCelular;

    public Contact() {}


    public Contact(String id, String nombre, String apellidos, String numeroCelular) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroCelular = numeroCelular;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
}
