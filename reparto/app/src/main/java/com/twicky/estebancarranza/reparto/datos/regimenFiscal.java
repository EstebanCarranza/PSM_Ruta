package com.twicky.estebancarranza.reparto.datos;

/**
 * Created by esteban.carranza on 29/04/2018.
 */

public class regimenFiscal {

    private int id;
    private String regimenFiscal;

    public regimenFiscal(int id, String regimenFiscal) {
        this.id = id;
        this.regimenFiscal = regimenFiscal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegimenFiscal() {
        return regimenFiscal;
    }

    public void setRegimenFiscal(String regimenFiscal) {
        this.regimenFiscal = regimenFiscal;
    }
}
