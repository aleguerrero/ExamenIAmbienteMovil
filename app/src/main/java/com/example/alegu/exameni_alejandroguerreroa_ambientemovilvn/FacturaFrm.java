package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FacturaFrm extends AppCompatActivity implements View.OnClickListener {

    //Spinner
    Spinner spCliente;
    Spinner spProducto;

    //TextViews
    TextView tvTotal;

    //EditText
    EditText etCantidad;

    //ListView
    ListView lvLista;

    //Buttons
    Button btnAgregar;
    Button btnPagar;

    //Instancias
    FacturaBL facturaBL =  new FacturaBL(this);
    Util_UI util_ui =  new Util_UI();

    //Para Lista
    ArrayList<String> carrito = new ArrayList<>();
    ArrayAdapter<String> adapter;

    //Total
    int total;

    //Cantidades por producto
    ArrayList<Integer> cantidadesXProducto = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura_frm);

        iniciaPantalla();
        cargaSpinners();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void cargaSpinners() {
        try {
            spCliente.setAdapter(util_ui.CargaArrayAdapter(this, facturaBL.CargaSpinnerCliente()));
            spProducto.setAdapter(util_ui.CargaArrayAdapter(this, facturaBL.CargaSpinnerProducto()));
        } catch (Exception e) {
            util_ui.MensajeToast(this, "Hubo un error");
        }
    }

    private void iniciaPantalla() {
        //Spinners
        spCliente = findViewById(R.id.spCliente);
        spProducto = findViewById(R.id.spProducto);

        //TextViews
        tvTotal = findViewById(R.id.tvTotal);

        //EditText
        etCantidad = findViewById(R.id.etCantidad);

        //ListView
        lvLista = findViewById(R.id.lvLista);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carrito);
        lvLista.setAdapter(adapter);

        //Buttons
        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);
        btnPagar = findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAgregar:
                try {
                    //Separa texto
                    String[] caracProducto = spProducto.getSelectedItem().toString().split(" - ");

                    //Toma precio y lo multiplica
                    int precioXCantidad = Integer.parseInt(caracProducto[2]) * Integer.parseInt(etCantidad.getText().toString());
                    cantidadesXProducto.add(Integer.parseInt(etCantidad.getText().toString()));

                    //Agrega a la lista
                    carrito.add(caracProducto[0] + " - " + caracProducto[1] + " - Cantidad: " + etCantidad.getText().toString() + " - Precio: " + precioXCantidad);
                    adapter.notifyDataSetChanged();

                    //Calcula total
                    total += precioXCantidad;
                    tvTotal.setText(String.valueOf(total));

                    //Limpia campos
                    spProducto.setSelection(-1);
                    etCantidad.getText().clear();

                    break;
                } catch (Exception e) {
                    util_ui.MensajeToast(this, "Hubo un error");
                    break;
                }
            case R.id.btnPagar:
                try {
                    //ArrayList para cada fila
                     ArrayList<Factura> facturas = new ArrayList<>();

                    //Busca ID
                    String[] caracCliente = spCliente.getSelectedItem().toString().split(" - ");

                    //Por cada producto en carrito, lo va a agregar en factura
                    for (int i = 0; i < adapter.getCount(); i++) {

                        //Divide texto por fila de carrito
                        String[] caracFila = adapter.getItem(i).toString().split(" - ");

                        //Se crea una factura
                        Factura factura = new Factura();
                        factura.cantidad = cantidadesXProducto.get(i);
                        factura.cliente = caracCliente[0];
                        factura.producto = caracFila[0];

                        //Se agrega a facturasPorComprar
                        facturas.add(factura);
                    }

                    //Se crea un boolean que agregue las facturas a la tabla
                    boolean exito = facturaBL.PagarProductos(facturas);

                    //Si funciona, muestra mensaje de éxito y cierra
                    //si no, muestra que hubo un error
                    if (exito){
                        util_ui.MensajeToast(this, "Pagado con éxito");
                        this.finish();
                    } else {
                        util_ui.MensajeToast(this, "Hubo un error");
                    }
                    break;
                } catch (Exception e) {
                    util_ui.MensajeToast(this, "Hubo un error");
                    break;
                }
        }
    }
}

