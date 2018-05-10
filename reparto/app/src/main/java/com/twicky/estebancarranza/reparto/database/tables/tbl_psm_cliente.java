package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_cliente {
    static public final String name = "tbl_psm_cliente";
    public class column
    {
        public class name
        {
            static public final String idCliente   = "idCliente";
            static public final String rfc       = "rfc";
            static public final String razonSocial  = "razonSocial";
            static public final String idRegimenFiscal = "idRegimenFiscal";
            static public final String direccion = "direccion";
            static public final String telefono = "telefono";
            static public final String latitude = "latitude";
            static public final String longitude = "longitude";
            static public final String estadoCliente = "estadoCliente";

        }

    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idCliente + " integer not null primary key autoincrement," +
                    column.name.rfc + " text," +
                    column.name.razonSocial + " text," +
                    column.name.idRegimenFiscal + " integer," +
                    column.name.direccion + " text," +
                    column.name.telefono + " text," +
                    column.name.latitude + " real," +
                    column.name.longitude + " real," +
                    column.name.estadoCliente + " integer" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
