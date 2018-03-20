package com.twicky.estebancarranza.reparto.datos;

import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

/**
 * Created by esteban.carranza on 10/03/2018.
 */

public class cliente {
    private int id;
    private String nombre;
    private String domicilio;

    public estado_cliente getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(estado_cliente estadoActual) {
        this.estadoActual = estadoActual;
    }

    private estado_cliente estadoActual;

    public cliente() {
    }
    public cliente(String nombre, String domicilio, estado_cliente estadoActual) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.estadoActual = estadoActual;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}
