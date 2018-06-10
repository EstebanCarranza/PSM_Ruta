package com.twicky.estebancarranza.reparto.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.twicky.estebancarranza.reparto.models.vendedor;

/**
 * Created by esteban.carranza on 24/05/2018.
 */

public class host {
    static final String HOST_TO_CONNECT = "HOST_TO_CONNECT";

    public static final void setHost(Context context, String IP_HOST)
    {
        SharedPreferences settings = context.getSharedPreferences(PREFF_NAME.HOST_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(HOST_TO_CONNECT, IP_HOST);
        editor.commit();
    }
    public static final String getHost(Context context)
    {
        String IP_HOST = "";
        SharedPreferences settings = context.getSharedPreferences(PREFF_NAME.HOST_NAME, 0);

        IP_HOST = settings.getString(HOST_TO_CONNECT, "");

        return IP_HOST;
    }
}
