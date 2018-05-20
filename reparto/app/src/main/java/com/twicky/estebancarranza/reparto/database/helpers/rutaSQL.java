package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_ruta;
import com.twicky.estebancarranza.reparto.models.ruta;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class rutaSQL extends SQLHelper {
    public rutaSQL(Context context) {
        super(context);
    }

    public long insert(ruta ruta)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_ruta.column.name.rutaConfirmada, ruta.getRutaConfirmada());
        values.put(tbl_psm_ruta.column.name.rutaIniciada, ruta.getRutaIniciada());
        values.put(tbl_psm_ruta.column.name.titulo, ruta.getTitulo());

        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_ruta.name, null, values);

        db.close();

        return id;

    }
    public long update(ruta ruta)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_ruta.column.name.rutaConfirmada, ruta.getRutaConfirmada());
        values.put(tbl_psm_ruta.column.name.rutaIniciada, ruta.getRutaIniciada());
        values.put(tbl_psm_ruta.column.name.titulo, ruta.getTitulo());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_ruta.column.name.idRuta + " = " + ruta.getIdRuta();
        id = db.update(tbl_psm_ruta.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_ruta.column.name.idRuta + " = " + id;
        db.delete(tbl_psm_ruta.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tbl_psm_ruta.name, null, null);

        db.close();
    }
    public ruta getRuta(int idruta)
    {
        ruta ruta = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_ruta.column.name.idRuta + " = " + idruta;

        Cursor cursor = db.query(tbl_psm_ruta.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            ruta = new ruta();

            ruta.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_ruta.column.name.idRuta)));
            ruta.setRutaConfirmada(cursor.getInt(cursor.getColumnIndex(tbl_psm_ruta.column.name.rutaConfirmada)));
            ruta.setRutaIniciada(cursor.getInt(cursor.getColumnIndex(tbl_psm_ruta.column.name.rutaIniciada)));
            ruta.setTitulo(cursor.getString(cursor.getColumnIndex(tbl_psm_ruta.column.name.titulo)));

            cursor.close();
        }
        db.close();

        return ruta;
    }


    public ArrayList<ruta> getRutas(int limit1, int limit2)
    {
        ArrayList<ruta> rutas = new ArrayList<ruta>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_ruta.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                ruta ruta = new ruta();

                ruta.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_ruta.column.name.idRuta)));
                ruta.setRutaConfirmada(cursor.getInt(cursor.getColumnIndex(tbl_psm_ruta.column.name.rutaConfirmada)));
                ruta.setRutaIniciada(cursor.getInt(cursor.getColumnIndex(tbl_psm_ruta.column.name.rutaIniciada)));
                ruta.setTitulo(cursor.getString(cursor.getColumnIndex(tbl_psm_ruta.column.name.titulo)));


                rutas.add(ruta);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return rutas;
    }
}
