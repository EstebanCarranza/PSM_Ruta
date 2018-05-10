package com.twicky.estebancarranza.reparto.models;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class almacen {
    private int idAlmacen;
    private String titulo;
    private String direccion;
    private String coordenada;

    public almacen() {
    }

    public almacen(int idAlmacen, String titulo, String direccion, String coordenada) {
        this.idAlmacen = idAlmacen;
        this.titulo = titulo;
        this.direccion = direccion;
        this.coordenada = coordenada;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }
}
