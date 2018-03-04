package com.twicky.estebancarranza.reparto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.twicky.estebancarranza.reparto.adapters.recycler_list;

import java.util.ArrayList;

public class mac_home_sin_ruta extends AppCompatActivity {

    ArrayList<String> listDatos;
    ArrayList<recycler_list.Boton> listBotones;
    RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_home_sin_ruta);


        recycler = (RecyclerView) findViewById(R.id.lstBotones);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //listDatos = new ArrayList<String>();
        listBotones = new ArrayList<recycler_list.Boton>();
        /*
        for(int i = 0; i < 10; i++)
        {
            listDatos.add("Dato #" + i + " ");
        }*/
        //listDatos.add("Iniciar ruta");
        //listDatos.add("Cerrar sesión");


        listBotones.add(0, new recycler_list.Boton(getString(R.string.btnIniciarRuta), getString(R.string.btnIniciarRutaDesc), "btnIniciarRuta"));
        listBotones.add(1, new recycler_list.Boton(getString(R.string.btnCerrarSesion), getString(R.string.btnCerrarSesionDesc), "btnCerrarSesion"));
        /*
        listDatos.add("@string/txtAlert");
        listDatos.add("@string/txtAlert");*/
        ArrayList<Object> objListBotones = (ArrayList<Object>)(ArrayList<?>)(listBotones);
        //ArrayList<Object> objListDatos = (ArrayList<Object>)(ArrayList<?>)(listDatos);
        //recycler_list adapter = new recycler_list(objListDatos);
        recycler_list adapter = new recycler_list(objListBotones);

        recycler.setAdapter(adapter);



    }

}
