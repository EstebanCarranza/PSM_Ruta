package com.twicky.estebancarranza.reparto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.adapters.list_clients;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.datos.cliente;
import com.twicky.estebancarranza.reparto.datos.custom_parameter;
import com.twicky.estebancarranza.reparto.estaticos.ID;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import java.util.ArrayList;
import java.util.List;

import com.twicky.estebancarranza.reparto.estaticos.layout;

public class mac_prc_confirmarCliente extends AppCompatActivity {
    
    ArrayList<cliente> listCliente;
    RecyclerView recycler;
    TextView tvTotalClientesVal;
    TextView tvTotalClientesNoConfirmadosVal;
    TextView tvTotalClientesNoAsignadosVal;
    list_clients adapter;
    Button btnConfirmarClienteCOC;
    custom_parameter opciones_adicionales;
    TextView lblConfirmarClientes;
    TextView lblTotalClientes;
    TextView lblTotalClientesNoConfirmados;
    TextView lblTotalClientesAsignados;
    Button btnCrearClienteCOC;

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

    private void  botonGuardar()
    {
        btnConfirmarClienteCOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mac_prc_confirmarCliente.this, "Cambios guardados", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void botonAbrirCRU()
    {
        btnCrearClienteCOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clienteCRUD = new Intent(mac_prc_confirmarCliente.this, mac_cliente.class);
                startActivity(clienteCRUD);
            }
        });
    }
    private void inicializarElementos()
    {
        
        opciones_adicionales = new custom_parameter();
        btnConfirmarClienteCOC = (Button) findViewById(R.id.btnConfirmarClienteCOC);
        btnCrearClienteCOC = (Button) findViewById(R.id.btnCrearClienteCOC);
        //Etiquetas
        lblConfirmarClientes = (TextView) findViewById(R.id.lblConfirmarClientes);
        lblTotalClientes = (TextView) findViewById(R.id.lblTotalClientes);
        lblTotalClientesNoConfirmados = (TextView) findViewById(R.id.lblTotalClientesNoConfirmados);
        lblTotalClientesAsignados = (TextView) findViewById(R.id.lblTotalClientesAsignados);


        //Valores
        tvTotalClientesVal = (TextView) findViewById(R.id.lblTotalClientesVal);
        tvTotalClientesNoAsignadosVal= (TextView) findViewById(R.id.lblTotalClientesNoAsignadosVal);
        tvTotalClientesNoConfirmadosVal= (TextView) findViewById(R.id.lblTotalClientesNoConfirmadosVal);

        int nextLayout = getIntent().getExtras().getInt("tipoLayout");
        String title = getIntent().getExtras().getString("title");

        if(title.isEmpty())
           title = lblConfirmarClientes.getText().toString();

            try {
                if (nextLayout >= 0) {
                    opciones_adicionales.setTipoLayout(layout.fromInteger(nextLayout));

                    switch (opciones_adicionales.getTipoLayout()) {
                        case cliente_lista_confirmar:
                            lblConfirmarClientes.setText(title);
                            btnCrearClienteCOC.setVisibility(View.GONE);
                            Toast.makeText(this, "Entraste a confirmar clientes", Toast.LENGTH_SHORT).show();
                            break;
                        case cliente_lista_CRU:
                            Toast.makeText(this, "Entraste para hacer CRUD de clientes", Toast.LENGTH_SHORT).show();
                            lblConfirmarClientes.setText(title);
                            btnConfirmarClienteCOC.setVisibility(View.GONE);

                            tvTotalClientesNoConfirmadosVal.setVisibility(View.GONE);
                            tvTotalClientesNoAsignadosVal.setVisibility(View.GONE);
                            tvTotalClientesVal.setVisibility(View.GONE);

                            lblTotalClientes.setVisibility(View.GONE);
                            lblTotalClientesAsignados.setVisibility(View.GONE);
                            lblTotalClientesNoConfirmados.setVisibility(View.GONE);


                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {

            }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_prc_confirmar_cliente);
        
       inicializarElementos();
        
        botonGuardar();
        botonAbrirCRU();

        construirRecycler();

        tvTotalClientesVal.setText(String.valueOf(listCliente.size()));
        
        tvTotalClientesNoAsignadosVal.setText(String.valueOf(contarClientesSinAsignar()));
        tvTotalClientesNoConfirmadosVal.setText(String.valueOf(contarClientesSinConfirmar()));


    }
    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstClientes);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listCliente = new ArrayList<cliente>();

        clienteSQL db = new clienteSQL(getApplicationContext());

        listCliente = db.getCliente(1,1);


/*
        listCliente.add(new cliente("Esteban CarranzaNew","Virgilio cardenas 1401-D", estado_cliente.confirmado));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D", estado_cliente.sinAsignar));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.sinConfirmar));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.confirmado));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D", estado_cliente.sinAsignar));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.confirmado));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.sinConfirmar));
        listCliente.add(new cliente("Esteban Carranza","Virgilio cardenas 1401-D",estado_cliente.sinAsignar));


*/


        ArrayList<Object> objlistCliente = (ArrayList<Object>)(ArrayList<?>)(listCliente);

        if(opciones_adicionales != null)
            adapter = new list_clients(objlistCliente, opciones_adicionales);
        else
            adapter = new list_clients(objlistCliente);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(opciones_adicionales != null) 
                {
                    switch(opciones_adicionales.getTipoLayout())
                    {
                        case cliente_lista_confirmar:
                            listCliente.get(recycler.getChildAdapterPosition(view)).setEstadoActual(estado_cliente.confirmado);
                            adapter.notifyDataSetChanged();


                            tvTotalClientesVal.setText(String.valueOf(listCliente.size()));
                            tvTotalClientesNoAsignadosVal.setText(String.valueOf(contarClientesSinAsignar()));
                            tvTotalClientesNoConfirmadosVal.setText(String.valueOf(contarClientesSinConfirmar()));

                            //Abrir productos por confirmar

                            Intent prodConfirm = new Intent(mac_prc_confirmarCliente.this, mac_prc_productosCliente.class);
                            // layout.producto_por_cliente
                            prodConfirm.putExtra("titulo", "Productos por cliente");
                            prodConfirm.putExtra("tipoLayout", 0);
                            startActivity(prodConfirm);
                        break;
                        case cliente_lista_CRU:
                            Toast.makeText(mac_prc_confirmarCliente.this, "Se abrirá el CRU de Cliente", Toast.LENGTH_SHORT).show();
                            Intent clienteCRUD = new Intent(mac_prc_confirmarCliente.this, mac_cliente.class);
                            clienteCRUD.putExtra("idCliente", listCliente.get(recycler.getChildAdapterPosition(view)).getId());
                            startActivity(clienteCRUD);
                        break;
                        default: break;
                    }
                    
                }
                


            }
        });

        recycler.setAdapter(adapter);



    }
}
