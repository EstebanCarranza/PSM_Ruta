package com.twicky.estebancarranza.reparto.webservice;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.models.vendedor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.twicky.estebancarranza.reparto.util.encrypt.md5;
import static com.twicky.estebancarranza.reparto.webservice.networking.inputStreamToString;
import static com.twicky.estebancarranza.reparto.webservice.staticData.SERVER_PATH_LOGIN;
import static com.twicky.estebancarranza.reparto.webservice.staticData.TIMEOUT;

/**
 * Created by esteban.carranza on 15/05/2018.
 */

public class logIn {
    public static vendedor validateLogin(String correo, String contrasenia) {
        String login = "";
        String postParams = "&correo=" + correo + "&contrasenia=" + md5(contrasenia);
        String response = "";
        vendedor vendedor = null;
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(SERVER_PATH_LOGIN);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(TIMEOUT);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setFixedLengthStreamingMode(postParams.getBytes().length);

            OutputStream out = new BufferedOutputStream(conn.getOutputStream());
            out.write(postParams.getBytes());
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();
            Log.w("RESPONSE CODE", "" + responseCode);

            InputStream in = new BufferedInputStream(conn.getInputStream());
            String jsonResponse = inputStreamToString(in);


            try {

                // Convertimos nuestro JSON String a un objeto para extraer sus datos
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject(jsonResponse);

                if(jsonObject.getString("result").equals("OK")) {
                    vendedor = new vendedor();
                    vendedor.setIdVendedor(jsonObject.optInt("idVendedor1"));
                    vendedor.setCorreo(jsonObject.getString("correo"));
                    vendedor.setNombres(jsonObject.getString("nombres"));
                    vendedor.setAppat(jsonObject.getString("appat"));
                    vendedor.setApmat(jsonObject.getString("apmat"));
                    vendedor.setFechNac(jsonObject.getString("fechNac"));
                    vendedor.setContrasenia(jsonObject.getString("contrasenia"));


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vendedor;
    }


}
