package com.twicky.estebancarranza.reparto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.adapters.list_buttons;
import com.twicky.estebancarranza.reparto.estaticos.ID;

import java.util.ArrayList;

public class mac_home_con_ruta extends AppCompatActivity {

    ArrayList<list_buttons.Boton> listBotones;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_home_con_ruta);
        getSupportActionBar().hide();
        construirRecycler();



    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstBotones);
        listBotones = new ArrayList<list_buttons.Boton>();
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnMensajes), getString(R.string.btnMensajesDesc), ID.HCR.btnMensajes));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnClientes), getString(R.string.btnClientesDesc), ID.HCR.btnClientes));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnRuta), getString(R.string.btnRutaDesc), ID.HCR.btnRuta));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnAlmacen), getString(R.string.btnAlmacenDesc), ID.HCR.btnAlmacen));
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
                        Intent irAMensajes = new Intent(mac_home_con_ruta.this, mac_mensajes.class);
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
}
