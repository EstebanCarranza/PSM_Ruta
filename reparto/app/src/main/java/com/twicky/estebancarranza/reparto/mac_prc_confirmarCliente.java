package com.twicky.estebancarranza.reparto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.twicky.estebancarranza.reparto.adapters.list_clients;
import com.twicky.estebancarranza.reparto.datos.cliente;
import com.twicky.estebancarranza.reparto.estaticos.ID;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import java.util.ArrayList;

public class mac_prc_confirmarCliente extends AppCompatActivity {
    
    ArrayList<cliente> listCliente;
    RecyclerView recycler;
    TextView tvTotalClientesVal;
    TextView tvTotalClientesNoConfirmadosVal;
    TextView tvTotalClientesNoAsignadosVal;
    list_clients adapter;

    private int contarClientesSinAsignar()
    {
        int total = 0;
        for(int i =0; i < listCliente.size(); i++)
        {
            if (listCliente.get(i).getEstadoActual() == estado_cliente.sinAsignar)
            {
                total++;
            }
        }
        
        return total;
    }
    private int contarClientesSinConfirmar()
    {
        int total = 0;
        for(int i =0; i < listCliente.size(); i++)
        {
            if (listCliente.get(i).getEstadoActual() == estado_cliente.sinConfirmar)
            {
                total++;
            }
        }

        return total;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_prc_confirmar_cliente);
        construirRecycler();

        tvTotalClientesVal = (TextView) findViewById(R.id.lblTotalClientesVal);
        tvTotalClientesNoAsignadosVal= (TextView) findViewById(R.id.lblTotalClientesNoAsignadosVal);
        tvTotalClientesNoConfirmadosVal= (TextView) findViewById(R.id.lblTotalClientesNoConfirmadosVal);


        tvTotalClientesVal.setText(String.valueOf(listCliente.size()));
        
        tvTotalClientesNoAsignadosVal.setText(String.valueOf(contarClientesSinAsignar()));
        tvTotalClientesNoConfirmadosVal.setText(String.valueOf(contarClientesSinConfirmar()));


    }
    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstClientes);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listCliente = new ArrayList<cliente>();


        listCliente.add(new cliente("Esteban CarranzaNew","Virgilio cardenas 1401-D", estado_cliente.confirmado));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D", estado_cliente.sinAsignar));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.sinConfirmar));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.confirmado));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D", estado_cliente.sinAsignar));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.confirmado));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.sinConfirmar));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.sinAsignar));





        ArrayList<Object> objlistCliente = (ArrayList<Object>)(ArrayList<?>)(listCliente);

        adapter = new list_clients(objlistCliente);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listCliente.get(recycler.getChildAdapterPosition(view)).setEstadoActual(estado_cliente.confirmado);
                adapter.notifyDataSetChanged();
                tvTotalClientesVal.setText(String.valueOf(listCliente.size()));
                tvTotalClientesNoAsignadosVal.setText(String.valueOf(contarClientesSinAsignar()));
                tvTotalClientesNoConfirmadosVal.setText(String.valueOf(contarClientesSinConfirmar()));

                //Abrir productos por confirmar
                Intent prodConfirm = new Intent(mac_prc_confirmarCliente.this, mac_prc_productosCliente.class);
                startActivity(prodConfirm);


            }
        });

        recycler.setAdapter(adapter);

    }
}
