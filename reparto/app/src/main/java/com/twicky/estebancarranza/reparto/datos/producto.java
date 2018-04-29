package com.twicky.estebancarranza.reparto.datos;

import com.twicky.estebancarranza.reparto.R;


/**
 * Created by esteban.carranza on 12/03/2018.
 */

public class producto {

    private float total;
    private String titulo;
    private String descripcion;


    private int color;

    public producto() {
    }

    public producto(float total, String titulo, String descripcion) {
        this.total = total;
        this.titulo = titulo;
        this.descripcion = descripcion;

        this.color = R.color.background_color;
    }


    public float getTotal() {
        return total;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}