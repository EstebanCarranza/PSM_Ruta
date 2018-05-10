package com.twicky.estebancarranza.reparto.models;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class rutaDetalle {
    private int idRutaDetalle;
    private int idRuta;
    private int idVendedor;
    private int idProductoCliente;

    public rutaDetalle() {
    }

    public rutaDetalle(int idRutaDetalle, int idRuta, int idVendedor, int idProductoCliente) {
        this.idRutaDetalle = idRutaDetalle;
        this.idRuta = idRuta;
        this.idVendedor = idVendedor;
        this.idProductoCliente = idProductoCliente;
    }

    public int getIdRutaDetalle() {
        return idRutaDetalle;
    }

    public void setIdRutaDetalle(int idRutaDetalle) {
        this.idRutaDetalle = idRutaDetalle;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdProductoCliente() {
        return idProductoCliente;
    }

    public void setIdProductoCliente(int idProductoCliente) {
        this.idProductoCliente = idProductoCliente;
    }
}
