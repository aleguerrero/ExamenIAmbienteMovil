package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.content.Context;

import java.util.ArrayList;

public class ClienteBL {

    Context myContext;
    private SQLite_Class sql;
    Util_UI util_ui = new Util_UI();

    public ClienteBL(Context myContext) {
        this.myContext = myContext;
        sql = new SQLite_Class(myContext);
    }

    public void GuardaCliente(String elnombre, String eltelefono){
        Cliente Persona = new Cliente();
        Persona.telefono = eltelefono;
        Persona.nombre = elnombre;
        sql.InsertaCliente(Persona);
    }

    public ArrayList<String> ConsultaClientes(){
        int conteo = sql.ConsultaClientes().size();
        if (conteo == 0){
            util_ui.MensajeToast(myContext, "No hay Clientes");
            return sql.ConsultaClientes();
        } else {
            return sql.ConsultaClientes();
        }
    }

    public boolean ModificaCliente(String id, String nombre, String telefono) {
        Cliente cliente = new Cliente();
        cliente.id = id;
        cliente.nombre = nombre;
        cliente.telefono = telefono;
        return sql.ModificaCliente(cliente);
    }
}
