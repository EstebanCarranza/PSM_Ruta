package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_mensaje {
    static public final String name = "tbl_psm_mensaje";
    public class column
    {
        public class name
        {
            static public final String idMensaje    = "idMensaje";
            static public final String idRuta       = "idRuta";
            static public final String idVendedor1 = "idVendedor1";
            static public final String idVendedor2 = "idVendedor2";
            static public final String mensaje      = "mensaje";
            static public final String fechaEnvio   = "fechaEnvio";
            static public final String me = "me";
        }
    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idMensaje + " integer not null primary key autoincrement," +
                    column.name.idRuta + " integer," +
                    column.name.idVendedor1 + " integer," +
                    column.name.idVendedor2 + " integer," +
                    column.name.mensaje + " text," +
                    column.name.fechaEnvio + " text" +
                    column.name.me + " int" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
