package com.twicky.estebancarranza.reparto.estaticos;

/**
 * Created by esteban.carranza on 28/04/2018.
 */

public enum layout {
    producto_por_cliente,
    producto_sin_cliente,
    producto_vista,
    cliente_lista_confirmar,
    cliente_lista_CRU,
    producto_por_entregar
    ;

    public static layout fromInteger(int x) {
        switch(x) {
            case 0:
                return producto_por_cliente;
            case 1:
                return producto_sin_cliente;

            case 2:
                return producto_vista;
            case 3:
                return cliente_lista_confirmar;
            case 4:
                return cliente_lista_CRU;

            case 5: producto_por_entregar:
                return producto_por_entregar;

        }
        return null;
    }

    public static layout fromString(String x) {
        switch(x) {
            case "producto_por_cliente":
                return producto_por_cliente;
            case "producto_sin_cliente":
                return producto_sin_cliente;

            case "producto_vista":
                return producto_vista;

            case "cliente_lista_confirmar":
                return cliente_lista_confirmar;

            case "cliente_lista_CRU":
                return cliente_lista_CRU;

            case "producto_por_entregar":
                return producto_por_entregar;
        }
        return null;
    }
}
