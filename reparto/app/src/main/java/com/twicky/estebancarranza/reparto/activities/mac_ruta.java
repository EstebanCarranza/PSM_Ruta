package com.twicky.estebancarranza.reparto.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.adapters.list_buttons;
import com.twicky.estebancarranza.reparto.models.custom_parameter;
import com.twicky.estebancarranza.reparto.estaticos.ID;

import java.util.ArrayList;

public class mac_ruta  extends AppCompatActivity {

    ArrayList<list_buttons.Boton> menu;
    RecyclerView recycler;
    custom_parameter custom_parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_ruta);


        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        construirRecycler();

    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstRuta);
        menu = new ArrayList<list_buttons.Boton>();

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        menu.add(new list_buttons.Boton(getString(R.string.strVerProductosTitleRUTA), getString(R.string.strVerProductosDescRUTA), ID.RUTA.btnVerProductos));
        menu.add(new list_buttons.Boton(getString(R.string.strEntregaMercanciaTitleRUTA), getString(R.string.strEntregarMercanciaDescRUTA), ID.RUTA.btnEntregarMercancia));
        menu.add(new list_buttons.Boton(getString(R.string.strRecogerMercanciaTitleRUTA), getString(R.string.strRecogerMercanciaDescRUTA), ID.RUTA.btnRecogerMercancia));
        menu.add(new list_buttons.Boton(getString(R.string.strVerUbicacionClientesTitleRUTA), getString(R.string.strVerUbicacionClientesDescRUTA), ID.RUTA.btnAbrirMapa));
        menu.add(new list_buttons.Boton(getString(R.string.strFinalizarRutaTitleRUTA), getString(R.string.strFinalizarRutaDescRUTA), ID.RUTA.btnFinalizarRuta));

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

                    case ID.RUTA.btnVerProductos:

                     Intent listaProductos = new Intent(mac_ruta.this, mac_prc_productosCliente.class);
                        // layout.producto_por_entregar = 5
                        listaProductos.putExtra("tipoLayout", 5);
                        listaProductos.putExtra("titulo", getString(R.string.strTitlePPC3));
                        startActivity(listaProductos);

                    break;
                    case ID.RUTA.btnAbrirMapa:
                        Intent irAMapa = new Intent(mac_ruta.this, mac_ubicacion_cliente.class);
                        startActivity(irAMapa);

                    break;

                }
            }
        });



        recycler.setAdapter(adapter);




    }

}
