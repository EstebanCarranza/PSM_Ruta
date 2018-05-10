package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_configuracion {
    static public final String name = "tbl_psm_configuracion";
    public class column
    {
        public class name
        {
            static public final String idConfiguracion   = "idConfiguracion";
            static public final String idVendedor       = "idVendedor";
            static public final String idTema  = "idTema";
            static public final String PIN = "PIN";
            static public final String activarPIN = "activarPIN";

        }

    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idConfiguracion + " integer not null primary key autoincrement," +
                    column.name.idVendedor + " integer not null," +
                    column.name.idTema + " integer not null," +
                    column.name.PIN + " integer," +
                    column.name.activarPIN + " integer" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
