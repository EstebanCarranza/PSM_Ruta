package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_rutaDetalle {
    static public final String name = "tbl_psm_rutaDetalle";
    public class column
    {
        public class name
        {
            static public final String idRutaDetalle   = "idRutaDetalle";
            static public final String idRuta       = "idRuta";
            static public final String idVendedor  = "idVendedor";
            static public final String idProductoCliente = "idProductoCliente";
        }
    }
    static public final String create =
            "create table " + name +
                    "(" +
                    column.name.idRutaDetalle + " integer not null primary key autoincrement," +
                    column.name.idRuta + " integer," +
                    column.name.idVendedor + " integer," +
                    column.name.idProductoCliente + " integer" +
                    "); ";
    static public final String drop = "drop table " + name + "; ";
}
