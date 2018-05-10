package com.twicky.estebancarranza.reparto.models;



/**
 * Created by esteban.carranza on 29/04/2018.
 */

public class mensaje {
    int idMensaje;
    String mensaje;
    int idRuta;
    int idVendedor;
    int idAlmacen;
    String fechaEnvio;
    boolean me;

    public mensaje() {
    }

    public mensaje(int idMensaje, String text, boolean me) {
        this.idMensaje = idMensaje;
        this.mensaje = text;
        this.me = me;

    }

    public mensaje(int idMensaje, String mensaje, int idRuta, int idVendedor, int idAlmacen, String fechaEnvio, boolean me) {
        this.idMensaje = idMensaje;
        this.mensaje = mensaje;
        this.idRuta = idRuta;
        this.idVendedor = idVendedor;
        this.idAlmacen = idAlmacen;
        this.fechaEnvio = fechaEnvio;
        this.me = me;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public boolean isMe() {
        return me;
    }

    public int getidMensaje() {
        return idMensaje;
    }

    public void setidMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getText() {
        return mensaje;
    }

    public void setText(String text) {
        this.mensaje = text;
    }

    public boolean getMe() {
        return me;
    }

    public void setMe(boolean me) {
        this.me = me;
    }


}
