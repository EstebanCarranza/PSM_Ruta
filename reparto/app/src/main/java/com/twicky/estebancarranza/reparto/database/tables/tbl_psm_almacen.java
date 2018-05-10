package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_almacen {
    static public final String name = "tbl_psm_almacen";
    public class column
    {
        public class name
        {
            static public final String idAlmacen   = "idAlmacen";
            static public final String titulo       = "titulo";
            static public final String direccion  = "direccion";
            static public final String coordenada = "coordenada";

        }

    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idAlmacen + " integer not null primary key autoincrement," +
                    column.name.titulo + " text," +
                    column.name.direccion + " text," +
                    column.name.coordenada + " text" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
