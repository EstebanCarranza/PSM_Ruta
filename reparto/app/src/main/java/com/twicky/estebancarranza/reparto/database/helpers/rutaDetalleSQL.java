package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_rutaDetalle;
import com.twicky.estebancarranza.reparto.models.rutaDetalle;
import com.twicky.estebancarranza.reparto.models.vendedor;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class rutaDetalleSQL extends SQLHelper {
    public rutaDetalleSQL(Context context) {
        super(context);
    }
    public long insert(rutaDetalle rutaDetalle)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_rutaDetalle.column.name.idRuta, rutaDetalle.getIdRuta());
        values.put(tbl_psm_rutaDetalle.column.name.idVendedor, rutaDetalle.getIdVendedor());
        values.put(tbl_psm_rutaDetalle.column.name.idProductoCliente, rutaDetalle.getIdProductoCliente());

        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_rutaDetalle.name, null, values);

        db.close();

        return id;

    }
    public long update(rutaDetalle rutaDetalle)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_rutaDetalle.column.name.idRuta, rutaDetalle.getIdRuta());
        values.put(tbl_psm_rutaDetalle.column.name.idVendedor, rutaDetalle.getIdVendedor());
        values.put(tbl_psm_rutaDetalle.column.name.idProductoCliente, rutaDetalle.getIdProductoCliente());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_rutaDetalle.column.name.idRutaDetalle + " = " + rutaDetalle.getIdRutaDetalle();
        id = db.update(tbl_psm_rutaDetalle.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_rutaDetalle.column.name.idRutaDetalle + " = " + id;
        db.delete(tbl_psm_rutaDetalle.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tbl_psm_rutaDetalle.name, null, null);

        db.close();
    }
    public rutaDetalle getrutaDetalle(int id)
    {
        rutaDetalle rutaDetalle = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_rutaDetalle.column.name.idRutaDetalle + " = " + id;

        Cursor cursor = db.query(tbl_psm_rutaDetalle.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            rutaDetalle = new rutaDetalle();

            rutaDetalle.setIdRutaDetalle(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idRutaDetalle)));
            rutaDetalle.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idRuta)));
            rutaDetalle.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idVendedor)));
            rutaDetalle.setIdProductoCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idProductoCliente)));



            cursor.close();
        }
        db.close();

        return rutaDetalle;
    }


    public ArrayList<rutaDetalle> getrutaDetallees(int limit1, int limit2)
    {
        ArrayList<rutaDetalle> ListRutaDetalle = new ArrayList<rutaDetalle>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_rutaDetalle.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                rutaDetalle rutaDetalle = new rutaDetalle();

                rutaDetalle.setIdRutaDetalle(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idRutaDetalle)));
                rutaDetalle.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idRuta)));
                rutaDetalle.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idVendedor)));
                rutaDetalle.setIdProductoCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idProductoCliente)));


                ListRutaDetalle.add(rutaDetalle);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return ListRutaDetalle;
    }
    public rutaDetalle validarRuta(vendedor vendedor)
    {
        rutaDetalle rutaDetalle = null;

        SQLiteDatabase db = getWritableDatabase();
        String where =
                "idVendedor = " + vendedor.getIdVendedor() +
                ""
                ;

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_rutaDetalle.name + " "+ where +";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                rutaDetalle = new rutaDetalle();

                rutaDetalle.setIdRutaDetalle(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idRutaDetalle)));
                rutaDetalle.setIdRuta(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idRuta)));
                rutaDetalle.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idVendedor)));
                rutaDetalle.setIdProductoCliente(cursor.getInt(cursor.getColumnIndex(tbl_psm_rutaDetalle.column.name.idProductoCliente)));

                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();


        return rutaDetalle;
    }
}
