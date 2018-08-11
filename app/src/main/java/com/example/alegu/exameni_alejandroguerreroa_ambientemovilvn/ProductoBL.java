package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.content.Context;

import java.util.ArrayList;

public class ProductoBL {
    Context myContext;
    private SQLite_Class sql;
    Util_UI util_ui = new Util_UI();

    public ProductoBL(Context myContext) {
        this.myContext = myContext;
        sql = new SQLite_Class(myContext);
    }

    public void GuardaProducto(String elnombre, String elprecioVenta){
        Producto producto = new Producto();
        producto.precioVenta = Integer.parseInt(elprecioVenta);
        producto.nombre = elnombre;
        sql.InsertaProducto(producto);
    }

    public ArrayList<String> ConsultaProducto(){
        int conteo = sql.ConsultaProductos().size();
        if (conteo == 0){
            util_ui.MensajeToast(myContext, "No hay productos");
            return sql.ConsultaProductos();
        } else {
            return sql.ConsultaProductos();
        }
    }

    public boolean ModificaProducto(String id, String nombre, String precioVenta) {
        Producto producto = new Producto();
        producto.id = Integer.parseInt(id);
        producto.nombre = nombre;
        producto.precioVenta = Integer.parseInt(precioVenta);
        return sql.ModificaProducto(producto);
    }
}
