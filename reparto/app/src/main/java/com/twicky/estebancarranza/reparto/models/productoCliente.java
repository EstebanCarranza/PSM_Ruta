package com.twicky.estebancarranza.reparto.models;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class productoCliente {
    private int idProductoCliente;
    private int idProducto;
    private int idCliente;
    private float cantidad;

    public productoCliente() {
    }

    public productoCliente(int idProductoCliente, int idProducto, int idCliente, float cantidad) {
        this.idProductoCliente = idProductoCliente;
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.cantidad = cantidad;
    }

    public int getIdProductoCliente() {
        return idProductoCliente;
    }

    public void setIdProductoCliente(int idProductoCliente) {
        this.idProductoCliente = idProductoCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
}
