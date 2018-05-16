package com.twicky.estebancarranza.reparto.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.vendedorSQL;
import com.twicky.estebancarranza.reparto.estaticos.defaultData;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.vendedor;
import com.twicky.estebancarranza.reparto.webservice.NetCallback;
import com.twicky.estebancarranza.reparto.webservice.networking;

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

    private void remoteLogin()
    {
        String correo = txtCorreo.getText().toString();
        String contrasenia = txtContrasenia.getText().toString();

        new networking(mac_login.this, new NetCallback() {
            @Override
            public void onWorkFinish(Object data) {
                final vendedor result = (vendedor) data;

                // Es imposible modificar directamente cualquier vista del activity fuera del hilo principal donde
                // estas se ejecutan (El hilo de UI). Por tal razon es necesario hacer uso del metodo "runOnUithread"
                // para "forzar" a correr el bloque de codigo dentro del hilo de la UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Todoo el codigo dentro de este metodo se ejecuta dentro del hilo principal o hilo de la UI
                        if(result != null)
                        {
                            Toast.makeText(mac_login.this, "Iniciaste sesión correctamente", Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder builder = new AlertDialog.Builder(mac_login.this);

                            builder.setTitle("Iniciar sesión");
                            builder.setMessage("¿Quieres guardar el inicio de sesión?");

                            builder.setPositiveButton("Guardar sesión", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mac_login.this, "Sesión guardada :)", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    vendedorSQL db = new vendedorSQL(getApplicationContext());
                                    vendedor vendedor = new vendedor();
                                    vendedor = result;
                                    vendedor.setLoginActive(1);
                                    db.insert(vendedor);
                                }
                            });

                            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(mac_login.this, "Sesion no guardada, la proxima vez tendrás que iniciar sesión", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();


                        }
                        else
                        {
                            Toast.makeText(mac_login.this, "No estás registrado :(", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).execute("RemoteLogin", correo, contrasenia);
    }
    private vendedor localLogin()
    {
        vendedorSQL db = new vendedorSQL(getApplicationContext());
        vendedor vendedor =db.validarInicioSesion(txtCorreo.getText().toString(), txtContrasenia.getText().toString());

        return vendedor;

    }

    private void Login()
    {
        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Toast.makeText(getApplicationContext(), "va a entrar", Toast.LENGTH_SHORT).show();
            // BotonAceptar(view);

            if(txtCorreo.getText().toString().isEmpty())
                Toast.makeText(mac_login.this, "Escribe tu correo o contraseña", Toast.LENGTH_SHORT).show();
            else {


              {
                switch (txtCorreo.getText().toString()) {
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
                       vendedor vendedor = localLogin();
                        if(vendedor != null)
                           remoteLogin();
                        else
                            Toast.makeText(mac_login.this, "Inicio se sesión local", Toast.LENGTH_SHORT).show();
                    break;
                }
            }


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
