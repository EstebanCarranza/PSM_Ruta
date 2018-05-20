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
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.database.helpers.productoSQL;
import com.twicky.estebancarranza.reparto.estaticos.defaultData;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.results;
import com.twicky.estebancarranza.reparto.services.synchronizeClientsInBackground;
import com.twicky.estebancarranza.reparto.webservice.NetCallback;
import com.twicky.estebancarranza.reparto.webservice.networking;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.twicky.estebancarranza.reparto.util.encrypt.md5;

public class mac_test_and_other_things extends AppCompatActivity  {

    Button btnCrearClientes;
    Button btnCrearProductos;
    Button btnCrearRutas;

    Button btnBorrarClientes; //resetea los valores default
    Button btnBorrarProductos; //resetea los valores default
    Button btnBorrarRutas;
    Button btnObtenerClientes;

    TextView txtLatitude;
    TextView txtLongitude;

    Button btnHASH;
    Button btnSincronizar;

    Button btnIniciarServicio;
    Button btnDetenerServicio;

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
        btnHASH = (Button) findViewById(R.id.btnHASH);
        btnSincronizar = (Button) findViewById(R.id.btnSincronizar);
        btnObtenerClientes = (Button) findViewById(R.id.btnObtenerClientes);
        btnIniciarServicio = (Button) findViewById(R.id.btnIniciarServicio);
        btnDetenerServicio = (Button) findViewById(R.id.btnDetenerServicio);

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

        btnHASH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String crypt = md5(txtLongitude.getText().toString());
                Toast.makeText(mac_test_and_other_things.this, crypt, Toast.LENGTH_SHORT).show();
            }
        });

        final cliente cliente = new cliente();
        cliente.setId(1);
        cliente.setRfc("HOLA");
        cliente.setNombre("NOMBRE");
        cliente.setIdRegimenFiscal(1);
        cliente.setDomicilio("DOMICILIO");
        cliente.setTelefono("TELEFONO");
        cliente.setCoordenada(new LatLng(10, 10));
        cliente.setEstadoActual(estado_cliente.sinConfirmar);
        //new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())




        btnSincronizar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                cliente.setFechaUltimaModificacion(txtLongitude.getText().toString());
                clienteSQL db = new clienteSQL(getApplicationContext());
                final ArrayList<cliente> clientes = db.getCliente(0,0);
                int i = 0;

                new networking(mac_test_and_other_things.this, new NetCallback() {

                    @Override
                    public void onWorkFinish(Object data) {
                        //final ArrayList<results> results = (ArrayList<results>) data;
                        final String results = (String) data;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(mac_test_and_other_things.this, results, Toast.LENGTH_SHORT).show();
                             /*   // Todoo el codigo dentro de este metodo se ejecuta dentro del hilo principal o hilo de la UI
                                Toast.makeText(mac_test_and_other_things.this, results.get(3).getDetail(), Toast.LENGTH_SHORT).show();

                               TotalResult.add(results);
                                if(finalI == clientes.size()-1)
                                {
                                    Toast.makeText(mac_test_and_other_things.this, "Clientes locales: " + clientes.size(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(mac_test_and_other_things.this, "Clientes en servidor" + TotalResult.get(0).get(2).getDetail(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(mac_test_and_other_things.this, "Sincronizacion terminada", Toast.LENGTH_SHORT).show();
                                }*/

                            }
                        });
                    }
                }).execute("SynchronizeClient", clientes);






            }
        });



        btnObtenerClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new networking(mac_test_and_other_things.this, new NetCallback() {

                    @Override
                    public void onWorkFinish(Object data) {
                        //final ArrayList<results> results = (ArrayList<results>) data;
                        final ArrayList<cliente> clientes = (ArrayList<cliente>) data;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                clienteSQL db = new clienteSQL(getApplicationContext());
                                db.insertIfNotExists(clientes);
                                Toast.makeText(mac_test_and_other_things.this, "Clientes sincronizados correctamente", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }).execute("getSynchronizeClient");
            }
        });


        btnIniciarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(mac_test_and_other_things.this, synchronizeClientsInBackground.class));
            }
        });

        btnDetenerServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(mac_test_and_other_things.this, synchronizeClientsInBackground.class));
            }
        });
    }

}
