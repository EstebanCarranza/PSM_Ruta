package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_rutaCliente {
    static public final String name = "tbl_psm_rutaCliente";
    public class column
    {
        public class name
        {
            static public final String idRutaCliente   = "idRutaCliente";
            static public final String idRuta       = "idRuta";
            static public final String idCliente  = "idCliente";
            static public final String idProducto = "idProducto";
            static public final String cantidad = "cantidad";
        }
    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idRutaCliente + " integer not null primary key autoincrement," +
                    column.name.idRuta + " integer," +
                    column.name.idCliente + " integer," +
                    column.name.idProducto + " integer," +
                    column.name.cantidad + " real" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
