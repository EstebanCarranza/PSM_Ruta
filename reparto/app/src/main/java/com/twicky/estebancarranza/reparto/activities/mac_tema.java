package com.twicky.estebancarranza.reparto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twicky.estebancarranza.reparto.R;

public class mac_tema extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_tema);

        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
