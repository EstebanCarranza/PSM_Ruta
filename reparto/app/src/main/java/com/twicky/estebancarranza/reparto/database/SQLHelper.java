package com.twicky.estebancarranza.reparto.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_almacen;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_cliente;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_configuracion;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_mensaje;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_producto;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_productoCliente;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_ruta;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_rutaCliente;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_rutaDetalle;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_vendedor;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class SQLHelper extends SQLiteOpenHelper{
private static final String DB_NAME = db.name + ".db";
private static final int DB_VERSION = 1;

    public SQLHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(db.create.db_psm);
        sqLiteDatabase.execSQL(tbl_psm_almacen.create);
        sqLiteDatabase.execSQL(tbl_psm_cliente.create);
        sqLiteDatabase.execSQL(tbl_psm_configuracion.create);
        sqLiteDatabase.execSQL(tbl_psm_mensaje.create);
        sqLiteDatabase.execSQL(tbl_psm_producto.create);
        sqLiteDatabase.execSQL(tbl_psm_productoCliente.create);
        sqLiteDatabase.execSQL(tbl_psm_ruta.create);
        sqLiteDatabase.execSQL(tbl_psm_rutaCliente.create);
        sqLiteDatabase.execSQL(tbl_psm_rutaDetalle.create);
        sqLiteDatabase.execSQL(tbl_psm_vendedor.create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(db.drop.db_psm);
        //sqLiteDatabase.execSQL(db.create.db_psm);
        onCreate(sqLiteDatabase);
    }

    public void getTables ()
    {
        ArrayList<String> arrTblNames = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table';", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                arrTblNames.add(cursor.getString(cursor.getColumnIndex("name")));

                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();


    }
}
