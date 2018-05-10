package com.twicky.estebancarranza.reparto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.vendedorSQL;
import com.twicky.estebancarranza.reparto.estaticos.defaultData;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.vendedor;

import java.util.ArrayList;

public class mac_login extends AppCompatActivity {

    public void BotonAceptar (View vista){

        Toast.makeText(this, "entro", Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this, mac_registro.class);
        startActivity(intent);
    }

    EditText txtCorreo;
    EditText txtContrasenia;
    Button btnLogin;
    Button btnLoginFacebook;

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
    private void RegistrarVendedor()
    {
        Toast.makeText(mac_login.this, "Registrando usuario", Toast.LENGTH_SHORT).show();
        vendedorSQL db = new vendedorSQL(getApplicationContext());

        vendedor nuevo = new vendedor();

        nuevo.setNombres("Esteban");
        nuevo.setAppat("Carranza");
        nuevo.setApmat("Delgado");
        nuevo.setCorreo("esteban.carranza@outlook.com");
        nuevo.setContrasenia("1234");
        nuevo.setFechNac("1995-06-21");

        long id = db.insert(nuevo);

        if(id > 0)
            Toast.makeText(mac_login.this, "El id para " + nuevo.getCorreo() + " es: " + Long.toString(id), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mac_login.this, "No se pudo agregar al nuevo vendedor", Toast.LENGTH_SHORT).show();
    }
    private void LoginSQL()
    {
        if(!txtCorreo.getText().toString().isEmpty() && !txtContrasenia.getText().toString().isEmpty()) {
            Toast.makeText(mac_login.this, "Intentando iniciar sesión", Toast.LENGTH_SHORT).show();
            vendedorSQL db = new vendedorSQL(getApplicationContext());
            vendedor nuevo = new vendedor();
            nuevo = db.validarInicioSesion(txtCorreo.getText().toString(), txtContrasenia.getText().toString());

            if (nuevo != null)
                Toast.makeText(mac_login.this, "Iniciaste sesión :D", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(mac_login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(mac_login.this, "Campos vacíos, llenar la información y voler a intentarlo", Toast.LENGTH_SHORT).show();
    }
    private void LoginFacebook()
    {
        btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custom_color colorConfirmado = new custom_color(getResources().getColor(R.color.green));
                custom_color colorNoConfirmado = new custom_color(getResources().getColor(R.color.red));

               defaultData.InsertProductos(getApplicationContext(), colorConfirmado, colorNoConfirmado);
                Toast.makeText(mac_login.this, "Productos insertados", Toast.LENGTH_SHORT).show();

                defaultData.InsertCliente(getApplicationContext());
                Toast.makeText(mac_login.this, "Clientes insertados", Toast.LENGTH_SHORT).show();
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
        btnLoginFacebook = (Button) findViewById(R.id.btnLoginFacebook);

        Login();
        LoginFacebook();

    }
}
