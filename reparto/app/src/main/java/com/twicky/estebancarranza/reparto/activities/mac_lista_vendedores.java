package com.twicky.estebancarranza.reparto.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;

import com.twicky.estebancarranza.reparto.adapters.list_vendedores;
import com.twicky.estebancarranza.reparto.database.helpers.vendedorSQL;
import com.twicky.estebancarranza.reparto.models.vendedor;


import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.estaticos.global.setVendedorMSG;
import static com.twicky.estebancarranza.reparto.sensores.luz.intensidad;
import static com.twicky.estebancarranza.reparto.sensores.luz.ln;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensor;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensorManager;

public class mac_lista_vendedores extends AppCompatActivity {

    ArrayList<vendedor> listVendedor;
    RecyclerView recycler;
    list_vendedores adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_lista_vendedores);

        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ln = (LinearLayout) findViewById(R.id.lnlBackground);
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        construirRecycler();

    }

    private void construirRecycler() {
        recycler = (RecyclerView) findViewById(R.id.lstListaVendedores);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listVendedor = new ArrayList<vendedor>();

        vendedorSQL db = new vendedorSQL(getApplicationContext());

        listVendedor = db.getVendedores(1, 1);
        adapter = new list_vendedores(listVendedor);

        recycler.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = listVendedor.get(recycler.getChildAdapterPosition(v)).getIdVendedor();
                
                vendedorSQL db = new vendedorSQL(getApplicationContext());  
                vendedor vendedor = db.getVendedor(id);
                
                if(vendedor != null)
                {
                 //   Toast.makeText(mac_lista_vendedores.this, vendedor.getNombres(), Toast.LENGTH_SHORT).show();
                    setVendedorMSG(vendedor);
                    startActivity(new Intent(mac_lista_vendedores.this, mac_mensajes.class));
                }
                else
                    Toast.makeText(mac_lista_vendedores.this, "No data", Toast.LENGTH_SHORT).show();
                
                //adapter.notifyDataSetChanged();
            }
        });

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
