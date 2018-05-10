package com.twicky.estebancarranza.reparto.models;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class rutaCliente {
    private int idRutaCliente;
    private int idRuta;
    private int idCliente;
    private int idProducto;
    private float cantidad;

    public rutaCliente() {
    }

    public rutaCliente(int idRutaCliente, int idRuta, int idCliente, int idProducto, float cantidad) {
        this.idRutaCliente = idRutaCliente;
        this.idRuta = idRuta;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdRutaCliente() {
        return idRutaCliente;
    }

    public void setIdRutaCliente(int idRutaCliente) {
        this.idRutaCliente = idRutaCliente;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
}
