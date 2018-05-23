package com.twicky.estebancarranza.reparto.estaticos;

import com.twicky.estebancarranza.reparto.models.vendedor;

/**
 * Created by esteban.carranza on 22/05/2018.
 */

public class global {

    public static vendedor getVendedor() {
        return vendedor;
    }

    public static void setVendedor(vendedor vendedor) {
        global.vendedor = vendedor;
    }

    static vendedor vendedor;



}
