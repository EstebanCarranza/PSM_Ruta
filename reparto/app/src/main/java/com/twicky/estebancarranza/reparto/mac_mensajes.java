package com.twicky.estebancarranza.reparto;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.adapters.list_messages;
import com.twicky.estebancarranza.reparto.datos.custom_color;
import com.twicky.estebancarranza.reparto.datos.message;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class mac_mensajes extends AppCompatActivity {

    RecyclerView recycler;
    list_messages adapter;
    ArrayList<message> messages;
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
                messages.add(new message(indexMSG, txtEnviarMSG.getText().toString(), true));
                adapter.notifyDataSetChanged();
                txtEnviarMSG.setText("");


            }
        });
    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstMessages);
        messages = new ArrayList<message>();

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        indexMSG += 1;
        messages.add(new message(indexMSG, "Hola", true));
        indexMSG += 1;
        messages.add(new message(indexMSG, "Que paso?", false));
        indexMSG += 1;
        messages.add(new message(indexMSG, "Tengo una duda", true));

        custom_color colorMe = new custom_color(getResources().getColor(R.color.green));
        custom_color colorOther = new custom_color(getResources().getColor(R.color.red));

        adapter = new list_messages(
                    messages,
                    colorMe,
                    colorOther
                );


        recycler.setAdapter(adapter);

    }

}
