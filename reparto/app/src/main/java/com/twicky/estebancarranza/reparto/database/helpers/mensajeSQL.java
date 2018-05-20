package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_mensaje;
import com.twicky.estebancarranza.reparto.models.mensaje;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class mensajeSQL extends SQLHelper {
    public mensajeSQL(Context context) {
        super(context);
    }

    public long insert(mensaje mensaje)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_mensaje.column.name.mensaje, mensaje.getMensaje());
        values.put(tbl_psm_mensaje.column.name.idRuta, mensaje.getIdRuta());
        values.put(tbl_psm_mensaje.column.name.idVendedor, mensaje.getIdVendedor());
        values.put(tbl_psm_mensaje.column.name.idAlmacen, mensaje.getIdAlmacen());
        values.put(tbl_psm_mensaje.column.name.fechaEnvio, mensaje.getFechaEnvio());
        values.put(tbl_psm_mensaje.column.name.me, mensaje.getMe());
        

        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_mensaje.name, null, values);

        db.close();

        return id;

    }
    public long update(mensaje mensaje)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_mensaje.column.name.mensaje, mensaje.getMensaje());
        values.put(tbl_psm_mensaje.column.name.idRuta, mensaje.getIdRuta());
        values.put(tbl_psm_mensaje.column.name.idVendedor, mensaje.getIdVendedor());
        values.put(tbl_psm_mensaje.column.name.idAlmacen, mensaje.getIdAlmacen());
        values.put(tbl_psm_mensaje.column.name.fechaEnvio, mensaje.getFechaEnvio());
        values.put(tbl_psm_mensaje.column.name.me, mensaje.getMe());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_mensaje.column.name.idMensaje + " = " + mensaje.getidMensaje();
        id = db.update(tbl_psm_mensaje.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_mensaje.column.name.idMensaje + " = " + id;
        db.delete(tbl_psm_mensaje.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tbl_psm_mensaje.name, null, null);

        db.close();
    }
    public mensaje getmensaje(int id)
    {
        mensaje mensaje = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_mensaje.column.name.idMensaje + " = " + id;

        Cursor cursor = db.query(tbl_psm_mensaje.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            mensaje = new mensaje();

            mensaje.setidMensaje(cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.idMensaje)));
            mensaje.setMensaje(cursor.getString(cursor.getColumnIndex(tbl_psm_mensaje.column.name.mensaje)));
            mensaje.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.idRuta)));
            mensaje.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.idVendedor)));
            mensaje.setIdAlmacen(cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.idAlmacen)));
            mensaje.setFechaEnvio(cursor.getString(cursor.getColumnIndex(tbl_psm_mensaje.column.name.fechaEnvio)));
            int MeLocal = cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.me));
            mensaje.setMe((MeLocal==1)?true:false);

            cursor.close();
        }
        db.close();

        return mensaje;
    }


    public ArrayList<mensaje> getmensajees(int limit1, int limit2)
    {
        ArrayList<mensaje> ListMensaje = new ArrayList<mensaje>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_mensaje.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                mensaje mensaje = new mensaje();

                mensaje.setidMensaje(cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.idMensaje)));
                mensaje.setMensaje(cursor.getString(cursor.getColumnIndex(tbl_psm_mensaje.column.name.mensaje)));
                mensaje.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.idRuta)));
                mensaje.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.idVendedor)));
                mensaje.setIdAlmacen(cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.idAlmacen)));
                mensaje.setFechaEnvio(cursor.getString(cursor.getColumnIndex(tbl_psm_mensaje.column.name.fechaEnvio)));
                int MeLocal = cursor.getInt(cursor.getColumnIndex(tbl_psm_mensaje.column.name.me));
                mensaje.setMe((MeLocal==1)?true:false);

                ListMensaje.add(mensaje);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return ListMensaje;
    }
}
