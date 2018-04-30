package com.twicky.estebancarranza.reparto;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.twicky.estebancarranza.reparto.adapters.list_buttons;
import com.twicky.estebancarranza.reparto.datos.custom_parameter;
import com.twicky.estebancarranza.reparto.estaticos.ID;

import java.util.ArrayList;

public class mac_configuracion extends AppCompatActivity {


    ArrayList<list_buttons.Boton> menu;
    RecyclerView recycler;
    com.twicky.estebancarranza.reparto.datos.custom_parameter custom_parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_configuracion);

        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        construirRecycler();
    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstConfiguracion);
        menu = new ArrayList<list_buttons.Boton>();

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_CambiarTemaTitle), getString(R.string.strCONFIG_CambiarTemaDesc), ID.CONFIG.btnCambiarTema));
        menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_CambiarPrecisionTitle),getString(R.string.strCONFIG_CambiarPrecisionDesc), ID.CONFIG.btnCambiarPrecision));
        menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_PINTitle), getString(R.string.strCONFIG_PINDesc), ID.CONFIG.btnAgregarPIN));
        menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_DatosUsuarioTitle), getString(R.string.strCONFIG_DatosUsuarioDesc), ID.CONFIG.btnVerDatosPersonales));
        menu.add(new list_buttons.Boton(getString(R.string.strCONFIG_CerrarSesionTitle), getString(R.string.strCONFIG_CerrarSesionDesc), ID.CONFIG.btnCerrarSesion));



        ArrayList<Object> objListBotones = (ArrayList<Object>)(ArrayList<?>)(menu);
        list_buttons adapter = new list_buttons(objListBotones);

        if(custom_parameter != null) {
            if (custom_parameter.getTipoLayout() != null)
                adapter = new list_buttons(objListBotones);
            else
                adapter = new list_buttons(objListBotones);
        }
        else
            adapter = new list_buttons(objListBotones);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (menu.get(recycler.getChildAdapterPosition(view)).getIdBoton()) {

                    case ID.CONFIG.btnCambiarTema:
                        Intent irATema = new Intent(mac_configuracion.this, mac_tema.class);
                        startActivity(irATema);
                    break;
                    case ID.CONFIG.btnCambiarPrecision:
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    break;
                    case ID.CONFIG.btnAgregarPIN:
                        startActivity(new Intent(mac_configuracion.this, mac_pin.class));
                    break;
                    case ID.CONFIG.btnVerDatosPersonales:

                    break;
                    case ID.CONFIG.btnCerrarSesion:
                        startActivity(new Intent(mac_configuracion.this, MainActivity.class));
                    break;
                }
            }
        });



        recycler.setAdapter(adapter);




    }
}
