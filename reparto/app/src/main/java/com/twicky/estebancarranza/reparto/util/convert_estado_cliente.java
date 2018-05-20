package com.twicky.estebancarranza.reparto.util;

import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import static com.twicky.estebancarranza.reparto.estaticos.estado_cliente.confirmado;
import static com.twicky.estebancarranza.reparto.estaticos.estado_cliente.sinAsignar;
import static com.twicky.estebancarranza.reparto.estaticos.estado_cliente.sinConfirmar;

/**
 * Created by esteban.carranza on 19/05/2018.
 */

public class convert_estado_cliente {
    public static int toInt(estado_cliente estado_cliente)
    {
        switch (estado_cliente)
        {
            case confirmado: return 0;
            case sinAsignar: return 1;
            case sinConfirmar: return 2;
        }
        return -1;
    }
    public static estado_cliente toEstadoCliente(String estado_cliente)
    {
        switch (estado_cliente)
        {
            case "confirmado": return confirmado;
            case "sinAsignar": return sinAsignar;
            case "sinConfirmar": return sinConfirmar;
        }
        return null;
    }
}
