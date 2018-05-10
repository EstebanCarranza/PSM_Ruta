package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_producto {
    static public final String name = "tbl_psm_producto";
    public class column
    {
        public class name
        {
            static public final String idProducto   = "idProducto";
            static public final String titulo       = "titulo";
            static public final String descripcion  = "descripcion";
            static public final String precioNormal = "precioNormal";
        }

    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idProducto + " integer not null primary key autoincrement," +
                    column.name.titulo + " text," +
                    column.name.descripcion + " text," +
                    column.name.precioNormal + " real" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
