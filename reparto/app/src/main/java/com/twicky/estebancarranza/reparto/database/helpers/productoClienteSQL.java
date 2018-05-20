package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_productoCliente;
import com.twicky.estebancarranza.reparto.models.productoCliente;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class productoClienteSQL extends SQLHelper {
    public productoClienteSQL(Context context) {
        super(context);
    }

    public long insert(productoCliente productoCliente)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_productoCliente.column.name.idProducto, productoCliente.getIdProducto());
        values.put(tbl_psm_productoCliente.column.name.idCliente, productoCliente.getIdCliente());
        values.put(tbl_psm_productoCliente.column.name.cantidad, productoCliente.getCantidad());


        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_productoCliente.name, null, values);

        db.close();

        return id;

    }
    public long update(productoCliente productoCliente)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_productoCliente.column.name.idProducto, productoCliente.getIdProducto());
        values.put(tbl_psm_productoCliente.column.name.idCliente, productoCliente.getIdCliente());
        values.put(tbl_psm_productoCliente.column.name.cantidad, productoCliente.getCantidad());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_productoCliente.column.name.idProductoCliente + " = " + productoCliente.getIdProductoCliente();
        id = db.update(tbl_psm_productoCliente.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_productoCliente.column.name.idProductoCliente + " = " + id;
        db.delete(tbl_psm_productoCliente.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tbl_psm_productoCliente.name, null, null);

        db.close();
    }
    public productoCliente getproductoCliente(int id)
    {
        productoCliente productoCliente = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_productoCliente.column.name.idProductoCliente + " = " + id;

        Cursor cursor = db.query(tbl_psm_productoCliente.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            productoCliente = new productoCliente();

            productoCliente.setIdProductoCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_productoCliente.column.name.idProductoCliente)));
            productoCliente.setIdProducto(cursor.getInt(cursor.getColumnIndex(tbl_psm_productoCliente.column.name.idProducto)));
            productoCliente.setIdCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_productoCliente.column.name.idCliente)));
            productoCliente.setCantidad(cursor.getFloat(cursor.getColumnIndex(tbl_psm_productoCliente.column.name.cantidad)));


            cursor.close();
        }
        db.close();

        return productoCliente;
    }


    public ArrayList<productoCliente> getproductoClientees(int limit1, int limit2)
    {
        ArrayList<productoCliente> ListProductoCliente = new ArrayList<productoCliente>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_productoCliente.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                productoCliente productoCliente = new productoCliente();

                productoCliente.setIdProductoCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_productoCliente.column.name.idProductoCliente)));
                productoCliente.setIdProducto(cursor.getInt(cursor.getColumnIndex(tbl_psm_productoCliente.column.name.idProducto)));
                productoCliente.setIdCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_productoCliente.column.name.idCliente)));
                productoCliente.setCantidad(cursor.getFloat(cursor.getColumnIndex(tbl_psm_productoCliente.column.name.cantidad)));

                ListProductoCliente.add(productoCliente);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return ListProductoCliente;
    }
}
