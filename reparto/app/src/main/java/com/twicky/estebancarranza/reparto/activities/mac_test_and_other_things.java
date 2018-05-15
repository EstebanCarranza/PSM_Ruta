package com.twicky.estebancarranza.reparto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.productoSQL;
import com.twicky.estebancarranza.reparto.estaticos.defaultData;
import com.twicky.estebancarranza.reparto.models.custom_color;

public class mac_test_and_other_things extends AppCompatActivity  {

    Button btnCrearClientes;
    Button btnCrearProductos;
    Button btnCrearRutas;

    Button btnBorrarClientes; //resetea los valores default
    Button btnBorrarProductos; //resetea los valores default
    Button btnBorrarRutas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_test_and_other_things);

        btnCrearClientes = (Button) findViewById(R.id.btnCrearClientes);

        btnBorrarProductos = (Button) findViewById(R.id.btnBorrarProductos);
        btnBorrarClientes = (Button) findViewById(R.id.btnBorrarClientes);

        btnBorrarProductos.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     custom_color colorConfirmado = new custom_color(getResources().getColor(R.color.green));
                     custom_color colorNoConfirmado = new custom_color(getResources().getColor(R.color.red));

                     defaultData.InsertProductos(getApplicationContext(), colorConfirmado, colorNoConfirmado);
                 }
             }
        );

        btnBorrarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                defaultData.InsertCliente(getApplicationContext());
            }
        });

        btnCrearClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (mac_test_and_other_things.this, mac_cliente.class));
            }
        });

    }

}
