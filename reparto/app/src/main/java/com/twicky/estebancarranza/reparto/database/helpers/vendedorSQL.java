package com.twicky.estebancarranza.reparto.database.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_vendedor;
import com.twicky.estebancarranza.reparto.models.vendedor;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class vendedorSQL extends SQLHelper{
    public vendedorSQL(Context context) {
        super(context);
    }

    public long insert(vendedor vendedor)
    {
        long id = -1;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_vendedor.column.name.correo, vendedor.getCorreo());
        values.put(tbl_psm_vendedor.column.name.contrasenia, vendedor.getContrasenia());
        values.put(tbl_psm_vendedor.column.name.nombres, vendedor.getNombres());
        values.put(tbl_psm_vendedor.column.name.appat, vendedor.getAppat());
        values.put(tbl_psm_vendedor.column.name.apmat, vendedor.getApmat());
        values.put(tbl_psm_vendedor.column.name.fechNac, vendedor.getFechNac());

        SQLiteDatabase db = getWritableDatabase();

        id = db.insert(tbl_psm_vendedor.name, null, values);

        db.close();

        return id;

    }
    public long update(vendedor vendedor)
    {
        long id = -1;
        ContentValues values = new ContentValues();

        values.put(tbl_psm_vendedor.column.name.correo, vendedor.getCorreo());
        values.put(tbl_psm_vendedor.column.name.contrasenia, vendedor.getContrasenia());
        values.put(tbl_psm_vendedor.column.name.nombres, vendedor.getNombres());
        values.put(tbl_psm_vendedor.column.name.appat, vendedor.getAppat());
        values.put(tbl_psm_vendedor.column.name.apmat, vendedor.getApmat());
        values.put(tbl_psm_vendedor.column.name.fechNac, vendedor.getFechNac());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_vendedor.column.name.idVendedor + " = " + vendedor.getIdVendedor();
         id = db.update(tbl_psm_vendedor.name, values, where, null);
        db.close();

        return id;
    }
    public void delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_vendedor.column.name.idVendedor + " = " + id;
        db.delete(tbl_psm_vendedor.name, where, null);

        db.close();
    }
    public vendedor getVendedor(int idVendedor)
    {
        vendedor vendedor = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_vendedor.column.name.idVendedor + " = " + idVendedor;

        Cursor cursor = db.query(tbl_psm_vendedor.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            vendedor = new vendedor();

            vendedor.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_vendedor.column.name.idVendedor)));
            vendedor.setCorreo(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.correo)));
            vendedor.setContrasenia(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.contrasenia)));
            vendedor.setNombres(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.nombres)));
            vendedor.setAppat(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.appat)));
            vendedor.setApmat(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.apmat)));
            vendedor.setFechNac(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.fechNac)));


            cursor.close();
        }
        db.close();

        return vendedor;
    }

    public vendedor validarInicioSesion(String correo, String contrasenia)
    {
        vendedor vendedor = null;


        SQLiteDatabase db = getReadableDatabase();
        String where =
                tbl_psm_vendedor.column.name.correo + " = '" + correo + "' AND " +
                tbl_psm_vendedor.column.name.contrasenia + " = '" + contrasenia + "'";

        Cursor cursor = db.query(tbl_psm_vendedor.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {

            vendedor = new vendedor();

            vendedor.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_vendedor.column.name.idVendedor)));
            vendedor.setCorreo(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.correo)));
            vendedor.setContrasenia(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.contrasenia)));
            vendedor.setNombres(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.nombres)));
            vendedor.setAppat(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.appat)));
            vendedor.setApmat(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.apmat)));
            vendedor.setFechNac(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.fechNac)));


            cursor.close();
        }
        db.close();

        return vendedor;

    }

    public ArrayList<vendedor> getVendedores(int limit1, int limit2)
    {
        ArrayList<vendedor> vendedores = new ArrayList<vendedor>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_vendedor.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                vendedor vendedor = new vendedor();

                vendedor.setIdVendedor(cursor.getInt(cursor.getColumnIndex(tbl_psm_vendedor.column.name.idVendedor)));
                vendedor.setCorreo(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.correo)));
                vendedor.setContrasenia(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.contrasenia)));
                vendedor.setNombres(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.nombres)));
                vendedor.setAppat(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.appat)));
                vendedor.setApmat(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.apmat)));
                vendedor.setFechNac(cursor.getString(cursor.getColumnIndex(tbl_psm_vendedor.column.name.fechNac)));

                vendedores.add(vendedor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return vendedores;
    }
}