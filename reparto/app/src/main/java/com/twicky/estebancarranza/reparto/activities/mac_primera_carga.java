package com.twicky.estebancarranza.reparto.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
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
        listBotones.add( new list_buttons.Boton(getString(R.string.btnConfirmarProd), getString(R.string.btnConfirmarProdDesc), ID.CDI.btnConfirmarProd));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnVerProductos), getString(R.string.btnVerProductosDesc), ID.CDI.btnVerProductos));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnAgregarProd), getString(R.string.btnAgregarProdDesc), ID.CDI.btnAgregarProd));
        listBotones.add( new list_buttons.Boton(getString(R.string.btnGuardarCambiosPRC), getString(R.string.btnGuardarCambiosPRCDesc), ID.CDI.btnGuardarCambiosPRC));




        ArrayList<Object> objListBotones = (ArrayList<Object>)(ArrayList<?>)(listBotones);
        list_buttons adapter = new list_buttons(objListBotones);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mac_home_con_ruta.this, String.valueOf(listBotones.get(recycler.getChildAdapterPosition(view)).getTitulo()), Toast.LENGTH_SHORT).show();
                switch(listBotones.get(recycler.getChildAdapterPosition(view)).getIdBoton()) {

                    //CONFIRMAR PRODUCTOS DEL CLIENTE
                    case ID.CDI.btnConfirmarProd:
                        Intent intentPRC_confirmarCliente = new Intent(mac_primera_carga.this, mac_prc_confirmarCliente.class);
                        //layout.cliente_lista_confirmar
                        intentPRC_confirmarCliente.putExtra("tipoLayout", 3);
                        intentPRC_confirmarCliente.putExtra("title", getString(R.string.lblCOCTitulo));
                        startActivity(intentPRC_confirmarCliente);
                        break;

                    //VER LISTA DE PRODUCTOS
                    case ID.CDI.btnVerProductos:
                        //Abrir productos por confirmar
                        Intent prodConfirm = new Intent(mac_primera_carga.this, mac_prc_productosCliente.class);
                        // layout.producto_vista = 2
                        prodConfirm.putExtra("tipoLayout", 2);
                        prodConfirm.putExtra("titulo", getString(R.string.strTitlePPC2));
                        startActivity(prodConfirm);
                    break;

                    //AGREGAR PRODUCTOS SIN CLIENTE
                    case ID.CDI.btnAgregarProd:
                        //Abrir productos por confirmar
                        Intent agregaProd = new Intent(mac_primera_carga.this, mac_prc_productosCliente.class);
                        // layout.producto_vista = 2
                        agregaProd.putExtra("tipoLayout", 1);
                        agregaProd.putExtra("titulo", getString(R.string.strTitlePPC1));
                        startActivity(agregaProd);
                    break;

                    //GUARDAR CAMBIOS Y SALIR
                    case ID.CDI.btnGuardarCambiosPRC:
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mac_primera_carga.this);

                        builder.setTitle("Iniciar ruta");
                        builder.setMessage("¿Quieres empezar la ruta? \n Este proceso no se puede revertir");

                        builder.setPositiveButton("Iniciar ruta", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mac_primera_carga.this, "Elegiste iniciar la ruta", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                Intent irAHomeConRuta = new Intent(mac_primera_carga.this, mac_home_con_ruta.class);
                                startActivity(irAHomeConRuta);
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
