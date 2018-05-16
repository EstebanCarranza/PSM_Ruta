package com.twicky.estebancarranza.reparto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.twicky.estebancarranza.reparto.R;

public class mac_registro extends AppCompatActivity {


    Button btnLoginFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_registro);

        btnLoginFacebook = (Button) findViewById(R.id.btnLoginFacebook);


        btnLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mac_registro.this, mac_test_and_other_things.class));
            }
        });
    }
}
