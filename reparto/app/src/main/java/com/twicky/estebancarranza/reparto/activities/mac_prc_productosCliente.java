package com.twicky.estebancarranza.reparto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.adapters.list_products;
import com.twicky.estebancarranza.reparto.database.helpers.productoSQL;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.custom_parameter;
import com.twicky.estebancarranza.reparto.models.producto;
import com.twicky.estebancarranza.reparto.estaticos.layout;

import java.util.ArrayList;

public class mac_prc_productosCliente extends AppCompatActivity {

    RecyclerView recycler;
    list_products adapter;
    Button btnGuardarPPC;
    ArrayList<producto> listProductos;
    custom_parameter custom_parameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_prc_productos_cliente);
        btnGuardarPPC = (Button) findViewById(R.id.btnGuardarPPC);
        TextView tvTitlePPC = findViewById(R.id.tvTitlePPC);
        botonGuardar();

        custom_parameter = new custom_parameter();

        int aux = getIntent().getExtras().getInt("tipoLayout");
        String text = getIntent().getExtras().getString("titulo");

        if(!text.isEmpty())
            tvTitlePPC.setText(text);

        try {
            if  (aux >= 0) {

                custom_parameter.setTipoLayout(layout.fromInteger(aux));
                switch (custom_parameter.getTipoLayout())
                {
                    case producto_por_cliente:

                    break;
                    case producto_sin_cliente:

                    break;
                    case producto_vista:
                        btnGuardarPPC.setVisibility(View.GONE);
                    break;
                    case producto_por_entregar:
                        btnGuardarPPC.setVisibility(View.GONE);
                    break;
                    default:break;
                }
                Toast.makeText(this, "Aux: " + aux, Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No hay parametros", Toast.LENGTH_SHORT).show();
        }

        construirRecycler();
    }

    private void construirRecycler()
    {
        recycler = (RecyclerView) findViewById(R.id.lstProductos);
        listProductos = new ArrayList<producto>();

        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        productoSQL db = new productoSQL(getApplicationContext());

        custom_color colorConfirmado = new custom_color(getResources().getColor(R.color.greenDark));
        custom_color colorNoConfirmado = new custom_color(getResources().getColor(R.color.background_color));
        custom_color colorDefault = new custom_color(getResources().getColor(R.color.background_color));

        listProductos = db.getproductos(1,1, colorConfirmado, colorNoConfirmado, colorDefault);
  /*      listProductos.add(new producto(10, "Tortillas", "1 Kg de tortillas de maiz"));
        listProductos.add(new producto(10, "Tortillas", "1 Kg de tortillas de maiz"));
        listProductos.add(new producto(10, "Tortillas", "1 Kg de tortillas de maiz"));
*/


        //Toast.makeText(this, listProductos.get(0).getColor(), Toast.LENGTH_SHORT).show();


        if(custom_parameter.getTipoLayout() != null)
            adapter = new list_products(listProductos, custom_parameter);
        else
            adapter = new list_products(listProductos);



        recycler.setAdapter(adapter);

}

    private void botonGuardar()
    {
        btnGuardarPPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mac_prc_productosCliente.this, "Cambios guardados", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
