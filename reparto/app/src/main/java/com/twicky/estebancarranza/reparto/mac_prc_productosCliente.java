package com.twicky.estebancarranza.reparto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.adapters.list_products;
import com.twicky.estebancarranza.reparto.datos.producto;

import java.util.ArrayList;

public class mac_prc_productosCliente extends AppCompatActivity {

    RecyclerView recycler;
    list_products adapter;
    ArrayList<producto> listProductos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_prc_productos_cliente);

        construirRecycler();
    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstProductos);
        listProductos = new ArrayList<producto>();

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listProductos.add(new producto(10, "TortillasAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "1 Kg de tortillas de maiz AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
        listProductos.add(new producto(10, "Tortillas", "1 Kg de tortillas de maiz"));
        listProductos.add(new producto(10, "Tortillas", "1 Kg de tortillas de maiz"));


        adapter = new list_products(listProductos);
/*
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.notifyDataSetChanged();

            }
        });*/

        recycler.setAdapter(adapter);

    }
}
