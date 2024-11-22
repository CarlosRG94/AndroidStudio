package com.example.databaseapp;

public class ElementoUsuario {
    private int telefono ;
    private String nombre;

    public ElementoUsuario(int telefono, String nombre){
        this.telefono = telefono;
        this.nombre = nombre;
    }

    public int getTelefono(){
        return telefono;
    }
    public void setTelefono(int telefono){
        this.telefono = telefono;
    }

    public String getNombre(){
        return nombre;
    }


    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}

