package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ProductosFrm extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre;
    EditText etPrecioVenta;
    EditText etId;
    Button agregarProducto;
    Button modificarProducto;
    Button cancelarMod;
    ListView lvProducto;

    //Instancias
    private Util_UI UtilesUI = new Util_UI();
    ProductoBL bl = new ProductoBL(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_frm);

        incializaPantalla();
        lvProducto.setAdapter(UtilesUI.CargaArrayAdapter(this, bl.ConsultaProducto()));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ListViewClick();
    }

    private void ListViewClick(){
        lvProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int laPosicion = position + 1;
                String elementoSeleccionado = (String) lvProducto.getItemAtPosition(position);
                String[] TextoPartido = elementoSeleccionado.split(",");
                etId.setText(String.valueOf(laPosicion));
                etPrecioVenta.setText(TextoPartido[1]);
                etNombre.setText(TextoPartido[0]);
                modificarProducto.setEnabled(true);
                cancelarMod.setVisibility(View.VISIBLE);
                agregarProducto.setEnabled(false);
            }
        });
    }

    private void incializaPantalla() {
        //EditTexts
        etPrecioVenta = (EditText) findViewById(R.id.etPrecioVenta);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etId = (EditText) findViewById(R.id.etId);

        //Buttons
        agregarProducto = (Button) findViewById(R.id.btnAgregar);
        agregarProducto.setOnClickListener(this);
        modificarProducto = (Button) findViewById(R.id.btnModificar);
        modificarProducto.setOnClickListener(this);
        cancelarMod = (Button) findViewById(R.id.btnCancelar);
        cancelarMod.setOnClickListener(this);

        //Lista
        lvProducto = (ListView) findViewById(R.id.listProductos);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnAgregar:
                bl.GuardaProducto(etNombre.getText().toString(),etPrecioVenta.getText().toString());
                lvProducto.setAdapter(UtilesUI.CargaArrayAdapter(this, bl.ConsultaProducto()));
                break;
            case R.id.btnCancelar:
                etId.getText().clear();
                etNombre.getText().clear();
                etPrecioVenta.getText().clear();
                agregarProducto.setEnabled(true);
                modificarProducto.setEnabled(false);
                cancelarMod.setVisibility(View.INVISIBLE);
            case R.id.btnModificar:
                if(bl.ModificaProducto(etId.getText().toString(), etNombre.getText().toString(), etPrecioVenta.getText().toString())) {
                    etId.getText().clear();
                    etNombre.getText().clear();
                    etPrecioVenta.getText().clear();
                    agregarProducto.setEnabled(true);
                    modificarProducto.setEnabled(false);
                    cancelarMod.setVisibility(View.INVISIBLE);
                    UtilesUI.MensajeToast(this, "Modificado con éxito");
                    lvProducto.setAdapter(UtilesUI.CargaArrayAdapter(this, bl.ConsultaProducto()));
                } else {
                    UtilesUI.MensajeToast(this, "Hubo un error");
                }
        }//End switch
    }
}
