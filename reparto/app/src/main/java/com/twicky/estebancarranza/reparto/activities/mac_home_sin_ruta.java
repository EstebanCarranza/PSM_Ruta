package com.twicky.estebancarranza.reparto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.adapters.list_buttons;
import com.twicky.estebancarranza.reparto.estaticos.ID;
import com.twicky.estebancarranza.reparto.estaticos.global;
import com.twicky.estebancarranza.reparto.models.vendedor;

import java.util.ArrayList;

public class mac_home_sin_ruta extends AppCompatActivity {

    ArrayList<String> listDatos;
    ArrayList<list_buttons.Boton> listBotones;
    RecyclerView recycler;
    TextView lblUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_home_sin_ruta);
        getSupportActionBar().hide();

      construirRecycler();

      lblUser = (TextView) findViewById(R.id.lblUser);

        vendedor vendedor = global.getVendedor();
        lblUser.setText(vendedor.getNombres() + " " + vendedor.getAppat());

    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstBotones);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //listDatos = new ArrayList<String>();
        listBotones = new ArrayList<list_buttons.Boton>();
        /*
        for(int i = 0; i < 10; i++)
        {
            listDatos.add("Dato #" + i + " ");
        }*/
        //listDatos.add("Iniciar ruta");
        //listDatos.add("Cerrar sesión");


        listBotones.add(new list_buttons.Boton(getString(R.string.btnIniciarRuta), getString(R.string.btnIniciarRutaDesc), ID.HSR.btnIniciarRuta));
        listBotones.add(new list_buttons.Boton(getString(R.string.btnCerrarSesion), getString(R.string.btnCerrarSesionDesc), ID.HSR.btnCerrarSesion));
        /*
        listDatos.add("@string/txtAlert");
        listDatos.add("@string/txtAlert");*/
        ArrayList<Object> objListBotones = (ArrayList<Object>)(ArrayList<?>)(listBotones);
        //ArrayList<Object> objListDatos = (ArrayList<Object>)(ArrayList<?>)(listDatos);
        //list_buttons adapter = new list_buttons(objListDatos);
        list_buttons adapter = new list_buttons(objListBotones);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(getApplicationContext(),
                        String.valueOf(listBotones.get(recycler.getChildAdapterPosition(view)).getTitulo()),
                        Toast.LENGTH_SHORT).show();
                */

                switch(listBotones.get(recycler.getChildAdapterPosition(view)).getIdBoton())
                {
                    case ID.HSR.btnIniciarRuta: {
                        Intent intentPRC = new Intent(mac_home_sin_ruta.this, mac_primera_carga.class);
                        startActivity(intentPRC);
                    }
                    break;
                    case ID.HSR.btnCerrarSesion:
                        finish();
                    break;
                    default:break;
                }


            }
        });

        recycler.setAdapter(adapter);

    }

}
