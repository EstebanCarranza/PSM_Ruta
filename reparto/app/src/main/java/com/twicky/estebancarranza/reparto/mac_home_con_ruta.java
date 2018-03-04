package com.twicky.estebancarranza.reparto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twicky.estebancarranza.reparto.adapters.recycler_list;

import java.util.ArrayList;

public class mac_home_con_ruta extends AppCompatActivity {

    ArrayList<recycler_list.Boton> listBotones;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_home_con_ruta);

        recycler = (RecyclerView) findViewById(R.id.lstBotones);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listBotones = new ArrayList<recycler_list.Boton>();

        listBotones.add(0, new recycler_list.Boton(getString(R.string.btnMensajes), getString(R.string.btnMensajesDesc), "btnMensajes"));
        listBotones.add(1, new recycler_list.Boton(getString(R.string.btnFinalizarRuta), getString(R.string.btnFinalizarRutaDesc), "btnFinalizarRuta"));
        listBotones.add(2, new recycler_list.Boton(getString(R.string.btnDescargarMercancia), getString(R.string.btnDescargarMercanciaDesc), "btnDescargarMercancia"));
        listBotones.add(3, new recycler_list.Boton(getString(R.string.btnRecargaMercancia), getString(R.string.btnRecargaMercanciaDesc), "btnRecargaMercancia"));
        listBotones.add(4, new recycler_list.Boton(getString(R.string.btnClientes), getString(R.string.btnClientesDesc), "btnClientes"));
        listBotones.add(5, new recycler_list.Boton(getString(R.string.btnCerrarSesion), getString(R.string.btnCerrarSesionDesc), "btnCerrarSesion"));


        ArrayList<Object> objListBotones = (ArrayList<Object>)(ArrayList<?>)(listBotones);
        recycler_list adapter = new recycler_list(objListBotones);

        recycler.setAdapter(adapter);
    }
}
