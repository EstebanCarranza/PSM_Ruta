package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_configuracion;
import com.twicky.estebancarranza.reparto.models.configuracion;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class configuracionSQL extends SQLHelper {
    public configuracionSQL(Context context) {
        super(context);
    }

    public long insert(configuracion configuracion)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_configuracion.column.name.idVendedor, configuracion.getIdVendedor());
        values.put(tbl_psm_configuracion.column.name.idTema, configuracion.getIdTema());
        values.put(tbl_psm_configuracion.column.name.activarPIN, configuracion.getActivarPIN());
        values.put(tbl_psm_configuracion.column.name.PIN, configuracion.getPIN());

        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_configuracion.name, null, values);

        db.close();

        return id;

    }
    public long update(configuracion configuracion)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_configuracion.column.name.idVendedor, configuracion.getIdVendedor());
        values.put(tbl_psm_configuracion.column.name.idTema, configuracion.getIdTema());
        values.put(tbl_psm_configuracion.column.name.activarPIN, configuracion.getActivarPIN());
        values.put(tbl_psm_configuracion.column.name.PIN, configuracion.getPIN());


        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_configuracion.column.name.idConfiguracion + " = " + configuracion.getIdConfiguracion();
        id = db.update(tbl_psm_configuracion.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_configuracion.column.name.idConfiguracion + " = " + id;
        db.delete(tbl_psm_configuracion.name, where, null);

        db.close();
    }
    public configuracion getconfiguracion(int id)
    {
        configuracion configuracion = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_configuracion.column.name.idConfiguracion + " = " + id;

        Cursor cursor = db.query(tbl_psm_configuracion.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            configuracion = new configuracion();

            configuracion.setIdConfiguracion(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.idConfiguracion)));
            configuracion.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.idVendedor)));
            configuracion.setIdTema(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.idTema)));
            configuracion.setActivarPIN(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.activarPIN)));
            configuracion.setPIN(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.PIN)));

            cursor.close();
        }
        db.close();

        return configuracion;
    }


    public ArrayList<configuracion> getconfiguraciones(int limit1, int limit2)
    {
        ArrayList<configuracion> ListConfiguracion = new ArrayList<configuracion>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_configuracion.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                configuracion configuracion = new configuracion();

                configuracion.setIdConfiguracion(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.idConfiguracion)));
                configuracion.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.idVendedor)));
                configuracion.setIdTema(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.idTema)));
                configuracion.setActivarPIN(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.activarPIN)));
                configuracion.setPIN(cursor.getInt(cursor.getColumnIndex(tbl_psm_configuracion.column.name.PIN)));

                ListConfiguracion.add(configuracion);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return ListConfiguracion;
    }
}
