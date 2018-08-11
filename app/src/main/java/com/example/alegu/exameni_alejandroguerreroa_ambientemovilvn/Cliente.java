package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

public class Cliente {

    public String id;
    public String nombre;
    public String telefono;

    public Cliente() {}

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Cliente(String id, String nombre, String telefono) {

        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }
}
