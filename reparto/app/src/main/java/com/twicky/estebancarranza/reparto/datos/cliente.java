package com.twicky.estebancarranza.reparto.datos;

import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

/**
 * Created by esteban.carranza on 10/03/2018.
 */

public class cliente {
    private int id;
    private String rfc;
    private String nombre;
    private int idRegimenFiscal;
    private String domicilio;
    private String telefono;
    private String coordenada;
    private estado_cliente estadoActual;

    public estado_cliente getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(estado_cliente estadoActual) {
        this.estadoActual = estadoActual;
    }



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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public int getIdRegimenFiscal() {
        return idRegimenFiscal;
    }

    public void setIdRegimenFiscal(int idRegimenFiscal) {
        this.idRegimenFiscal = idRegimenFiscal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }
}
