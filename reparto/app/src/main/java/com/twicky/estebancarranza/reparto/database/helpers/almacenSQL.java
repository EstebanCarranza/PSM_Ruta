package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_almacen;
import com.twicky.estebancarranza.reparto.models.almacen;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class almacenSQL extends SQLHelper {
    public almacenSQL(Context context) {
        super(context);
    }

    public long insert(almacen almacen)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_almacen.column.name.titulo, almacen.getTitulo());
        values.put(tbl_psm_almacen.column.name.direccion, almacen.getDireccion());
        values.put(tbl_psm_almacen.column.name.coordenada, almacen.getCoordenada());

        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_almacen.name, null, values);

        db.close();

        return id;

    }
    public long update(almacen almacen)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_almacen.column.name.titulo, almacen.getTitulo());
        values.put(tbl_psm_almacen.column.name.direccion, almacen.getDireccion());
        values.put(tbl_psm_almacen.column.name.coordenada, almacen.getCoordenada());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_almacen.column.name.idAlmacen + " = " + almacen.getIdAlmacen();
        id = db.update(tbl_psm_almacen.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_almacen.column.name.idAlmacen + " = " + id;
        db.delete(tbl_psm_almacen.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tbl_psm_almacen.name, null, null);

        db.close();
    }
    public almacen getAlmacen(int id)
    {
        almacen almacen = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_almacen.column.name.idAlmacen + " = " + id;

        Cursor cursor = db.query(tbl_psm_almacen.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            almacen = new almacen();

            almacen.setIdAlmacen(cursor.getInt(cursor.getColumnIndex(tbl_psm_almacen.column.name.idAlmacen)));
            almacen.setTitulo(cursor.getString(cursor.getColumnIndex(tbl_psm_almacen.column.name.titulo)));
            almacen.setDireccion(cursor.getString(cursor.getColumnIndex(tbl_psm_almacen.column.name.direccion)));
            almacen.setCoordenada(cursor.getString(cursor.getColumnIndex(tbl_psm_almacen.column.name.coordenada)));

            cursor.close();
        }
        db.close();

        return almacen;
    }


    public ArrayList<almacen> getalmacenes(int limit1, int limit2)
    {
        ArrayList<almacen> ListAlmacen = new ArrayList<almacen>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_almacen.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                almacen almacen = new almacen();

                almacen.setIdAlmacen(cursor.getInt(cursor.getColumnIndex(tbl_psm_almacen.column.name.idAlmacen)));
                almacen.setTitulo(cursor.getString(cursor.getColumnIndex(tbl_psm_almacen.column.name.titulo)));
                almacen.setDireccion(cursor.getString(cursor.getColumnIndex(tbl_psm_almacen.column.name.direccion)));
                almacen.setCoordenada(cursor.getString(cursor.getColumnIndex(tbl_psm_almacen.column.name.coordenada)));


                ListAlmacen.add(almacen);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return ListAlmacen;
    }
}
