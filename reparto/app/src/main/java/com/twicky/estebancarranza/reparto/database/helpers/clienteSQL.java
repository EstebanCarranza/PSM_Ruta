package com.twicky.estebancarranza.reparto.database.helpers;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.LinkAddress;

import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_cliente;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class clienteSQL extends SQLHelper {

    public clienteSQL(Context context) {
        super(context);
    }

    public long insert(cliente Cliente)
    {
        long id = 0;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_cliente.column.name.rfc, Cliente.getRfc());
        values.put(tbl_psm_cliente.column.name.razonSocial, Cliente.getNombre());
        values.put(tbl_psm_cliente.column.name.direccion, Cliente.getDomicilio());
        values.put(tbl_psm_cliente.column.name.telefono, Cliente.getTelefono());
        values.put(tbl_psm_cliente.column.name.latitude, Cliente.getCoordenada().latitude);
        values.put(tbl_psm_cliente.column.name.longitude, Cliente.getCoordenada().longitude);


        SQLiteDatabase db = getWritableDatabase();

         id = db.insert(tbl_psm_cliente.name, null, values);

        db.close();
        return id;
    }
    public void update(cliente Cliente)
    {
        ContentValues values = new ContentValues();
        values.put(tbl_psm_cliente.column.name.rfc, Cliente.getRfc());
        values.put(tbl_psm_cliente.column.name.razonSocial, Cliente.getNombre());
        values.put(tbl_psm_cliente.column.name.direccion, Cliente.getDomicilio());
        values.put(tbl_psm_cliente.column.name.telefono, Cliente.getTelefono());
        values.put(tbl_psm_cliente.column.name.latitude, Cliente.getCoordenada().latitude);
        values.put(tbl_psm_cliente.column.name.longitude, Cliente.getCoordenada().longitude);

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_cliente.column.name.idCliente + " = " + Cliente.getId();
        long id = db.update(tbl_psm_cliente.name, values, where, null);
        db.close();
    }
    public void delete(int idCliente)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_cliente.column.name.idCliente + " = " + idCliente;
        db.delete(tbl_psm_cliente.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tbl_psm_cliente.name, null, null);

        db.close();
    }
    public cliente getCliente(int idCliente)
    {
        cliente Cliente = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_cliente.column.name.idCliente + " = " + idCliente;

        Cursor cursor = db.query(tbl_psm_cliente.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {
            cliente clienteLocal = new cliente();

            clienteLocal.setId(cursor.getInt(cursor.getColumnIndex(tbl_psm_cliente.column.name.idCliente)));
            clienteLocal.setRfc(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.rfc)));
            clienteLocal.setNombre(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.razonSocial)));
            clienteLocal.setDomicilio(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.direccion)));
            clienteLocal.setEstadoActual(estado_cliente.sinConfirmar);
            clienteLocal.setTelefono(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.telefono)));
            float Latitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.latitude));
            float Longitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.longitude));
            LatLng coordenada = new LatLng(Latitude, Longitude);
            clienteLocal.setCoordenada(coordenada);
            //String estadoCliente = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.estadoCliente));


            cursor.close();
        }
        db.close();

        return Cliente;
    }

    public ArrayList<cliente> getCliente(int limit1, int limit2)
    {
        ArrayList<cliente> Clientes = new ArrayList<cliente>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_cliente.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                cliente clienteLocal = new cliente();

                clienteLocal.setId(cursor.getInt(cursor.getColumnIndex(tbl_psm_cliente.column.name.idCliente)));
                clienteLocal.setRfc(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.rfc)));
                clienteLocal.setNombre(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.razonSocial)));
                clienteLocal.setDomicilio(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.direccion)));
                clienteLocal.setEstadoActual(estado_cliente.sinConfirmar);
                clienteLocal.setTelefono(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.telefono)));
                float Latitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.latitude));
                float Longitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.longitude));
                LatLng coordenada = new LatLng(Latitude, Longitude);
                clienteLocal.setCoordenada(coordenada);
                //String estadoCliente = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.estadoCliente));

                Clientes.add(clienteLocal);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return Clientes;
    }
}
