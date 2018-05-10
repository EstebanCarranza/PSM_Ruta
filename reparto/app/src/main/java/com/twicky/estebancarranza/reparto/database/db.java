package com.twicky.estebancarranza.reparto.database;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_almacen;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_cliente;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_configuracion;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_mensaje;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_producto;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_productoCliente;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_ruta;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_rutaCliente;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_rutaDetalle;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_vendedor;



/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class db {

    static public final String name = "db_psm";

    public class create {

        static public final String db_psm =
                tbl_psm_almacen.create +
                tbl_psm_cliente.create +
                tbl_psm_configuracion.create +
                tbl_psm_mensaje.create +
                tbl_psm_producto.create +
                tbl_psm_productoCliente.create +
                tbl_psm_ruta.create +
                tbl_psm_rutaCliente.create +
                tbl_psm_rutaDetalle.create +
                tbl_psm_vendedor.create
                ;
    }
    public class drop
    {
        static public final String db_psm =
                tbl_psm_almacen.create +
                tbl_psm_cliente.drop +
                tbl_psm_configuracion.drop +
                tbl_psm_mensaje.drop +
                tbl_psm_producto.drop +
                tbl_psm_productoCliente.drop +
                tbl_psm_ruta.drop +
                tbl_psm_rutaCliente.drop +
                tbl_psm_rutaDetalle.drop +
                tbl_psm_vendedor.drop
                ;
    }
}
