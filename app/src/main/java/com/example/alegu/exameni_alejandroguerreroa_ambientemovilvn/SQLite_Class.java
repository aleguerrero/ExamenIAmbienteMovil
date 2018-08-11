package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SQLite_Class extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Polacos.db";

    Util_UI utilUi =  new Util_UI();

    public static final String CREA_TABLA_CLIENTES =
            "CREATE TABLE Clientes(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "telefono TEXT)";

    public static final String CREA_TABLA_PRODUCTOS =
            "CREATE TABLE Productos(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "precioVenta int)";

    public static final String CREA_TABLA_FACTURA =
            "CREATE TABLE Facturas(" +
                    "numero INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "cliente INTEGER," +
                    "producto TEXT," +
                    "cantidad INTEGER," +
                    "fecha DATE," +
                    "FOREIGN KEY(cliente) REFERENCES Clientes(id)," +
                    "FOREIGN KEY(producto) REFERENCES Productos(id))";


    public SQLite_Class(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLite_Class(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }//Fin Constructor  =======================


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREA_TABLA_CLIENTES);
        sqLiteDatabase.execSQL(CREA_TABLA_FACTURA);
        sqLiteDatabase.execSQL(CREA_TABLA_PRODUCTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Clientes");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Productos");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Facturas");
        onCreate(sqLiteDatabase);
    }

    //Clientes
    public int InsertaCliente(Cliente cliente) {
        ContentValues values = new ContentValues();
        values.put("nombre", cliente.nombre);
        values.put("telefono", cliente.telefono);

        SQLiteDatabase db = this.getWritableDatabase();
        long Cliente_Id = db.insert("Clientes", null, values);
        db.close();
        return (int) Cliente_Id;
    }

    public ArrayList<String> ConsultaClientes() {
        String Nombre = "";
        String Telefono = "";
        ArrayList<String> ListaClientes = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Clientes", null);

        while(cursor.moveToNext())
        {
            Telefono = cursor.getString(cursor.getColumnIndex("telefono"));
            Nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            ListaClientes.add(Nombre +", "+ Telefono);
            Nombre = "";
            Telefono = "";
        }
        cursor.close();
        db.close();
        return ListaClientes;
    }

    public ArrayList<String> ConsultaClientesSpinner() {
        String Nombre = "", Telefono = "";
        int Id;
        ArrayList<String> ListaClientes = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Clientes", null);
        while(cursor.moveToNext())
        {
            Id = cursor.getInt(cursor.getColumnIndex("id"));
            Nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            Telefono = cursor.getString(cursor.getColumnIndex("telefono"));
            ListaClientes.add(Id + " - " + Nombre + " - " + Telefono);
            Nombre = "";
            Id = 0;
            Telefono = "";
        }
        cursor.close();
        db.close();
        return ListaClientes;
    }

    public boolean ModificaCliente(Cliente cliente) {
        try {
            ContentValues values = new ContentValues();
            values.put("nombre", cliente.nombre);
            values.put("telefono", cliente.telefono);

            SQLiteDatabase db = this.getWritableDatabase();
            long Cliente_Id = db.update("Clientes", values, "id = " + cliente.getId(), null);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //Producto
    public int InsertaProducto(Producto producto) {
        ContentValues values = new ContentValues();
        values.put("nombre", producto.nombre);
        values.put("precioVenta", producto.precioVenta);

        SQLiteDatabase db = this.getWritableDatabase();
        long Producto_Id = db.insert("Productos", null, values);
        db.close();
        return (int) Producto_Id;
    }

    public ArrayList<String> ConsultaProductos() {
        String Nombre = "";
        String Precio = "";
        ArrayList<String> ListaProductos = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Productos", null);

        while(cursor.moveToNext())
        {
            Precio = cursor.getString(cursor.getColumnIndex("precioVenta"));
            Nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            ListaProductos.add(Nombre +", "+ Precio);
            Nombre = "";
            Precio = "";
        }
        cursor.close();
        db.close();
        return ListaProductos;
    }

    public ArrayList<String> ConsultaProductosSpinner() {
        String Nombre = "";
        int Id;
        String Precio = "";
        ArrayList<String> ListaProductos = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Productos", null);

        while(cursor.moveToNext())
        {
            Id = cursor.getInt(cursor.getColumnIndex("id"));
            Precio = cursor.getString(cursor.getColumnIndex("precioVenta"));
            Nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            ListaProductos.add(Id + " - " + Nombre + " - " + Precio);
            Nombre = "";
            Precio = "";
            Id = 0;
        }
        cursor.close();
        db.close();
        return ListaProductos;
    }

    public boolean ModificaProducto(Producto producto) {
        try {
            ContentValues values = new ContentValues();
            values.put("nombre", producto.nombre);
            values.put("precioVenta", producto.precioVenta);

            SQLiteDatabase db = this.getWritableDatabase();
            long Producto_Id = db.update("Productos", values, "id = " + producto.getId(), null);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
