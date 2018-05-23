package com.twicky.estebancarranza.reparto.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.twicky.estebancarranza.reparto.models.vendedor;

/**
 * Created by esteban.carranza on 22/05/2018.
 */

public class login {

    private static final String LOGIN_AUTO = "LoginAuto";
    private static final String CORREO = "Correo";
    private static final String CONTRASENIA = "Contrasenia";
    public static void setLoginAuto(Context context, boolean value, String correo, String contrasenia)
    {

        SharedPreferences settings = context.getSharedPreferences(PREFF_NAME.PREFF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(LOGIN_AUTO, value);
        editor.putString(CORREO, correo);
        editor.putString(CONTRASENIA, contrasenia);
        editor.commit();
    }
    public static vendedor getLoginAuto(Context context)
    {
        vendedor vendedor = null;
        SharedPreferences settings = context.getSharedPreferences(PREFF_NAME.PREFF_NAME, 0);
        boolean loginAuto = settings.getBoolean(LOGIN_AUTO, false);

        if(loginAuto)
        {
            vendedor = new vendedor();
            vendedor.setCorreo(settings.getString(CORREO, ""));
            vendedor.setContrasenia(settings.getString(CONTRASENIA, ""));
        }

        return vendedor;
    }
}
