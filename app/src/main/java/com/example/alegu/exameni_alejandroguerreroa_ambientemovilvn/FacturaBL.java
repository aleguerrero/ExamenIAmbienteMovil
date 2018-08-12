package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

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
        ArrayList<String> listaClientes;
        try {
            listaClientes = sql.ConsultaClientesSpinner();
            return listaClientes;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<String> CargaSpinnerProducto() {
        ArrayList<String> listaProductos;
        try {
            listaProductos = sql.ConsultaProductosSpinner();
            return listaProductos;
        } catch (Exception e) {
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean PagarProductos (ArrayList<Factura> facturas) {

        //Para retornar que sí se agregan las facturas
        boolean working = false;

        try {
            for (Factura facturaLoop : facturas) {
                working = sql.InsertaFactura(facturaLoop);
            }
            return working;
        } catch (Exception e) {
            return false;
        }

    }
}
