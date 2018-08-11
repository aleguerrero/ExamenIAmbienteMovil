package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura_frm);

        iniciaPantalla();
        cargaSpinners();
    }

    private void cargaSpinners() {
        
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

        //Buttons
        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);
        btnPagar = findViewById(R.id.btnPagar);
        btnPagar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
