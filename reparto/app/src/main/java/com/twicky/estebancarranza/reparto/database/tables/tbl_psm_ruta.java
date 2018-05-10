package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_ruta {
    static public final String name = "tbl_psm_ruta";
    public class column
    {
        public class name
        {
            static public final String idRuta           = "idRuta";
            static public final String rutaIniciada     = "rutaIniciada";
            static public final String rutaConfirmada   = "rutaConfirmada";
            static public final String titulo           = "titulo";
        }

    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idRuta + " integer not null primary key autoincrement," +
                    column.name.rutaIniciada + " integer," +
                    column.name.rutaConfirmada + " integer," +
                    column.name.titulo + " text" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
