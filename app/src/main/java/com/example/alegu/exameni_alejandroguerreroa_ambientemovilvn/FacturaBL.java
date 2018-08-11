package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.content.Context;
import java.util.ArrayList;

public class FacturaBL {

    Context myContext;
    private SQLite_Class sql;
    Util_UI util_ui = new Util_UI();

    public FacturaBL(Context myContext) {
        this.myContext = myContext;
        sql = new SQLite_Class(myContext);
    }

    public ArrayList<String> CargaSpinnerCliente() {
        ArrayList<String> listaClientes = new ArrayList<>();
        try {
            listaClientes = sql.ConsultaClientesSpinner();
            return listaClientes;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<String> CargaSpinnerProducto() {
        ArrayList<String> listaProductos = new ArrayList<>();
        try {
            listaProductos = sql.ConsultaProductosSpinner();
            return listaProductos;
        } catch (Exception e) {
            return null;
        }
    }
}
