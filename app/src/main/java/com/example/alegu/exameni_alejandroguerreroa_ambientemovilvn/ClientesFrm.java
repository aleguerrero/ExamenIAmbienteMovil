package com.example.alegu.exameni_alejandroguerreroa_ambientemovilvn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ClientesFrm extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre;
    EditText etTelefono;
    EditText etId;
    Button agregarCliente;
    Button modificarCliente;
    Button cancelarMod;
    ListView lvClientes;

    //Instancias
    private Util_UI UtilesUI = new Util_UI();
    ClienteBL bl = new ClienteBL(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_frm);

        incializaPantalla();
        lvClientes.setAdapter(UtilesUI.CargaArrayAdapter(this, bl.ConsultaClientes()));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ListViewClick();
    }

    private void ListViewClick(){
        lvClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int laPosicion = position + 1;
                String elementoSeleccionado = (String) lvClientes.getItemAtPosition(position);
                String[] TextoPartido = elementoSeleccionado.split(",");
                etId.setText(String.valueOf(laPosicion));
                etTelefono.setText(TextoPartido[1]);
                etNombre.setText(TextoPartido[0]);
                modificarCliente.setEnabled(true);
                cancelarMod.setVisibility(View.VISIBLE);
                agregarCliente.setEnabled(false);
            }
        });
    }

    private void incializaPantalla() {
        //EditTexts
        etTelefono = (EditText) findViewById(R.id.etPrecioVenta);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etId = (EditText) findViewById(R.id.etId);

        //Buttons
        agregarCliente = (Button) findViewById(R.id.btnAgregar);
        agregarCliente.setOnClickListener(this);
        modificarCliente = (Button) findViewById(R.id.btnModificar);
        modificarCliente.setOnClickListener(this);
        cancelarMod = (Button) findViewById(R.id.btnCancelar);
        cancelarMod.setOnClickListener(this);

        //Lista
        lvClientes = (ListView) findViewById(R.id.listProductos);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnAgregar:
                bl.GuardaCliente(etNombre.getText().toString(),etTelefono.getText().toString());
                lvClientes.setAdapter(UtilesUI.CargaArrayAdapter(this, bl.ConsultaClientes()));
                break;
            case R.id.btnCancelar:
                etId.getText().clear();
                etNombre.getText().clear();
                etTelefono.getText().clear();
                agregarCliente.setEnabled(true);
                modificarCliente.setEnabled(false);
                cancelarMod.setVisibility(View.INVISIBLE);
            case R.id.btnModificar:
                if(bl.ModificaCliente(etId.getText().toString(), etNombre.getText().toString(), etTelefono.getText().toString())) {
                    etId.getText().clear();
                    etNombre.getText().clear();
                    etTelefono.getText().clear();
                    agregarCliente.setEnabled(true);
                    modificarCliente.setEnabled(false);
                    cancelarMod.setVisibility(View.INVISIBLE);
                    UtilesUI.MensajeToast(this, "Modificado con éxito");
                    lvClientes.setAdapter(UtilesUI.CargaArrayAdapter(this, bl.ConsultaClientes()));
                } else {
                    UtilesUI.MensajeToast(this, "Hubo un error");
                }
        }//End switch
    }
}
