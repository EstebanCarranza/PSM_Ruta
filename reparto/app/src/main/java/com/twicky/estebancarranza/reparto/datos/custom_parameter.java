package com.twicky.estebancarranza.reparto.datos;

/**
 * Created by esteban.carranza on 28/04/2018.
 */

import com.twicky.estebancarranza.reparto.estaticos.layout;

public class custom_parameter {

    public custom_parameter(layout tipoLayout) {
        this.tipoLayout = tipoLayout;
    }

    private layout tipoLayout;


    public custom_parameter() {
    }

    public layout getTipoLayout() {
        return tipoLayout;
    }

    public void setTipoLayout(layout tipoLayout) {
        this.tipoLayout = tipoLayout;
    }
}
