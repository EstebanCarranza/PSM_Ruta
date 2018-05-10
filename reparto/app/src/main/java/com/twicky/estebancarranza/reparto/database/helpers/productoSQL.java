package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_producto;
import com.twicky.estebancarranza.reparto.models.custom_color;
import com.twicky.estebancarranza.reparto.models.producto;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class productoSQL extends SQLHelper {
    public productoSQL(Context context) {
        super(context);
    }

    public long insert(producto producto)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_producto.column.name.titulo, producto.getTitulo());
        values.put(tbl_psm_producto.column.name.descripcion, producto.getDescripcion());
        values.put(tbl_psm_producto.column.name.precioNormal, producto.getPrecioNormal());
        

        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_producto.name, null, values);

        db.close();

        return id;

    }
    public long update(producto producto)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_producto.column.name.titulo, producto.getTitulo());
        values.put(tbl_psm_producto.column.name.descripcion, producto.getDescripcion());
        values.put(tbl_psm_producto.column.name.precioNormal, producto.getPrecioNormal());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_producto.column.name.idProducto + " = " + producto.getIdProducto();
        id = db.update(tbl_psm_producto.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_producto.column.name.idProducto + " = " + id;
        db.delete(tbl_psm_producto.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tbl_psm_producto.name, null, null);

        db.close();
    }
    public producto getproducto(int id)
    {
        producto producto = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_producto.column.name.idProducto + " = " + id;

        Cursor cursor = db.query(tbl_psm_producto.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            producto = new producto();

            producto.setIdProducto(cursor.getInt(cursor.getColumnIndex(tbl_psm_producto.column.name.idProducto)));
            producto.setTitulo(cursor.getString(cursor.getColumnIndex(tbl_psm_producto.column.name.titulo)));
            producto.setDescripcion(cursor.getString(cursor.getColumnIndex(tbl_psm_producto.column.name.descripcion)));
            producto.setPrecioNormal(cursor.getFloat(cursor.getColumnIndex(tbl_psm_producto.column.name.precioNormal)));


            cursor.close();
        }
        db.close();

        return producto;
    }


    public ArrayList<producto> getproductos(int limit1, int limit2, custom_color colorConfirmado, custom_color colorNoConfirmado, custom_color colorDefault)
    {
        ArrayList<producto> ListProducto = new ArrayList<producto>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_producto.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                producto producto = new producto();

                producto.setIdProducto(cursor.getInt(cursor.getColumnIndex(tbl_psm_producto.column.name.idProducto)));
                producto.setTitulo(cursor.getString(cursor.getColumnIndex(tbl_psm_producto.column.name.titulo)));
                producto.setDescripcion(cursor.getString(cursor.getColumnIndex(tbl_psm_producto.column.name.descripcion)));
                producto.setPrecioNormal(cursor.getFloat(cursor.getColumnIndex(tbl_psm_producto.column.name.precioNormal)));
                producto.setColorConfirmado(colorConfirmado);
                producto.setColorNoConfirmado(colorNoConfirmado);
                producto.setColorDefault(colorDefault);

                ListProducto.add(producto);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return ListProducto;
    }
}
