package com.twicky.estebancarranza.reparto.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.database.helpers.productoSQL;
import com.twicky.estebancarranza.reparto.database.helpers.vendedorSQL;
import com.twicky.estebancarranza.reparto.estaticos.defaultData;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.results;
import com.twicky.estebancarranza.reparto.models.vendedor;
import com.twicky.estebancarranza.reparto.sensores.luzTask;
import com.twicky.estebancarranza.reparto.services.synchronizeClientsInBackground;
import com.twicky.estebancarranza.reparto.webservice.NetCallback;
import com.twicky.estebancarranza.reparto.webservice.networking;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;
import com.txusballesteros.bubbles.OnInitializedCallback;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.twicky.estebancarranza.reparto.estaticos.defaultData.InsertRuta;
import static com.twicky.estebancarranza.reparto.estaticos.defaultData.InsertRutaDetalle;
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
    Button btnBubble;
    Button btnObtenerVendedores;

    Button btnActivarSensor;

    SensorManager m_sensorManager;
    Sensor m_sensor;
    LinearLayout ln;

    double intensidad = 0;

    private void clientes()
    {
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

    private void rutas()
    {

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

    private void sincronizacion()
    {
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

    }

    @SuppressLint("ResourceType")
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
        btnBubble = (Button) findViewById(R.id.btnBubble);
        btnObtenerVendedores = (Button) findViewById(R.id.btnObtenerVendedores);
        btnCrearRutas = (Button) findViewById(R.id.btnCrearRutas);
        btnActivarSensor = (Button) findViewById(R.id.btnActivarSensor);

        btnBorrarProductos.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     custom_color colorConfirmado = new custom_color(getResources().getColor(R.color.green));
                     custom_color colorNoConfirmado = new custom_color(getResources().getColor(R.color.red));

                     defaultData.InsertProductos(getApplicationContext(), colorConfirmado, colorNoConfirmado);
                 }
             }
        );




        btnHASH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String crypt = md5(txtLongitude.getText().toString());
                Toast.makeText(mac_test_and_other_things.this, crypt, Toast.LENGTH_SHORT).show();
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



        btnBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(mac_test_and_other_things.this, mac_bubble.class));
                initializeBubblesManager();

                addNewBubble();
            }
        });




        btnObtenerVendedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(mac_test_and_other_things.this, mac_lista_vendedores.class));
                /*
                vendedorSQL db = new vendedorSQL(getApplicationContext());
                ArrayList<vendedor> vendedores = new ArrayList<>();
                vendedores = db.getVendedores(0,0);
                int a  = 0;*/
            }
        });


        btnCrearRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InsertRuta(getApplicationContext());
                InsertRutaDetalle(getApplicationContext());
            }
        });

        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        ln = (LinearLayout) findViewById(R.id.lntBackground);



    }

    private BubblesManager bubblesManager;
    private void addNewBubble() {
        BubbleLayout bubbleView = (BubbleLayout) LayoutInflater.from(mac_test_and_other_things.this).inflate(R.layout.activity_mac_bubble, null);
        bubbleView.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
            @Override
            public void onBubbleRemoved(BubbleLayout bubble) { }
        });
        bubbleView.setOnBubbleClickListener(new BubbleLayout.OnBubbleClickListener() {

            @Override
            public void onBubbleClick(BubbleLayout bubble) {
                Toast.makeText(getApplicationContext(), "Clicked !",
                        Toast.LENGTH_SHORT).show();

                startActivity(new Intent(mac_test_and_other_things.this, mac_test_and_other_things.class));

            }
        });
        bubbleView.setShouldStickToWall(true);
        bubblesManager.addBubble(bubbleView, 60, 20);
    }

    private void initializeBubblesManager() {
        bubblesManager = new BubblesManager.Builder(this)
                .setTrashLayout(R.layout.activity_mac_bubble)
                .setInitializationCallback(new OnInitializedCallback() {
                    @Override
                    public void onInitialized() {
                        addNewBubble();
                    }
                })
                .build();
        bubblesManager.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bubblesManager.recycle();
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

           // Toast.makeText(mac_test_and_other_things.this, "Intensidad luz: " + event.values[0], Toast.LENGTH_SHORT).show();
            txtLatitude.setText(String.valueOf(event.values[0]));
            intensidad = event.values[0];

            if(intensidad < 50)
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
