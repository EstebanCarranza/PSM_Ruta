package com.twicky.estebancarranza.reparto.database.tables;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class tbl_psm_vendedor {

        static public final String name = "tbl_psm_vendedor";
        public class column
        {
            public class name
            {
                static public final String idVendedor   = "idVendedor";
                static public final String correo       = "correo";
                static public final String contrasenia  = "contrasenia";
                static public final String nombres      = "nombres";
                static public final String appat        = "appat";
                static public final String apmat        = "apmat";
                static public final String fechNac      = "fechNac";
                static public final String loginActive  = "loginActive";
            }

        }
        static public final String create =
                "create table " + name +
                        "(" +
                        column.name.idVendedor + " integer not null primary key autoincrement," +
                        column.name.correo + " text," +
                        column.name.contrasenia + " text," +
                        column.name.nombres + " text," +
                        column.name.appat + " text," +
                        column.name.apmat + " text," +
                        column.name.fechNac + " text," +
                        column.name.loginActive + " text" +
                        "); ";
        static public final String drop = "drop table " + name + "; ";

}
