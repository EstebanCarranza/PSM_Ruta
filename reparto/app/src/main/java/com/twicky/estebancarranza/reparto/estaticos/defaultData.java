package com.twicky.estebancarranza.reparto.estaticos;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.database.db;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.database.helpers.productoSQL;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.producto;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class defaultData {
    static public void InsertCliente(Context context)
    {
        clienteSQL dbDelete = new clienteSQL(context);
        dbDelete.deleteAll();


        Toast.makeText(context, "Se borraron los cliente anteriores", Toast.LENGTH_SHORT).show();

        ArrayList<cliente> cliente = new ArrayList<>();

        LatLng FCFM = new LatLng(25.725563,-100.3153316);
        LatLng FCQ = new LatLng(25.7243837,-100.3154000);
        LatLng FIC = new LatLng(25.7246921,-100.3142084);
        LatLng FIME = new LatLng(25.7250269,-100.3134715);

       cliente.add( new cliente(0, "rfc",
                "Facultad de Ciencias Fisico Matemáticas", 1,
                "Pedro de Alba, Ciudad Universitaria, 66451 San Nicolás de los Garza, N.L.",
                "telefono", FCFM, estado_cliente.sinConfirmar
        ));

        cliente.add(  new cliente (0, "rfc",
                "Facultad de Ciencias Quimicas", 1,
                "Pedro de Alba, Ciudad Universitaria, 66451 San Nicolás de los Garza, N.L.",
                "telefono", FCQ, estado_cliente.sinConfirmar
        ));

        cliente.add( new cliente (0, "rfc",
                "Facultad de Ingeniería Civil", 1,
                "Pedro de Alba, Ciudad Universitaria, 66451 San Nicolás de los Garza, N.L.",
                "telefono", FIC, estado_cliente.sinConfirmar
        ));

        cliente.add( new cliente (0, "rfc",
                "Facultad de Ingeniería Mecánica y Electrica", 1,
                "Pedro de Alba, Ciudad Universitaria, 66451 San Nicolás de los Garza, N.L.",
                "telefono", FIME, estado_cliente.sinConfirmar
        ));
        //cliente.add(new cliente(0, "", "","",));
        clienteSQL db = new clienteSQL(context);
        for(int i= 0; i < cliente.size(); i++)
            db.insert(cliente.get(i));

    }
    static public void InsertProductos(Context context, custom_color colorConfirmado, custom_color colorNoConfirmado)
    {
        productoSQL dbDelete = new productoSQL(context);
        dbDelete.deleteAll();

        Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show();
        
        ArrayList<producto> producto = new ArrayList<>();

        
        producto.add(new producto(10, "1 Kg T/caliente (PAPEL)", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(15, "1 Kg Tortilla de Bolsa", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(7.5f, "1/2 Kg Tortilla de Bolsa", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(9, "Tortilla de Bolsa 800 Gr", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(12, "Tortilla taquera No 11 (TROMPO)", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(12, "Tortilla taquera No 12 (MONO)", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(12, "Tortilla taquera Emplayada", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(13, "Tortilla Roja", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(15, "Bote de Frijol", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(20, "Charola de Flautas", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(20, "Charola de Tacos", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(10, "Totopos 280 Gr", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(10, "Tostadas 280 Gr", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(10, "Masa Kg", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(5, "1/2 kg tortilla caliente (PAPEL)", "", colorConfirmado, colorNoConfirmado));
        producto.add(new producto(8, "Tortilla caliente 800 g (PAPEL)", "", colorConfirmado, colorNoConfirmado));

        productoSQL db = new productoSQL(context);
        for(int i = 0; i < producto.size(); i++)
            db.insert(producto.get(i));

    }
}
