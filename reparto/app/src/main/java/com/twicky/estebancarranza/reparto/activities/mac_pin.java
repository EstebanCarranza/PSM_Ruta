package com.twicky.estebancarranza.reparto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twicky.estebancarranza.reparto.R;

public class mac_pin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_pin);
        getSupportActionBar().setTitle("Regresar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
