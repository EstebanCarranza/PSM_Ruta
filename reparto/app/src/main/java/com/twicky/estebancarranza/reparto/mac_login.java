package com.twicky.estebancarranza.reparto;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mac_login extends AppCompatActivity {

    public void BotonAceptar (View vista){

        Toast.makeText(this, "entro", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, mac_registro.class);
        startActivity(intent);
    }

    EditText txtCorreo;
    EditText txtContrasenia;
    Button btnLogin;
    Button btnRegistro;

    private void Login()
    {
        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Toast.makeText(getApplicationContext(), "va a entrar", Toast.LENGTH_SHORT).show();
            // BotonAceptar(view);

            if(txtCorreo.getText().toString().isEmpty())
                Toast.makeText(mac_login.this, "Escribe tu correo o contraseña", Toast.LENGTH_SHORT).show();
            else
                switch(txtCorreo.getText().toString()) {
                    case "sin@ruta": {
                        Intent intent = new Intent(mac_login.this, mac_home_sin_ruta.class);
                        startActivity(intent);
                    }
                    break;
                    case "con@ruta": {
                        Intent intent = new Intent(mac_login.this, mac_home_con_ruta.class);
                        startActivity(intent);
                    }
                    break;
                    default:
                        Toast.makeText(mac_login.this, "Comando no reconocido", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_login);

        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtContrasenia = (EditText) findViewById(R.id.txtContrasenia);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);

        Login();

    }
}
