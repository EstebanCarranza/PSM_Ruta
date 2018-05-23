package com.twicky.estebancarranza.reparto.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.estaticos.global;
import com.twicky.estebancarranza.reparto.models.vendedor;
import com.twicky.estebancarranza.reparto.sharedPreferences.login;

import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensorManager;
import static com.twicky.estebancarranza.reparto.sensores.luz.ln;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensor;
import static com.twicky.estebancarranza.reparto.sensores.luz.intensidad;

public class MainActivity extends AppCompatActivity {


    public void IrALogin (View vista){

//        Toast.makeText(this, "entro", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getApplicationContext(), mac_login.class);
        startActivity(intent);
    }
    public void IrARegistro (View vista){

        //Toast.makeText(this, "entro", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getApplicationContext(), mac_registro.class);
        startActivity(intent);
    }

    Button btnLogin;
    Button btnRegistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        ln = (LinearLayout) findViewById(R.id.lnlBackground);
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        //Crear vendedor para loguearse
        vendedor vendedor = null;
        //verifica si existe el usuario en shared preferences
        vendedor = login.getLoginAuto(getApplicationContext());
        if(vendedor != null)
        {
            vendedor = mac_login.localLogin(getApplicationContext(), vendedor.getCorreo(), vendedor.getContrasenia(), true);
        }
        if(vendedor != null)
        {
            Toast.makeText(this, "Iniciaste sesión con shared preferences " + vendedor.getNombres(), Toast.LENGTH_SHORT).show();
            global.setVendedor(vendedor);
        }



        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(), "va a entrar", Toast.LENGTH_SHORT).show();
                    IrALogin(view);
                }
            }
        );
        btnRegistro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(), "va a entrar", Toast.LENGTH_SHORT).show();
                    IrARegistro(view);
                }
            }
        );
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
