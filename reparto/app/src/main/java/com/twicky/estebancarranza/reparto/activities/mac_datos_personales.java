package com.twicky.estebancarranza.reparto.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.vendedorSQL;
import com.twicky.estebancarranza.reparto.models.vendedor;

import org.w3c.dom.Text;

import static com.twicky.estebancarranza.reparto.estaticos.global.getVendedor;
import static com.twicky.estebancarranza.reparto.sensores.luz.intensidad;
import static com.twicky.estebancarranza.reparto.sensores.luz.ln;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensor;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensorManager;

public class mac_datos_personales extends AppCompatActivity {

    TextView lblCorreo;
    EditText txtNombre;
    EditText txtAppat;
    EditText txtApmat;
    EditText txtFechNac;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_datos_personales);
        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ln = (LinearLayout) findViewById(R.id.lnlBackground);
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        lblCorreo = (TextView) findViewById(R.id.lblCorreo);
        txtNombre = (EditText) findViewById(R.id.txtNombres);
        txtAppat = (EditText) findViewById(R.id.txtAppat);
        txtApmat = (EditText) findViewById(R.id.txtApmat);
        txtFechNac = (EditText) findViewById(R.id.txtFechNac);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        

        lblCorreo.setText(getVendedor().getCorreo().toString());
        txtNombre.setText(getVendedor().getNombres().toString());
        txtAppat.setText(getVendedor().getAppat().toString());
        txtApmat.setText(getVendedor().getApmat().toString());
        txtFechNac.setText(getVendedor().getFechNac().toString());
        

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendedorSQL db = new vendedorSQL(getApplicationContext());
                vendedor vendedor = new vendedor();
                vendedor = getVendedor();
                vendedor.setNombres(txtNombre.getText().toString());
                vendedor.setAppat(txtAppat.getText().toString());
                vendedor.setApmat(txtApmat.getText().toString());
                vendedor.setFechNac(txtFechNac.getText().toString());
                if(db.updatePersonalData(vendedor)> 0)
                    Toast.makeText(mac_datos_personales.this, "Se actualizaron los cambios correctamente", Toast.LENGTH_SHORT).show();
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
