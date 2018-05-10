package com.twicky.estebancarranza.reparto.models;

import com.twicky.estebancarranza.reparto.R;


/**
 * Created by esteban.carranza on 12/03/2018.
 */

public class producto {

    private int idProducto;
    private String titulo;
    private String descripcion;
    private float precioNormal;
    custom_color colorDefault;
    custom_color colorConfirmado;
    custom_color colorNoConfirmado;

    public producto() {
    }

    public producto(int idProducto, String titulo, String descripcion, float precioNormal, custom_color colorConfirmado, custom_color colorNoConfirmado) {
        this.idProducto = idProducto;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precioNormal = precioNormal;

    }


    public producto(float total, String titulo, String descripcion, custom_color colorConfirmado, custom_color colorNoConfirmado) {
        this.precioNormal = total;
        this.titulo = titulo;
        this.descripcion = descripcion;

        this.colorConfirmado = colorConfirmado;
        this.colorNoConfirmado = colorNoConfirmado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }


    public float getPrecioNormal() {
        return precioNormal;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setPrecioNormal(float total) {
        this.precioNormal = total;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public custom_color getColorConfirmado() {
        return colorConfirmado;
    }

    public void setColorConfirmado(custom_color colorConfirmado) {
        this.colorConfirmado = colorConfirmado;
    }

    public custom_color getColorNoConfirmado() {
        return colorNoConfirmado;
    }

    public void setColorNoConfirmado(custom_color colorNoConfirmado) {
        this.colorNoConfirmado = colorNoConfirmado;
    }

    public custom_color getColorDefault() {
        return colorDefault;
    }

    public void setColorDefault(custom_color colorDefault) {
        this.colorDefault = colorDefault;
    }
}