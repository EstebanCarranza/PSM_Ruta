package com.twicky.estebancarranza.reparto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.productoSQL;
import com.twicky.estebancarranza.reparto.estaticos.defaultData;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.webservice.NetCallback;
import com.twicky.estebancarranza.reparto.webservice.networking;

import org.w3c.dom.Text;

public class mac_test_and_other_things extends AppCompatActivity  {

    Button btnCrearClientes;
    Button btnCrearProductos;
    Button btnCrearRutas;

    Button btnBorrarClientes; //resetea los valores default
    Button btnBorrarProductos; //resetea los valores default
    Button btnBorrarRutas;

    TextView txtLatitude;
    TextView txtLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_test_and_other_things);

        btnCrearClientes = (Button) findViewById(R.id.btnCrearClientes);

        btnBorrarProductos = (Button) findViewById(R.id.btnBorrarProductos);
        btnBorrarClientes = (Button) findViewById(R.id.btnBorrarClientes);
        btnBorrarRutas = (Button) findViewById(R.id.btnBorrarRutas);
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);

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


        btnBorrarRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            Double latitude = Double.parseDouble(txtLatitude.getText().toString());
            Double longitude = Double.parseDouble(txtLongitude.getText().toString());


                new networking(mac_test_and_other_things.this, new NetCallback() {
                    @Override
                    public void onWorkFinish(Object data) {
                        final String address = (String) data;

                        // Es imposible modificar directamente cualquier vista del activity fuera del hilo principal donde
                        // estas se ejecutan (El hilo de UI). Por tal razon es necesario hacer uso del metodo "runOnUithread"
                        // para "forzar" a correr el bloque de codigo dentro del hilo de la UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Todoo el codigo dentro de este metodo se ejecuta dentro del hilo principal o hilo de la UI
                                Toast.makeText(mac_test_and_other_things.this, address, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).execute("getAddress", new LatLng(latitude, longitude));
            }
        });

    }

}
