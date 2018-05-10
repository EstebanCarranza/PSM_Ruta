package com.twicky.estebancarranza.reparto.models;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class ruta {
    private int idRuta;
    private int rutaIniciada;
    private int rutaConfirmada;
    private String titulo;

    public ruta() {
    }

    public ruta(int idRuta, int rutaIniciada, int rutaConfirmada, String titulo) {
        this.idRuta = idRuta;
        this.rutaIniciada = rutaIniciada;
        this.rutaConfirmada = rutaConfirmada;
        this.titulo = titulo;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getRutaIniciada() {
        return rutaIniciada;
    }

    public void setRutaIniciada(int rutaIniciada) {
        this.rutaIniciada = rutaIniciada;
    }

    public int getRutaConfirmada() {
        return rutaConfirmada;
    }

    public void setRutaConfirmada(int rutaConfirmada) {
        this.rutaConfirmada = rutaConfirmada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
