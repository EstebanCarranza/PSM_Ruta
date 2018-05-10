package com.twicky.estebancarranza.reparto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.datos.cliente;
import com.twicky.estebancarranza.reparto.datos.regimenFiscal;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.twicky.estebancarranza.reparto.database.db;

public class mac_cliente extends AppCompatActivity {

    ArrayList<regimenFiscal> listRegimenFiscal;
    ArrayAdapter<String> comboAdapter;
    Spinner cbxRegimenFiscal;
    Button btnGuardarClienteCRU;
    TextView txtRFCCRU;
    TextView txtNombreClienteCRU;
    TextView txtDireccionClienteCRU;
    TextView txtTelefonoClienteCRU;
    Button btnCargarClienteCRU;

    private void inicializarComponentes()
    {
        btnGuardarClienteCRU = (Button) findViewById(R.id.btnGuardarClienteCRU);
        txtRFCCRU = (TextView) findViewById(R.id.txtRFCCRU);
        txtNombreClienteCRU = (TextView) findViewById(R.id.txtNombreClienteCRU);
        txtDireccionClienteCRU = (TextView) findViewById(R.id.txtDireccionClienteCRU);
        cbxRegimenFiscal = (Spinner) findViewById(R.id.cbxRegimenFiscal);
        txtTelefonoClienteCRU = (TextView) findViewById(R.id.txtTelefonoClienteCRU);
        btnCargarClienteCRU = (Button) findViewById(R.id.btnCargarClienteCRU);

        Toast.makeText(this, db.create.db_psm, Toast.LENGTH_SHORT).show();
    }
    private void verificarDatos()
    {
        int idCliente = 0;
        try {
            idCliente = getIntent().getExtras().getInt("idCliente");
        }
        catch (Exception e)
        {
            idCliente = 0;
        }
        if(idCliente > 0 )
        {
            clienteSQL db = new clienteSQL(getApplicationContext());
            cliente Cliente = db.getCliente(idCliente);
            txtNombreClienteCRU.setText(Cliente.getNombre());
            txtDireccionClienteCRU.setText(Cliente.getDomicilio());
            txtTelefonoClienteCRU.setText(Cliente.getTelefono());
            Toast.makeText(mac_cliente.this, "Cliente cargado con exito", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarCliente()
    {


        btnGuardarClienteCRU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtNombreClienteCRU.getText().toString();
                String domicilio = txtDireccionClienteCRU.getText().toString();
                String RFC = txtRFCCRU.getText().toString();
                String telefono = txtTelefonoClienteCRU.getText().toString();
                cliente Cliente = new cliente(name, domicilio, estado_cliente.sinConfirmar);
                clienteSQL db = new clienteSQL(getApplicationContext());
                db.insert(Cliente);
                Toast.makeText(mac_cliente.this, "Guardado", Toast.LENGTH_SHORT).show();
            }
        });
        btnCargarClienteCRU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clienteSQL db = new clienteSQL(getApplicationContext());
                //List<cliente> Cliente;
                //Cliente = db.getCliente(1,1);
                cliente Cliente = db.getCliente(1);
                Toast.makeText(mac_cliente.this, Cliente.getNombre().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_cliente);

        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializarComponentes();


        listRegimenFiscal = new ArrayList<>();

        listRegimenFiscal.add(new regimenFiscal(1, "General de personas fisicas"));
        listRegimenFiscal.add(new regimenFiscal(2, "General de personas morales"));
        listRegimenFiscal.add(new regimenFiscal(3, "General 3"));
        listRegimenFiscal.add(new regimenFiscal(4, "General 4"));
        listRegimenFiscal.add(new regimenFiscal(5, "General 5"));
        listRegimenFiscal.add(new regimenFiscal(6, "General 6"));

        ArrayList<String> listaNombresRF = new ArrayList<>();

        for(int i = 0; i < listRegimenFiscal.size(); i++)
            listaNombresRF.add(listRegimenFiscal.get(i).getRegimenFiscal());


        //Implemento el adapter con el contexto, layout, listaFrutas
        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listaNombresRF);
        //Cargo el spinner con los datos
        cbxRegimenFiscal.setAdapter(comboAdapter);

        guardarCliente();
        verificarDatos();

    }
}
