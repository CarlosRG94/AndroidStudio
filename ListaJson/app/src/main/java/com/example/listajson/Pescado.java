package com.example.listajson;

public class Pescado {
    private String nombre;
    private String categoria;
    private String tamano;
    private int precio;

    public Pescado(String nombre, String categoria, String tamano, int precio)
    {
        this.nombre = nombre;
        this.categoria = categoria;
        this.tamano = tamano;
        this.precio = precio;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public String getTamano()
    {
        return tamano;
    }

    public void setTamano(String tamano)
    {
        this.tamano = tamano;
    }

    public int getPrecio()
    {
        return precio;
    }

    public void setPrecio(int precio)
    {
        this.precio = precio;
    }
}
