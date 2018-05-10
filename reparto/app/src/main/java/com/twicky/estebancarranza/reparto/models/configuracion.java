package com.twicky.estebancarranza.reparto.models;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class configuracion {
    private int idConfiguracion;
    private int idVendedor;
    private int idTema;
    private int PIN;
    private int activarPIN;

    public configuracion() {
    }

    public configuracion(int idConfiguracion, int idVendedor, int idTema, int PIN, int activarPIN) {
        this.idConfiguracion = idConfiguracion;
        this.idVendedor = idVendedor;
        this.idTema = idTema;
        this.PIN = PIN;
        this.activarPIN = activarPIN;
    }

    public int getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(int idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public int getActivarPIN() {
        return activarPIN;
    }

    public void setActivarPIN(int activarPIN) {
        this.activarPIN = activarPIN;
    }
}
