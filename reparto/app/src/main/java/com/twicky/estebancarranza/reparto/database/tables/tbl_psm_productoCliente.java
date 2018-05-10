package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_productoCliente {
    static public final String name = "tbl_psm_productoCliente";
    public class column
    {
        public class name
        {
            static public final String idProductoCliente   = "idProductoCliente";
            static public final String idProducto       = "idProducto";
            static public final String idCliente  = "idCliente";
            static public final String cantidad = "cantidad";
        }
    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idProductoCliente + " integer not null primary key autoincrement," +
                    column.name.idProducto + " integer," +
                    column.name.idCliente + " integer," +
                    column.name.cantidad + " real" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
