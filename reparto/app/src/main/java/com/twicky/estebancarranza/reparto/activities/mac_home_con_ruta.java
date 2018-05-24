package com.twicky.estebancarranza.reparto.activities;

import android.content.Context;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.SettingInjectorService;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.adapters.list_buttons;
import com.twicky.estebancarranza.reparto.estaticos.ID;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;
import com.txusballesteros.bubbles.OnInitializedCallback;

import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.estaticos.global.getVendedor;
import static com.twicky.estebancarranza.reparto.sensores.luz.intensidad;
import static com.twicky.estebancarranza.reparto.sensores.luz.ln;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensor;
import static com.twicky.estebancarranza.reparto.sensores.luz.m_sensorManager;
import static com.twicky.estebancarranza.reparto.sharedPreferences.login.setLoginAuto;

public class mac_home_con_ruta extends AppCompatActivity {

    ArrayList<list_buttons.Boton> listBotones;
    TextView lblUser;
    RecyclerView recycler;
    





    @Override
    public void onStop()
    {
        super.onStop();
        addNewBubble();

        //startActivity(new Intent(Settings.H));
    }

    GestureOverlayView m_gestureOverlayView;
    GestureLibrary m_gestureLibrary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_home_con_ruta);
        getSupportActionBar().hide();
        construirRecycler();
        initializeBubblesManager();

        //Objeto el cual tendra nuestras gesturas que creemos
        // nosotros mismos con la app "Gesture Builder"

        m_gestureOverlayView = (GestureOverlayView) findViewById(R.id.gesture_overlay);

        m_gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        // Si no se pudo cargar nuestra libreria (coleccion) de gesturas
        if (!m_gestureLibrary.load()) {
            finish();
        }

        m_gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
                ArrayList<Prediction> predictionArrayList = m_gestureLibrary.recognize(gesture);
                for(Prediction prediction:predictionArrayList)
                {
                    if(prediction.score > 1.0)
                    {
                        if(prediction.name.toString().equals("cerrar"))
                        {
                            setLoginAuto(getApplicationContext(), false, "", "");
                            finish();
                        }
                        //Toast.makeText(mac_home_con_ruta.this, prediction.name.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ln = (LinearLayout) findViewById(R.id.lnlBackground);
        m_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        m_sensor = m_sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        lblUser = (TextView) findViewById(R.id.lblUser);
        lblUser.setText(getVendedor().getNombres().toString() + " " + getVendedor().getAppat().toString());


    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstBotones);
        listBotones = new ArrayList<list_buttons.Boton>();
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnMensajes), getString(R.string.btnMensajesDesc), ID.HCR.btnMensajes));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnClientes), getString(R.string.btnClientesDesc), ID.HCR.btnClientes));
       listBotones.add( new list_buttons.Boton(getString(R.string.btnRuta), getString(R.string.btnRutaDesc), ID.HCR.btnRuta));
        //listBotones.add( new list_buttons.Boton(getString(R.string.btnAlmacen), getString(R.string.btnAlmacenDesc), ID.HCR.btnAlmacen));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnConfiguraciones), getString(R.string.btnConfiguracionesDesc), ID.HCR.btnConfiguracion));




        ArrayList<Object> objListBotones = (ArrayList<Object>)(ArrayList<?>)(listBotones);
        list_buttons adapter = new list_buttons(objListBotones);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mac_home_con_ruta.this, String.valueOf(listBotones.get(recycler.getChildAdapterPosition(view)).getTitulo()), Toast.LENGTH_SHORT).show();
                switch(listBotones.get(recycler.getChildAdapterPosition(view)).getIdBoton())
                {

                    case ID.HCR.btnMensajes:
                        Intent irAMensajes = new Intent(mac_home_con_ruta.this, mac_lista_vendedores.class);
                        startActivity(irAMensajes);
                    break;

                    case ID.HCR.btnClientes:
                        Intent irAClientesCRU = new Intent(mac_home_con_ruta.this, mac_prc_confirmarCliente.class);
                        //layout.cliente_lista_CRU =
                        irAClientesCRU.putExtra("tipoLayout", 4);
                        irAClientesCRU.putExtra("title", getString(R.string.strClienteCRU_Title));
                        startActivity(irAClientesCRU);
                    break;

                    case ID.HCR.btnRuta:
                        Intent irARuta = new Intent(mac_home_con_ruta.this, mac_ruta.class);
                        startActivity(irARuta);
                    break;

                    case ID.HCR.btnConfiguracion:
                        Intent irAConfiguracion = new Intent(mac_home_con_ruta.this, mac_configuracion.class);
                        startActivity(irAConfiguracion);
                    break;

                }
            }
        });

        recycler.setAdapter(adapter);

    }

    @Override
    protected void onPause() {

        super.onPause();
        //Siempre suspender el sensor
        m_sensorManager.unregisterListener(m_sensorEventListener);
        //
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



    private BubblesManager bubblesManager;
    private void addNewBubble() {
        BubbleLayout bubbleView = (BubbleLayout) LayoutInflater.from(mac_home_con_ruta.this).inflate(R.layout.activity_mac_bubble, null);
        bubbleView.setOnBubbleRemoveListener(new BubbleLayout.OnBubbleRemoveListener() {
            @Override
            public void onBubbleRemoved(BubbleLayout bubble) { }
        });
        bubbleView.setOnBubbleClickListener(new BubbleLayout.OnBubbleClickListener() {

            @Override
            public void onBubbleClick(BubbleLayout bubble) {


                startActivity(new Intent(mac_home_con_ruta.this, mac_home_con_ruta.class));
                finish();
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
                        //addNewBubble();
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

}
