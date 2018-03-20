package com.twicky.estebancarranza.reparto;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
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
}
