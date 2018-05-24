package com.twicky.estebancarranza.reparto.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.twicky.estebancarranza.reparto.webservice.NetCallback;
import com.twicky.estebancarranza.reparto.webservice.networking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.twicky.estebancarranza.reparto.sensores.luz.intensidad;
import static com.twicky.estebancarranza.reparto.sensores.luz.ln;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensor;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensorManager;


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
    boolean editMode = false;
    cliente editClient;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100) {
            if(resultCode == Activity.RESULT_OK){

                String sLatitude = data.getStringExtra("Latitude");

                Double latitudeX =  Double.parseDouble(data.getStringExtra("Latitude"));
                Double longitudeX =  Double.parseDouble(data.getStringExtra("Longitude"));

                latLngCliente = new LatLng(latitudeX, longitudeX);
               // Toast.makeText(this, "LatLng: " + String.valueOf(latLngCliente.latitude) + "," + latLngCliente.longitude, Toast.LENGTH_SHORT).show();

                txtLatitude.setText(String.valueOf(latitudeX));
                txtLongitude.setText(String.valueOf(longitudeX));

                Double latitude = Double.parseDouble(txtLatitude.getText().toString());
                Double longitude = Double.parseDouble(txtLongitude.getText().toString());


                new networking(mac_cliente.this, new NetCallback() {
                    @Override
                    public void onWorkFinish(Object data) {
                        final String address = (String) data;

                        // Es imposible modificar directamente cualquier vista del activity fuera del hilo principal donde
                        // estas se ejecutan (El hilo de UI). Por tal razon es necesario hacer uso del metodo "runOnUithread"
                        // para "forzar" a correr el bloque de codigo dentro del hilo de la UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Todoo el codigo dentro de este metodo se ejecuta dentro del hilo principal o hilo de la UI
                                //Toast.makeText(mac_cliente.this, address, Toast.LENGTH_SHORT).show();
                                txtDireccionClienteCRU.setText(address);
                            }
                        });
                    }
                }).execute("getAddress", new LatLng(latitude, longitude));

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
            editClient = db.getCliente(idCliente);
            if(editClient != null)
            {

                txtRFCCRU.setText(editClient.getRfc());
                txtNombreClienteCRU.setText(editClient.getNombre());
                txtDireccionClienteCRU.setText(editClient.getDomicilio());
                txtTelefonoClienteCRU.setText(editClient.getTelefono());
                txtLatitude.setText(String.valueOf(editClient.getCoordenada().latitude));
                txtLongitude.setText(String.valueOf(editClient.getCoordenada().longitude));
                cbxRegimenFiscal.setSelection(editClient.getIdRegimenFiscal());
                editMode = true;
                txtRFCCRU.setEnabled(false);
                Toast.makeText(mac_cliente.this, "Cliente cargado con exito", Toast.LENGTH_SHORT).show();    
            }
            else
                Toast.makeText(this, "No se encontró el cliente seleccionado", Toast.LENGTH_SHORT).show();
            
        }
    }

    private void guardarCliente()
    {


        btnGuardarClienteCRU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    cliente Cliente = new cliente();
                    Cliente.setRfc(txtRFCCRU.getText().toString());
                    Cliente.setNombre(txtNombreClienteCRU.getText().toString());
                    Cliente.setDomicilio(txtDireccionClienteCRU.getText().toString());
                    Cliente.setTelefono(txtTelefonoClienteCRU.getText().toString());
                    Cliente.setEstadoActual(estado_cliente.sinConfirmar);
                    Cliente.setIdRegimenFiscal((int) cbxRegimenFiscal.getSelectedItemId());
                    Cliente.setCoordenada(latLngCliente);
                    Cliente.setFechaUltimaModificacion(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                    if(Cliente.validateAllDataNoID())
                    {
                        clienteSQL db = new clienteSQL(getApplicationContext());
                        if(editMode) {
                            db.update(Cliente);
                            Toast.makeText(mac_cliente.this, "Cliente actualizado con éxito", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            db.insert(Cliente);
                            Toast.makeText(mac_cliente.this, "Cliente agregado con éxito", Toast.LENGTH_SHORT).show();
                        }

                        finish();
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

        ln = (LinearLayout) findViewById(R.id.lnlBackground);
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);


        inicializarComponentes();
/*
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.map, );
        mFragmentTransaction.commit();
*/

        listRegimenFiscal = new ArrayList<>();

        listRegimenFiscal.add(new regimenFiscal(1, "General de Ley Personas Morales"));
        listRegimenFiscal.add(new regimenFiscal(2, "Personas Morales con Fines no Lucrativos"));
        listRegimenFiscal.add(new regimenFiscal(3, "Sueldos y Salarios e Ingresos Asimilados a Salarios"));
        listRegimenFiscal.add(new regimenFiscal(4, "Arrendamiento"));
        listRegimenFiscal.add(new regimenFiscal(5, "Demás ingresos"));
        listRegimenFiscal.add(new regimenFiscal(6, "Consolidación"));
        listRegimenFiscal.add(new regimenFiscal(7, "Residentes en el Extranjero sin Establecimiento Permanente en México"));
        listRegimenFiscal.add(new regimenFiscal(8, "Ingresos por Dividendos (socios y accionistas)"));
        listRegimenFiscal.add(new regimenFiscal(9, "Personas Físicas con Actividades Empresariales y Profesionales"));
        listRegimenFiscal.add(new regimenFiscal(10, "Ingresos por intereses"));
        listRegimenFiscal.add(new regimenFiscal(11, "Sin obligaciones fiscales"));
        listRegimenFiscal.add(new regimenFiscal(12, "Sociedades Cooperativas de Producción que optan por diferir sus ingresos"));
        listRegimenFiscal.add(new regimenFiscal(13, "Incorporación Fiscal"));
        listRegimenFiscal.add(new regimenFiscal(14, "Actividades Agrícolas, Ganaderas, Silvícolas y Pesqueras"));
        listRegimenFiscal.add(new regimenFiscal(15, "Opcional para Grupos de Sociedades"));
        listRegimenFiscal.add(new regimenFiscal(16, "Coordinados"));
        listRegimenFiscal.add(new regimenFiscal(17, "Hidrocarburos"));
        listRegimenFiscal.add(new regimenFiscal(18, "Régimen de Enajenación o Adquisición de Bienes"));
        listRegimenFiscal.add(new regimenFiscal(19, "De los Regímenes Fiscales Preferentes y de las Empresas Multinacionales"));
        listRegimenFiscal.add(new regimenFiscal(20, "Enajenación de acciones en bolsa de valores"));
        listRegimenFiscal.add(new regimenFiscal(21, "Régimen de los ingresos por obtención de premios"));


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


    @Override
    protected void onPause() {

        super.onPause();
        //Siempre suspender el sensor
        m_sensorManager.unregisterListener(m_sensorEventListener);
    }
    @Override
    protected void onResume() {
        super.onResume();
        m_sensorManager.registerListener(m_sensorEventListener,
                m_sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    SensorEventListener m_sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            intensidad = event.values[0];

            if(intensidad < 10)
            {
                ln.setBackgroundColor(getResources().getColor(R.color.DarkTheme_backgroundColor));
            }
            else
            {
                ln.setBackgroundColor(getResources().getColor(R.color.LightTheme_backgroundColor));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

    };

}
