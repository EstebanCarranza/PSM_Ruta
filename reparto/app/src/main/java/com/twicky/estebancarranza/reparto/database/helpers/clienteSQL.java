package com.twicky.estebancarranza.reparto.database.helpers;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.LinkAddress;

import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.database.SQLHelper;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_cliente;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import java.sql.Timestamp;
import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.util.TimeStampConverts.convertStringToTimestamp;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class clienteSQL extends SQLHelper {

    public clienteSQL(Context context) {
        super(context);
    }

    public long insert(cliente Cliente)
    {
        long id = 0;
        ContentValues values = new ContentValues();
        values.put(tbl_psm_cliente.column.name.rfc, Cliente.getRfc());
        values.put(tbl_psm_cliente.column.name.razonSocial, Cliente.getNombre());
        values.put(tbl_psm_cliente.column.name.direccion, Cliente.getDomicilio());
        values.put(tbl_psm_cliente.column.name.telefono, Cliente.getTelefono());
        values.put(tbl_psm_cliente.column.name.latitude, Cliente.getCoordenada().latitude);
        values.put(tbl_psm_cliente.column.name.longitude, Cliente.getCoordenada().longitude);
        values.put(tbl_psm_cliente.column.name.fechaUltimaModificacion, Cliente.getFechaUltimaModificacion().toString());



        SQLiteDatabase db = getWritableDatabase();

         id = db.insert(tbl_psm_cliente.name, null, values);

        db.close();
        return id;
    }
    public void update(cliente Cliente)
    {
        ContentValues values = new ContentValues();
        values.put(tbl_psm_cliente.column.name.rfc, Cliente.getRfc());
        values.put(tbl_psm_cliente.column.name.razonSocial, Cliente.getNombre());
        values.put(tbl_psm_cliente.column.name.direccion, Cliente.getDomicilio());
        values.put(tbl_psm_cliente.column.name.telefono, Cliente.getTelefono());
        values.put(tbl_psm_cliente.column.name.latitude, Cliente.getCoordenada().latitude);
        values.put(tbl_psm_cliente.column.name.longitude, Cliente.getCoordenada().longitude);
        values.put(tbl_psm_cliente.column.name.fechaUltimaModificacion, Cliente.getFechaUltimaModificacion().toString());

        SQLiteDatabase db = getWritableDatabase();

        String where = tbl_psm_cliente.column.name.rfc + " = '" + Cliente.getRfc() + "'";
        long id = db.update(tbl_psm_cliente.name, values, where, null);
        db.close();
    }
    public void delete(int idCliente)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = tbl_psm_cliente.column.name.idCliente + " = " + idCliente;
        db.delete(tbl_psm_cliente.name, where, null);

        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tbl_psm_cliente.name, null, null);

        db.close();
    }
    public cliente getCliente(int idCliente)
    {
        cliente Cliente = null;

        SQLiteDatabase db = getReadableDatabase();
        String where = tbl_psm_cliente.column.name.idCliente + " = " + idCliente;

        Cursor cursor = db.query(tbl_psm_cliente.name, null, where, null, null, null, null);
        if(cursor.moveToFirst())
        {
            cliente clienteLocal = new cliente();

            clienteLocal.setId(cursor.getInt(cursor.getColumnIndex(tbl_psm_cliente.column.name.idCliente)));
            clienteLocal.setRfc(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.rfc)));
            clienteLocal.setNombre(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.razonSocial)));
            clienteLocal.setDomicilio(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.direccion)));
            clienteLocal.setEstadoActual(estado_cliente.sinConfirmar);
            clienteLocal.setTelefono(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.telefono)));
            float Latitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.latitude));
            float Longitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.longitude));
            LatLng coordenada = new LatLng(Latitude, Longitude);
            clienteLocal.setCoordenada(coordenada);
            clienteLocal.setFechaUltimaModificacion(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.fechaUltimaModificacion)));

            Cliente = clienteLocal;

            cursor.close();
        }
        db.close();

        return Cliente;
    }

    public ArrayList<cliente> getCliente(int limit1, int limit2)
    {
        ArrayList<cliente> Clientes = new ArrayList<cliente>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from " + tbl_psm_cliente.name + ";", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                cliente clienteLocal = new cliente();

                clienteLocal.setId(cursor.getInt(cursor.getColumnIndex(tbl_psm_cliente.column.name.idCliente)));
                clienteLocal.setRfc(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.rfc)));
                clienteLocal.setNombre(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.razonSocial)));
                clienteLocal.setDomicilio(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.direccion)));
                clienteLocal.setEstadoActual(estado_cliente.sinConfirmar);
                clienteLocal.setTelefono(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.telefono)));
                float Latitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.latitude));
                float Longitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.longitude));
                LatLng coordenada = new LatLng(Latitude, Longitude);
                clienteLocal.setCoordenada(coordenada);
                String datetime = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.fechaUltimaModificacion));
                clienteLocal.setFechaUltimaModificacion(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.fechaUltimaModificacion)));


                Clientes.add(clienteLocal);
                cursor.moveToNext();
            }
            cursor.close();
        }
        db.close();

        return Clientes;
    }

    public ArrayList<cliente> insertIfNotExists(ArrayList<cliente> cliente)
    {
        int i = 0;
        int totalClientes = cliente.size();
        boolean encontrado = false;
        while(i < totalClientes) {
            SQLiteDatabase db = getWritableDatabase();
            String where = "where " +
                    tbl_psm_cliente.column.name.rfc + " = '" + cliente.get(i).getRfc() + "'";
            Cursor cursor = db.rawQuery("select * from " + tbl_psm_cliente.name + " " + where + ";", null);
            if (cursor.moveToFirst()) {
                encontrado = true;
                while (!cursor.isAfterLast()) {
                    cliente clienteLocal = new cliente();

                    clienteLocal.setId(cursor.getInt(cursor.getColumnIndex(tbl_psm_cliente.column.name.idCliente)));
                    clienteLocal.setRfc(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.rfc)));
                    clienteLocal.setNombre(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.razonSocial)));
                    clienteLocal.setDomicilio(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.direccion)));
                    clienteLocal.setEstadoActual(estado_cliente.sinConfirmar);
                    clienteLocal.setTelefono(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.telefono)));
                    float Latitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.latitude));
                    float Longitude = cursor.getFloat(cursor.getColumnIndex(tbl_psm_cliente.column.name.longitude));
                    LatLng coordenada = new LatLng(Latitude, Longitude);
                    clienteLocal.setCoordenada(coordenada);
                    String datetime = cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.fechaUltimaModificacion));
                    clienteLocal.setFechaUltimaModificacion(cursor.getString(cursor.getColumnIndex(tbl_psm_cliente.column.name.fechaUltimaModificacion)));

                    Timestamp fechaActual = convertStringToTimestamp(cliente.get(i).getFechaUltimaModificacion());
                    Timestamp fechaNueva = convertStringToTimestamp(clienteLocal.getFechaUltimaModificacion());
                        /*if(fechaActual.getTime() > fechaNueva.getTime())
                        {
                            //SQLite tiene el dato mas reciente

                        }
                        if(fechaActual.getTime() < fechaNueva.getTime())
                        {
                            //MySQL tiene el dato mas reciente
                        }*/


                    cursor.moveToNext();
                }
                cursor.close();
            }
            db.close();

            if(!encontrado)
            {
                insert(cliente.get(i));
            }
            encontrado = false;





            i++;
        }

        return cliente;
    }

}
