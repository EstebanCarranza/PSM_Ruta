package com.twicky.estebancarranza.reparto.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.adapters.list_buttons;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.estaticos.ID;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.sharedPreferences.login;
import com.twicky.estebancarranza.reparto.webservice.NetCallback;
import com.twicky.estebancarranza.reparto.webservice.networking;

import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.sensores.luz.intensidad;
import static com.twicky.estebancarranza.reparto.sensores.luz.ln;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensor;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensorManager;

public class mac_configuracion extends AppCompatActivity {


    ArrayList<list_buttons.Boton> menu;
    RecyclerView recycler;
    com.twicky.estebancarranza.reparto.models.custom_parameter custom_parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_configuracion);

        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ln = (LinearLayout) findViewById(R.id.lnlBackground);
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        construirRecycler();
    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstConfiguracion);
        menu = new ArrayList<list_buttons.Boton>();

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

       // menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_CambiarTemaTitle), getString(R.string.strCONFIG_CambiarTemaDesc), ID.CONFIG.btnCambiarTema));
        menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_CambiarPrecisionTitle),getString(R.string.strCONFIG_CambiarPrecisionDesc), ID.CONFIG.btnCambiarPrecision));
    //    menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_PINTitle), getString(R.string.strCONFIG_PINDesc), ID.CONFIG.btnAgregarPIN));
        menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_DatosUsuarioTitle), getString(R.string.strCONFIG_DatosUsuarioDesc), ID.CONFIG.btnVerDatosPersonales));
        menu.add(new list_buttons.Boton(getString(R.string.btnSincronizarTitle), getString(R.string.btnSincronizarDetalle), ID.CONFIG.btnSincronizar));
        menu.add(new list_buttons.Boton(getString(R.string.btnObtenerClientesTitle), getString(R.string.btnObtenerClientesDetalle), ID.CONFIG.btnObtenerClientes));
        menu.add(new list_buttons.Boton(getString(R.string.btnCambiarHOSTTitle), getString(R.string.btnCambiarHOSTDetail), ID.CONFIG.btnCambiarHOST));

        menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_CerrarSesionTitle), getString(R.string.strCONFIG_CerrarSesionDesc), ID.CONFIG.btnCerrarSesion));



        ArrayList<Object> objListBotones = (ArrayList<Object>)(ArrayList<?>)(menu);
        list_buttons adapter = new list_buttons(objListBotones);

        if(custom_parameter != null) {
            if (custom_parameter.getTipoLayout() != null)
                adapter = new list_buttons(objListBotones);
            else
                adapter = new list_buttons(objListBotones);
        }
        else
            adapter = new list_buttons(objListBotones);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (menu.get(recycler.getChildAdapterPosition(view)).getIdBoton()) {

                    case ID.CONFIG.btnCambiarTema:
                        Intent irATema = new Intent(mac_configuracion.this, mac_tema.class);
                        startActivity(irATema);
                    break;
                    case ID.CONFIG.btnCambiarPrecision:
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    break;
                    case ID.CONFIG.btnAgregarPIN:
                        startActivity(new Intent(mac_configuracion.this, mac_pin.class));
                    break;
                    case ID.CONFIG.btnVerDatosPersonales:
                        startActivity(new Intent(mac_configuracion.this, mac_datos_personales.class));
                    break;
                    case ID.CONFIG.btnCerrarSesion:
                        login.setLoginAuto(getApplicationContext(), false, "", "");
                        startActivity(new Intent(mac_configuracion.this, MainActivity.class));
                    break;

                    case ID.CONFIG.btnCambiarHOST:
                    {
                        startActivity(new Intent(mac_configuracion.this, mac_config_host.class));
                    }
                    break;


                    case ID.CONFIG.btnObtenerClientes:
                    {
                        new networking(mac_configuracion.this, new NetCallback() {

                            @Override
                            public void onWorkFinish(Object data) {
                                //final ArrayList<results> results = (ArrayList<results>) data;
                                final ArrayList<cliente> clientes = (ArrayList<cliente>) data;

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(clientes.size() > 0) {
                                            clienteSQL db = new clienteSQL(getApplicationContext());
                                            db.insertIfNotExists(clientes);
                                            Toast.makeText(mac_configuracion.this, "Obteniendo clientes sincronizados...", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }
                        }).execute("getSynchronizeClient");
                    }
                    break;

                    case ID.CONFIG.btnSincronizar: {

                        clienteSQL db = new clienteSQL(getApplicationContext());
                        final ArrayList<cliente> clientes = db.getCliente(0, 0);
                        int i = 0;
                        if(clientes.size()> 0) {

                            new networking(mac_configuracion.this, new NetCallback() {

                                @Override
                                public void onWorkFinish(Object data) {
                                    //final ArrayList<results> results = (ArrayList<results>) data;
                                    final String results = (String) data;

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            Toast.makeText(mac_configuracion.this, results, Toast.LENGTH_SHORT).show();


                                        }
                                    });
                                }
                            }).execute("SynchronizeClient", clientes);
                        }


                    }
                    break;
                }
            }
        });



        recycler.setAdapter(adapter);




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
