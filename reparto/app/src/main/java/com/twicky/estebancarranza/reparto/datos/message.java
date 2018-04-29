package com.twicky.estebancarranza.reparto.datos;



/**
 * Created by esteban.carranza on 29/04/2018.
 */

public class message {
    int id;
    String text;
    boolean me;


    public message(int id, String text, boolean me) {
        this.id = id;
        this.text = text;
        this.me = me;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getMe() {
        return me;
    }

    public void setMe(boolean me) {
        this.me = me;
    }


}
