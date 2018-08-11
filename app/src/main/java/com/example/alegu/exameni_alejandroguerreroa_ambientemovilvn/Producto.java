package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

public class Producto {

    public int id;
    public String nombre;
    public int precioVenta;

    public Producto() {
    }

    public Producto(int id, String nombre, int precioVenta) {
        this.id = id;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }
}
