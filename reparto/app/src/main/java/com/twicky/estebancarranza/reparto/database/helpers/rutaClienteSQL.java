package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_rutaCliente;
import com.twicky.estebancarranza.reparto.models.rutaCliente;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class rutaClienteSQL extends SQLHelper{
    public rutaClienteSQL(Context context) {
        super(context);
    }
    public long insert(rutaCliente rutaCliente)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_rutaCliente.column.name.idRuta, rutaCliente.getIdRuta());
        values.put(tbl_psm_rutaCliente.column.name.idCliente, rutaCliente.getIdCliente());
        values.put(tbl_psm_rutaCliente.column.name.idProducto, rutaCliente.getIdProducto());
        values.put(tbl_psm_rutaCliente.column.name.cantidad, rutaCliente.getCantidad());


        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_rutaCliente.name, null, values);

        db.close();

        return id;

    }
    public long update(rutaCliente rutaCliente)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_rutaCliente.column.name.idRuta, rutaCliente.getIdRuta());
        values.put(tbl_psm_rutaCliente.column.name.idCliente, rutaCliente.getIdCliente());
        values.put(tbl_psm_rutaCliente.column.name.idProducto, rutaCliente.getIdProducto());
        values.put(tbl_psm_rutaCliente.column.name.cantidad, rutaCliente.getCantidad());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_rutaCliente.column.name.idRutaCliente + " = " + rutaCliente.getIdRutaCliente();
        id = db.update(tbl_psm_rutaCliente.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_rutaCliente.column.name.idRutaCliente + " = " + id;
        db.delete(tbl_psm_rutaCliente.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tbl_psm_rutaCliente.name, null, null);

        db.close();
    }
    public rutaCliente getrutaCliente(int id)
    {
        rutaCliente rutaCliente = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_rutaCliente.column.name.idRutaCliente + " = " + id;

        Cursor cursor = db.query(tbl_psm_rutaCliente.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            rutaCliente = new rutaCliente();

            rutaCliente.setIdRutaCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.idRutaCliente)));
            rutaCliente.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.idRuta)));
            rutaCliente.setIdCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.idCliente)));
            rutaCliente.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.idProducto)));
            rutaCliente.setCantidad(cursor.getFloat(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.cantidad)));


            cursor.close();
        }
        db.close();

        return rutaCliente;
    }


    public ArrayList<rutaCliente> getrutaClientees(int limit1, int limit2)
    {
        ArrayList<rutaCliente> ListRutaCliente = new ArrayList<rutaCliente>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_rutaCliente.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                rutaCliente rutaCliente = new rutaCliente();

                rutaCliente.setIdRutaCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.idRutaCliente)));
                rutaCliente.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.idRuta)));
                rutaCliente.setIdCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.idCliente)));
                rutaCliente.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.idProducto)));
                rutaCliente.setCantidad(cursor.getFloat(cursor.getColumnIndex(tbl_psm_rutaCliente.column.name.cantidad)));

                ListRutaCliente.add(rutaCliente);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return ListRutaCliente;
    }
}
