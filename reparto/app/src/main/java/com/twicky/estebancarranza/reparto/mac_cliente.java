package com.twicky.estebancarranza.reparto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.twicky.estebancarranza.reparto.datos.regimenFiscal;

import java.util.ArrayList;
import java.util.Collections;

public class mac_cliente extends AppCompatActivity {

    ArrayList<regimenFiscal> listRegimenFiscal;
    ArrayAdapter<String> comboAdapter;
    Spinner cbxRegimenFiscal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_cliente);

        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cbxRegimenFiscal = (Spinner) findViewById(R.id.cbxRegimenFiscal);

        listRegimenFiscal = new ArrayList<>();

        listRegimenFiscal.add(new regimenFiscal(1, "General de personas fisicas"));
        listRegimenFiscal.add(new regimenFiscal(2, "General de personas morales"));
        listRegimenFiscal.add(new regimenFiscal(3, "General 3"));
        listRegimenFiscal.add(new regimenFiscal(4, "General 4"));
        listRegimenFiscal.add(new regimenFiscal(5, "General 5"));
        listRegimenFiscal.add(new regimenFiscal(6, "General 6"));

        ArrayList<String> listaNombresRF = new ArrayList<>();

        for(int i = 0; i < listRegimenFiscal.size(); i++)
            listaNombresRF.add(listRegimenFiscal.get(i).getRegimenFiscal());


        //Implemento el adapter con el contexto, layout, listaFrutas
        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, listaNombresRF);
        //Cargo el spinner con los datos
        cbxRegimenFiscal.setAdapter(comboAdapter);
    }
}
