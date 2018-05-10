package com.twicky.estebancarranza.reparto.database.helpers;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_cliente;
import com.twicky.estebancarranza.reparto.datos.cliente;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class clienteSQL extends SQLHelper {

    public clienteSQL(Context context) {
        super(context);
    }

    public void insert(cliente Cliente)
    {
        ContentValues values = new ContentValues();
        values.put(tbl_psm_cliente.column.name.rfc, Cliente.getRfc());
        values.put(tbl_psm_cliente.column.name.razonSocial, Cliente.getNombre());
        values.put(tbl_psm_cliente.column.name.direccion, Cliente.getDomicilio());
        values.put(tbl_psm_cliente.column.name.telefono, Cliente.getTelefono());
        values.put(tbl_psm_cliente.column.name.coordenada, Cliente.getCoordenada());

        SQLiteDatabase db = getWritableDatabase();

        long id = db.insert(tbl_psm_cliente.name, null, values);

        db.close();

    }
    public void update(cliente Cliente)
    {
        ContentValues values = new ContentValues();
        values.put(tbl_psm_cliente.column.name.rfc, Cliente.getRfc());
        values.put(tbl_psm_cliente.column.name.razonSocial, Cliente.getNombre());
        values.put(tbl_psm_cliente.column.name.direccion, Cliente.getDomicilio());
        values.put(tbl_psm_cliente.column.name.telefono, Cliente.getTelefono());
        values.put(tbl_psm_cliente.column.name.coordenada, Cliente.getCoordenada());

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
    public cliente getCliente(int idCliente)
    {
        cliente Cliente = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_cliente.column.name.idCliente + " = " + idCliente;

        Cursor cursor = db.query(tbl_psm_cliente.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            String name = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.razonSocial));
            String domicilio = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.direccion));
            //String estadoCliente = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.estadoCliente));

            Cliente = new cliente(name, domicilio, estado_cliente.sinConfirmar);
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
                int idCliente = cursor.getInt(cursor.getColumnIndex(tbl_psm_cliente.column.name.idCliente));
                String name = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.razonSocial));
                String direccion = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.direccion));
                //String estado = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.estadoCliente));

                cliente clienteLocal = new cliente(name, direccion, estado_cliente.sinConfirmar);

                Clientes.add(clienteLocal);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return Clientes;
    }
}
