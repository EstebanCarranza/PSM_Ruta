package com.twicky.estebancarranza.reparto.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.adapters.list_messages;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.mensaje;

import java.util.ArrayList;

public class mac_mensajes extends AppCompatActivity {

    RecyclerView recycler;
    list_messages adapter;
    ArrayList<mensaje> mensajes;
    Button btnEnviarMSG;
    int indexMSG = 0;
    TextView txtEnviarMSG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_mensajes);
        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnEnviarMSG = (Button) findViewById(R.id.btnEnviarMSG);
        txtEnviarMSG = (TextView) findViewById(R.id.txtEnviarMSG);
        construirRecycler();
        boton_enviar();
    }

    private void boton_enviar()
    {
        btnEnviarMSG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensajes.add(new mensaje(indexMSG, txtEnviarMSG.getText().toString(), true));
                adapter.notifyDataSetChanged();
                txtEnviarMSG.setText("");


            }
        });
    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstMessages);
        mensajes = new ArrayList<mensaje>();

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        indexMSG += 1;
        mensajes.add(new mensaje(indexMSG, "Hola", true));
        indexMSG += 1;
        mensajes.add(new mensaje(indexMSG, "Que paso?", false));
        indexMSG += 1;
        mensajes.add(new mensaje(indexMSG, "Tengo una duda", true));

        custom_color colorMe = new custom_color(getResources().getColor(R.color.cardview_dark_background));
        custom_color colorOther = new custom_color(getResources().getColor(R.color.text_color2));

        adapter = new list_messages(
                mensajes,
                    colorMe,
                    colorOther
                );


        recycler.setAdapter(adapter);

    }

}
