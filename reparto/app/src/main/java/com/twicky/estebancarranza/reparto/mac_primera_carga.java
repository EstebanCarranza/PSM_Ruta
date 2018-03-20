package com.twicky.estebancarranza.reparto;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.adapters.list_buttons;
import com.twicky.estebancarranza.reparto.estaticos.ID;

import java.util.ArrayList;

public class mac_primera_carga extends AppCompatActivity {

    ArrayList<list_buttons.Boton> listBotones;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_primera_carga);

        construirRecycler();
    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstBotones);
        listBotones = new ArrayList<list_buttons.Boton>();
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnVerProductos), getString(R.string.btnVerProductosDesc), ID.PRC.btnVerProductos));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnAgregarProd), getString(R.string.btnAgregarProdDesc), ID.PRC.btnAgregarProd));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnConfirmarProd), getString(R.string.btnConfirmarProdDesc), ID.PRC.btnConfirmarProd));



        ArrayList<Object> objListBotones = (ArrayList<Object>)(ArrayList<?>)(listBotones);
        list_buttons adapter = new list_buttons(objListBotones);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mac_home_con_ruta.this, String.valueOf(listBotones.get(recycler.getChildAdapterPosition(view)).getTitulo()), Toast.LENGTH_SHORT).show();
                switch(listBotones.get(recycler.getChildAdapterPosition(view)).getIdBoton()) {
                    case ID.PRC.btnVerProductos:
                        Intent intentPRC_confirmarCliente = new Intent(mac_primera_carga.this, mac_prc_confirmarCliente.class);
                        startActivity(intentPRC_confirmarCliente);
                        break;
                    case ID.PRC.btnConfirmarProd:
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mac_primera_carga.this);

                        builder.setTitle("Iniciar ruta");
                        builder.setMessage("¿Quieres empezar la ruta? \n Este proceso no se puede revertir");

                        builder.setPositiveButton("Iniciar ruta", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mac_primera_carga.this, "Elegiste iniciar la ruta", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Toast.makeText(mac_primera_carga.this, "Se canceló el inicio de la ruta", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();
                }
                        break;
                }
            }
        });

        recycler.setAdapter(adapter);

    }
}
