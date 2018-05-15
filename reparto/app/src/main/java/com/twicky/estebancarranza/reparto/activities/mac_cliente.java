package com.twicky.estebancarranza.reparto.activities;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.models.regimenFiscal;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import java.util.ArrayList;


public class mac_cliente extends AppCompatActivity {//implements OnMapReadyCallback {

    ArrayList<regimenFiscal> listRegimenFiscal;
    ArrayAdapter<String> comboAdapter;
    Spinner cbxRegimenFiscal;
    Button btnGuardarClienteCRU;
    TextView txtRFCCRU;
    TextView txtNombreClienteCRU;
    TextView txtDireccionClienteCRU;
    TextView txtTelefonoClienteCRU;
    Button btnCargarClienteCRU;
    Button btnAbrirMapa;
    LatLng latLngCliente;
    EditText txtLatitude;
    EditText txtLongitude;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100) {
            if(resultCode == Activity.RESULT_OK){

                String sLatitude = data.getStringExtra("Latitude");

                Double latitudeX =  Double.parseDouble(data.getStringExtra("Latitude"));
                Double longitudeX =  Double.parseDouble(data.getStringExtra("Longitude"));

                latLngCliente = new LatLng(latitudeX, longitudeX);
                Toast.makeText(this, "LatLng: " + String.valueOf(latLngCliente.latitude) + "," + latLngCliente.longitude, Toast.LENGTH_SHORT).show();

                txtLatitude.setText(String.valueOf(latitudeX));
                txtLongitude.setText(String.valueOf(longitudeX));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Toast.makeText(this, "No se eligió punto en el mapa", Toast.LENGTH_SHORT).show();
            }
        }
    }//onActivityResult

    private void inicializarComponentes()
    {
        btnGuardarClienteCRU = (Button) findViewById(R.id.btnGuardarClienteCRU);
        txtRFCCRU = (TextView) findViewById(R.id.txtRFCCRU);
        txtNombreClienteCRU = (TextView) findViewById(R.id.txtNombreClienteCRU);
        txtDireccionClienteCRU = (TextView) findViewById(R.id.txtDireccionClienteCRU);
        cbxRegimenFiscal = (Spinner) findViewById(R.id.cbxRegimenFiscal);
        txtTelefonoClienteCRU = (TextView) findViewById(R.id.txtTelefonoClienteCRU);
        btnCargarClienteCRU = (Button) findViewById(R.id.btnCargarClienteCRU);
        btnAbrirMapa = (Button) findViewById(R.id.btnAbrirMapa);
        //Toast.makeText(this, db.create.db_psm, Toast.LENGTH_SHORT).show();
        txtLatitude = (EditText) findViewById(R.id.txtLatitude);
        txtLongitude = (EditText) findViewById(R.id.txtLongitude);


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

                cliente Cliente = new cliente();
                Cliente.setNombre(txtNombreClienteCRU.getText().toString());
                Cliente.setDomicilio(txtDireccionClienteCRU.getText().toString());
                Cliente.setRfc(txtRFCCRU.getText().toString());
                Cliente.setTelefono(txtTelefonoClienteCRU.getText().toString());
                Cliente.setEstadoActual(estado_cliente.sinConfirmar);
                Cliente.setIdRegimenFiscal((int) cbxRegimenFiscal.getSelectedItemId());
                Cliente.setCoordenada(latLngCliente);

                if(Cliente.validateAllDataNoID())
                {
                    clienteSQL db = new clienteSQL(getApplicationContext());
                    db.insert(Cliente);
                    Toast.makeText(mac_cliente.this, "Guardado", Toast.LENGTH_SHORT).show();    
                }
                else
                    Toast.makeText(mac_cliente.this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
                
            }
        });
        btnCargarClienteCRU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*clienteSQL db = new clienteSQL(getApplicationContext());
                //List<cliente> Cliente;
                //Cliente = db.getCliente(1,1);
                cliente Cliente = db.getCliente(1);
                Toast.makeText(mac_cliente.this, Cliente.getNombre().toString(), Toast.LENGTH_SHORT).show();
                */
                Toast.makeText(mac_cliente.this, String.valueOf(cbxRegimenFiscal.getSelectedItemId()), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void abrirMapa()
    {
        btnAbrirMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirMapa = new Intent(mac_cliente.this, frg_getUbicacionCliente.class);
                /*abrirMapa.putExtra("latitude", 100);
                abrirMapa.putExtra("longitud", 101);*/
                startActivityForResult(abrirMapa, 100);
            }
        });
    }

    /*private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_cliente);

        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializarComponentes();
/*
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.map, );
        mFragmentTransaction.commit();
*/

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
        abrirMapa();

    }




}
