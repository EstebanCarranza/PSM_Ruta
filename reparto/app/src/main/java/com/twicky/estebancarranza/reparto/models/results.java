package com.twicky.estebancarranza.reparto.models;

/**
 * Created by esteban.carranza on 19/05/2018.
 */

public class results {
    private String result;
    private String detail;

    public results(String result, String detail) {
        this.result = result;
        this.detail = detail;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
