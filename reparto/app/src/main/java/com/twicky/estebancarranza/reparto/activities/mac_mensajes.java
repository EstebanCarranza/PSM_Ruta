package com.twicky.estebancarranza.reparto.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.adapters.list_messages;
import com.twicky.estebancarranza.reparto.database.helpers.mensajeSQL;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.mensaje;

import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.estaticos.global.getVendedor;
import static com.twicky.estebancarranza.reparto.estaticos.global.getVendedorMSG;
import static com.twicky.estebancarranza.reparto.sensores.luz.intensidad;
import static com.twicky.estebancarranza.reparto.sensores.luz.ln;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensor;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensorManager;

public class mac_mensajes extends AppCompatActivity {

    RecyclerView recycler;
    list_messages adapter;
    ArrayList<mensaje> mensajes;
    Button btnEnviarMSG;
    int indexMSG = 0;
    TextView txtEnviarMSG;
    android.support.constraint.ConstraintLayout cln;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_mensajes);


        getSupportActionBar().setTitle("Estás hablando con " +  getVendedorMSG().getNombres());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cln = (android.support.constraint.ConstraintLayout) findViewById(R.id.lnlBackground);
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        btnEnviarMSG = (Button) findViewById(R.id.btnEnviarMSG);
        txtEnviarMSG = (TextView) findViewById(R.id.txtEnviarMSG);
        construirRecycler();
        boton_enviar();
    }

    private void boton_enviar()
    {
        btnEnviarMSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensajeSQL db = new mensajeSQL(getApplicationContext());
                mensaje msg = new mensaje(indexMSG, txtEnviarMSG.getText().toString(), true);
                msg.setIdVendedor1(getVendedor().getIdVendedor());
                msg.setIdVendedor2(getVendedorMSG().getIdVendedor());
                msg.setMensaje(txtEnviarMSG.getText().toString());

                if(db.insert(msg)>0)
                {
                    mensajes.add(msg);
                }

                adapter.notifyDataSetChanged();
                txtEnviarMSG.setText("");


            }
        });
    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstMessages);
        mensajes = new ArrayList<mensaje>();

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mensajeSQL db = new mensajeSQL(getApplicationContext());
        mensajes = db.getmensajees(getVendedor().getIdVendedor());

        /*
        indexMSG += 1;
        mensajes.add(new mensaje(indexMSG, "Hola", true));
        indexMSG += 1;
        mensajes.add(new mensaje(indexMSG, "Que paso?", false));
        indexMSG += 1;
        mensajes.add(new mensaje(indexMSG, "Tengo una duda", true));*/

        custom_color colorMe = new custom_color(getResources().getColor(R.color.cardview_dark_background));
        custom_color colorOther = new custom_color(getResources().getColor(R.color.text_color2));

        adapter = new list_messages(
                mensajes,
                    colorMe,
                    colorOther
                );


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
                cln.setBackgroundColor(getResources().getColor(R.color.DarkTheme_backgroundColor));
            }
            else
            {
                cln.setBackgroundColor(getResources().getColor(R.color.LightTheme_backgroundColor));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

    };
}
