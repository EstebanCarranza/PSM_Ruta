package com.twicky.estebancarranza.reparto.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.vendedorSQL;
import com.twicky.estebancarranza.reparto.models.vendedor;
import com.twicky.estebancarranza.reparto.sharedPreferences.login;

import org.w3c.dom.Text;

import static com.twicky.estebancarranza.reparto.estaticos.global.setVendedor;
import static com.twicky.estebancarranza.reparto.sensores.luz.intensidad;
import static com.twicky.estebancarranza.reparto.sensores.luz.ln;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensor;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensorManager;

public class mac_registro extends AppCompatActivity {

    Button btnLogin;
    Button btnLoginFacebook;
    TextView txtEmail;
    TextView txtPassword01;
    TextView txtPassword02;
    TextView txtNombre;
    TextView txtAppat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_registro);

        btnLoginFacebook = (Button) findViewById(R.id.btnLoginFacebook);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtEmail = (TextView) findViewById(R.id.email);
        txtPassword01 = (TextView) findViewById(R.id.txtPassword01);
        txtPassword02 = (TextView) findViewById(R.id.txtPassword02);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtAppat = (TextView) findViewById(R.id.txtAppat);

        ln = (LinearLayout) findViewById(R.id.lnlBackground);
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mac_registro.this, mac_test_and_other_things.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtPassword01.getText().toString().equals(txtPassword02.getText().toString()))
                {
                    vendedor vendedor = new vendedor();
                    vendedor.setCorreo(txtEmail.getText().toString());
                    vendedor.setContrasenia(txtPassword01.getText().toString());
                    vendedor.setNombres(txtNombre.getText().toString());
                    vendedor.setAppat(txtAppat.getText().toString());
                    vendedorSQL db = new vendedorSQL(getApplicationContext());
                    if(db.insert(vendedor) > 0) {
                        Toast.makeText(mac_registro.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                       // saveLogin(vendedor);
                        finish();
                    }
                }else
                    Toast.makeText(mac_registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void saveLogin(final vendedor vendedor)
    {
        if(vendedor != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(mac_registro.this);

            builder.setTitle("Guardar inicio de sesion");
            builder.setMessage("¿Quieres guardar tu inicio de sesión? \n Iniciarás más rapido la aplicación la proxima vez que la abras");

            builder.setPositiveButton("Si, guardar inicio de sesión", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(mac_registro.this, "Elegiste guardar inicio de sesión", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    login.setLoginAuto(getApplicationContext(), true, vendedor.getCorreo(), vendedor.getContrasenia());
                    setVendedor(vendedor);
                    startActivity(new Intent(mac_registro.this, mac_home_con_ruta.class));
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(mac_registro.this, "No guardaste el inicio de sesión, tendrás que agregar el usuario y contraseña la proxima vez que inicies la app", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    setVendedor(vendedor);
                    startActivity(new Intent(mac_registro.this, mac_home_con_ruta.class));
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
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
