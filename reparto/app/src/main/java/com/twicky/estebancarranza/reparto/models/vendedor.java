package com.twicky.estebancarranza.reparto.models;

/**
 * Created by esteban.carranza on 10/05/2018.
 */

public class vendedor {
    private int idVendedor;
    private String correo;
    private String contrasenia;
    private String nombres;
    private String appat;
    private String apmat;
    private String fechNac;

    public int getLoginActive() {
        return LoginActive;
    }

    public void setLoginActive(int loginActive) {
        LoginActive = loginActive;
    }

    private int LoginActive;



    public vendedor() {
    }

    public vendedor(int idVendedor, String correo, String contrasenia, String nombres, String appat, String apmat, String fechNac) {
        this.idVendedor = idVendedor;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.nombres = nombres;
        this.appat = appat;
        this.apmat = apmat;
        this.fechNac = fechNac;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAppat() {
        return appat;
    }

    public void setAppat(String appat) {
        this.appat = appat;
    }

    public String getApmat() {
        return apmat;
    }

    public void setApmat(String apmat) {
        this.apmat = apmat;
    }

    public String getFechNac() {
        return fechNac;
    }

    public void setFechNac(String fechNac) {
        this.fechNac = fechNac;
    }
}
