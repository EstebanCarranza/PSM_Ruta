package com.twicky.estebancarranza.reparto.webservice;

import android.util.Log;

import com.twicky.estebancarranza.reparto.models.cliente;

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
import static com.twicky.estebancarranza.reparto.webservice.staticData.SERRVER_PATH_SYNCHRONIZE_CLIENTS;
import static com.twicky.estebancarranza.reparto.webservice.staticData.TIMEOUT;

/**
 * Created by esteban.carranza on 15/05/2018.
 */

public class synchronizeClients {

    public static String SynchronizeClient(cliente cliente) {

        int confirmacion = -1;
        switch(cliente.getEstadoActual())
        {
            case confirmado:
               confirmacion = 0;
            break;
            case sinAsignar:
                confirmacion = 1;
                break;
            case sinConfirmar:
                confirmacion = 2;
                break;
            default: confirmacion = -1; break;
        }

        String postParams =
                "&rfc=" + cliente.getRfc() +
                "&razonSocial=" + cliente.getNombre() +
                "&idRegimenFiscal=" + cliente.getIdRegimenFiscal() +
                "&direccion=" + cliente.getDomicilio() +
                "&telefono=" + cliente.getTelefono() +
                "&latitude=" + cliente.getCoordenada().latitude +
                "&longitude=" + cliente.getCoordenada().longitude +
                "&estadoCliente=" + confirmacion
        ;
        String response = "";
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(SERRVER_PATH_SYNCHRONIZE_CLIENTS);
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
            String jsonResponse = "";
            jsonResponse = inputStreamToString(in);
            response = jsonResponse;
            try {

                // Convertimos nuestro JSON String a un objeto para extraer sus datos
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject(jsonResponse);
                return jsonObject.optString("result")  + jsonObject.optString("detail");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
