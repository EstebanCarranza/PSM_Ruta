package com.twicky.estebancarranza.reparto.estaticos;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.database.db;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.database.helpers.productoClienteSQL;
import com.twicky.estebancarranza.reparto.database.helpers.productoSQL;
import com.twicky.estebancarranza.reparto.database.helpers.rutaDetalleSQL;
import com.twicky.estebancarranza.reparto.database.helpers.rutaSQL;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.producto;
import com.twicky.estebancarranza.reparto.models.productoCliente;
import com.twicky.estebancarranza.reparto.models.ruta;
import com.twicky.estebancarranza.reparto.models.rutaDetalle;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class defaultData {
    static public void InsertCliente(Context context)
    {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        clienteSQL dbDelete = new clienteSQL(context);
        dbDelete.deleteAll();


        Toast.makeText(context, "Se borraron los cliente anteriores", Toast.LENGTH_SHORT).show();

        ArrayList<cliente> cliente = new ArrayList<>();

       cliente.add( new cliente(0, "FCF8010128N8",
                "Facultad de Ciencias Fisico Matemáticas", 1,
                "Pedro de Alba, Ciudad Universitaria, 66451 San Nicolás de los Garza, N.L.",
                "telefono", new LatLng(25.725563,-100.3153316), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add(  new cliente (0, "FCQ8010128N7",
                "Facultad de Ciencias Quimicas", 1,
                "Pedro de Alba, Ciudad Universitaria, 66451 San Nicolás de los Garza, N.L.",
                "telefono", new LatLng(25.7243837,-100.3154000), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente (0, "FIC8010125N5",
                "Facultad de Ingeniería Civil", 1,
                "Pedro de Alba, Ciudad Universitaria, 66451 San Nicolás de los Garza, N.L.",
                "telefono", new LatLng(25.7246921,-100.3142084), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente (0, "FIM8010128F1",
                "Facultad de Ingeniería Mecánica y Electrica", 1,
                "Pedro de Alba, Ciudad Universitaria, 66451 San Nicolás de los Garza, N.L.",
                "telefono", new LatLng(25.7250269,-100.3134715), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "TJZ900508444",
                "Taquería Juárez", 1,
                "Galeana Norte 123, Centro, 64000 Monterrey, N.L.",
                "83421936", new LatLng(25.676287, -100.312117), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "EAC8909165L7",
                "El Asador Del Cabrito y Carnes Asadas", 1,
                "Ignacio Zaragoza 142 Sur, Centro, 64000 Monterrey, N.L.",
                "83421936", new LatLng(25.674987, -100.309637), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "TAM6804126Y5",
                "Tacos Los Amigos Del Norte", 1,
                "Calle Modesto Arreola Ote. 609, Centro, 64000 Monterrey, N.L.",
                "83521521", new LatLng(25.674927, -100.309291), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "TDA880507555",
                "Tacos Dorados de la Alberca", 1,
                "Juan Zuazua 123, Centro, 64000 Monterrey, N.L.",
                "8115122301", new LatLng(25.674578, -100.308273), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "CCS3505303D3",
                "Comidas Caseras", 1,
                "Calle Gral. Mariano Escobedo 333, Centro, 64000 Monterrey, N.L.",
                "83441814", new LatLng(25.673594, -100.310877), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "TLG5608154R3",
                "TAQUERIA LOS GALLOS", 1,
                "5 de Mayo Esq. Calle Vicente Guerrero 122 Ote, Centro, 64000 Monterrey, N.L.",
                "83428364", new LatLng(25.673380, -100.313739), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "TCM0205025T4",
                "Tacos Chema 2", 1,
                "5 de Mayo 210, Centro, 64000 Monterrey, N.L.",
                "8112756635", new LatLng(25.673704, -100.316219), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "TDC6507120E3",
                "Tacos Don Chema", 1,
                "Calle, José Mariano Jiménez #350, Centro, 64000 Monterrey, N.L.",
                "80407745", new LatLng(25.674163, -100.317564), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "ECT6809147U4",
                "El CENTRITO Tacos Y Tortas", 1,
                "Cuauhtémoc #347 sur, Centro, 64000 Monterrey, N.L.",
                "89951547", new LatLng(25.674605, -100.318413), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "TDN0607080F9",
                "Tacos Don Nacho", 1,
                "Calle Ignacio López Rayon S/N, Centro, 64000 Monterrey, N.L.",
                "8120394674", new LatLng(25.676976, -100.319819), estado_cliente.sinConfirmar, timeStamp
        ));

        cliente.add( new cliente(0, "GDT3405305D3",
                "Gorditas Doña Tota", 1,
                "Industrial, 64440 Monterrey, N.L.",
                "85958565", new LatLng(25.687174, -100.324434), estado_cliente.sinConfirmar, timeStamp
        ));




        //cliente.add(new cliente(0, "", "","",));
        clienteSQL db = new clienteSQL(context);
        for(int i= 0; i < cliente.size(); i++)
            db.insert(cliente.get(i));



        Toast.makeText(context, "Se crearon correctamente los clientes default", Toast.LENGTH_SHORT).show();

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

        Toast.makeText(context, "Se crearon correctamente los productos default", Toast.LENGTH_SHORT).show();
    }

    static public void InsertRuta(Context context)
    {
        rutaSQL dbDelete = new rutaSQL(context);
        dbDelete.deleteAll();

        Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show();

        ArrayList<ruta> ruta = new ArrayList<>();

        ruta.add(new ruta(0, 0, 0, "Ruta 1"));
        ruta.add(new ruta(0, 0, 0, "Ruta 2"));
        ruta.add(new ruta(0, 0, 0, "Ruta 3"));


        rutaSQL db = new rutaSQL(context);
        for(int i = 0; i < ruta.size(); i++)
            db.insert(ruta.get(i));

        Toast.makeText(context, "Se crearon correctamente las rutas default", Toast.LENGTH_SHORT).show();
    }
    static public void InsertRutaDetalle(Context context)
    {
        rutaDetalleSQL dbDelete = new rutaDetalleSQL(context);
        dbDelete.deleteAll();

        Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show();

        ArrayList<rutaDetalle> rutaDetalle = new ArrayList<>();

        rutaDetalle.add(new rutaDetalle(0, 1, 1, 1));
        rutaDetalle.add(new rutaDetalle(0, 1, 1, 2));
        rutaDetalle.add(new rutaDetalle(0, 1, 1, 3));
        rutaDetalle.add(new rutaDetalle(0, 2, 1, 4));
        rutaDetalle.add(new rutaDetalle(0, 2, 1, 5));

        rutaDetalle.add(new rutaDetalle(0, 2, 2, 6));
        rutaDetalle.add(new rutaDetalle(0, 2, 2, 7));
        rutaDetalle.add(new rutaDetalle(0, 2, 2, 8));
        rutaDetalle.add(new rutaDetalle(0, 2, 2, 9));
        rutaDetalle.add(new rutaDetalle(0, 2, 2, 10));

        rutaDetalle.add(new rutaDetalle(0, 3, 3, 11));
        rutaDetalle.add(new rutaDetalle(0, 3, 3, 12));
        rutaDetalle.add(new rutaDetalle(0, 3, 3, 13));
        rutaDetalle.add(new rutaDetalle(0, 3, 3, 14));
        rutaDetalle.add(new rutaDetalle(0, 3, 3, 15));


        rutaDetalleSQL db = new rutaDetalleSQL(context);
        for(int i = 0; i < rutaDetalle.size(); i++)
            db.insert(rutaDetalle.get(i));

        Toast.makeText(context, "Asignaron los vendedores y clientes a la ruta", Toast.LENGTH_SHORT).show();
    }
    static void asignarProductosAClientes(Context context)
    {
        productoClienteSQL dbDelete = new productoClienteSQL(context);
        dbDelete.deleteAll();

        Toast.makeText(context, "Borrado correctamente", Toast.LENGTH_SHORT).show();

        ArrayList<productoCliente> productoCliente = new ArrayList<>();

        productoCliente.add(new productoCliente(0, 1, 1, 10));
        productoCliente.add(new productoCliente(0, 2, 1, 10));
        productoCliente.add(new productoCliente(0, 3, 1, 10));
        productoCliente.add(new productoCliente(0, 4, 1, 10));
        productoCliente.add(new productoCliente(0, 5, 1, 10));

        productoCliente.add(new productoCliente(0, 1, 2, 10));
        productoCliente.add(new productoCliente(0, 2, 2, 10));
        productoCliente.add(new productoCliente(0, 3, 2, 10));
        productoCliente.add(new productoCliente(0, 4, 2, 10));
        productoCliente.add(new productoCliente(0, 5, 2, 10));

        productoCliente.add(new productoCliente(0, 1, 3, 10));
        productoCliente.add(new productoCliente(0, 2, 3, 10));
        productoCliente.add(new productoCliente(0, 3, 3, 10));
        productoCliente.add(new productoCliente(0, 4, 3, 10));
        productoCliente.add(new productoCliente(0, 5, 3, 10));

        productoCliente.add(new productoCliente(0, 1, 4, 10));
        productoCliente.add(new productoCliente(0, 2, 4, 10));
        productoCliente.add(new productoCliente(0, 3, 4, 10));
        productoCliente.add(new productoCliente(0, 4, 4, 10));
        productoCliente.add(new productoCliente(0, 5, 4, 10));

        productoCliente.add(new productoCliente(0, 1, 5, 10));
        productoCliente.add(new productoCliente(0, 2, 5, 10));
        productoCliente.add(new productoCliente(0, 3, 5, 10));
        productoCliente.add(new productoCliente(0, 4, 5, 10));
        productoCliente.add(new productoCliente(0, 5, 5, 10));

        productoCliente.add(new productoCliente(0, 1, 6, 10));
        productoCliente.add(new productoCliente(0, 2, 6, 10));
        productoCliente.add(new productoCliente(0, 3, 6, 10));
        productoCliente.add(new productoCliente(0, 4, 6, 10));
        productoCliente.add(new productoCliente(0, 5, 6, 10));

        productoCliente.add(new productoCliente(0, 1, 7, 10));
        productoCliente.add(new productoCliente(0, 2, 7, 10));
        productoCliente.add(new productoCliente(0, 3, 7, 10));
        productoCliente.add(new productoCliente(0, 4, 7, 10));
        productoCliente.add(new productoCliente(0, 5, 7, 10));

        productoCliente.add(new productoCliente(0, 1, 8, 10));
        productoCliente.add(new productoCliente(0, 2, 8, 10));
        productoCliente.add(new productoCliente(0, 3, 8, 10));
        productoCliente.add(new productoCliente(0, 4, 8, 10));
        productoCliente.add(new productoCliente(0, 5, 8, 10));

        productoCliente.add(new productoCliente(0, 1, 9, 10));
        productoCliente.add(new productoCliente(0, 2, 9, 10));
        productoCliente.add(new productoCliente(0, 3, 9, 10));
        productoCliente.add(new productoCliente(0, 4, 9, 10));
        productoCliente.add(new productoCliente(0, 5, 9, 10));

        productoCliente.add(new productoCliente(0, 1, 10, 10));
        productoCliente.add(new productoCliente(0, 2, 10, 10));
        productoCliente.add(new productoCliente(0, 3, 10, 10));
        productoCliente.add(new productoCliente(0, 4, 10, 10));
        productoCliente.add(new productoCliente(0, 5, 10, 10));

        productoCliente.add(new productoCliente(0, 1, 11, 10));
        productoCliente.add(new productoCliente(0, 2, 11, 10));
        productoCliente.add(new productoCliente(0, 3, 11, 10));
        productoCliente.add(new productoCliente(0, 4, 11, 10));
        productoCliente.add(new productoCliente(0, 5, 11, 10));

        productoCliente.add(new productoCliente(0, 1, 12, 10));
        productoCliente.add(new productoCliente(0, 2, 12, 10));
        productoCliente.add(new productoCliente(0, 3, 12, 10));
        productoCliente.add(new productoCliente(0, 4, 12, 10));
        productoCliente.add(new productoCliente(0, 5, 12, 10));

        productoCliente.add(new productoCliente(0, 1, 13, 10));
        productoCliente.add(new productoCliente(0, 2, 13, 10));
        productoCliente.add(new productoCliente(0, 3, 13, 10));
        productoCliente.add(new productoCliente(0, 4, 13, 10));
        productoCliente.add(new productoCliente(0, 5, 13, 10));

        productoCliente.add(new productoCliente(0, 1, 14, 10));
        productoCliente.add(new productoCliente(0, 2, 14, 10));
        productoCliente.add(new productoCliente(0, 3, 14, 10));
        productoCliente.add(new productoCliente(0, 4, 14, 10));
        productoCliente.add(new productoCliente(0, 5, 14, 10));

        productoCliente.add(new productoCliente(0, 1, 15, 10));
        productoCliente.add(new productoCliente(0, 2, 15, 10));
        productoCliente.add(new productoCliente(0, 3, 15, 10));
        productoCliente.add(new productoCliente(0, 4, 15, 10));
        productoCliente.add(new productoCliente(0, 5, 15, 10));



        productoClienteSQL db = new productoClienteSQL(context);
        for(int i = 0; i < productoCliente.size(); i++)
            db.insert(productoCliente.get(i));

        Toast.makeText(context, "Asignaron los productos a los clientes", Toast.LENGTH_SHORT).show();
    }
}
