package com.twicky.estebancarranza.reparto.webservice;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.twicky.estebancarranza.reparto.webservice.networking.inputStreamToString;
import static com.twicky.estebancarranza.reparto.webservice.staticData.SERVER_PATH_GET_ADDRESS;
import static com.twicky.estebancarranza.reparto.webservice.staticData.TIMEOUT;

/**
 * Created by esteban.carranza on 15/05/2018.
 */

public class getAddressWithLatLng {

    public static String getAddress(LatLng latlng) {
        String address = "";
        String postParams = "&latitude=" + latlng.latitude + "&longitude=" + latlng.longitude;
        String response = "";
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(SERVER_PATH_GET_ADDRESS);
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
                return jsonObject.optString("address");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}