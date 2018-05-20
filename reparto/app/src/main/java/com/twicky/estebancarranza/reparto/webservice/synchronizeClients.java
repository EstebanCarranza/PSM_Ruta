package com.twicky.estebancarranza.reparto.webservice;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.database.tables.tbl_psm_cliente;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.models.results;
import com.twicky.estebancarranza.reparto.util.convert_estado_cliente;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.webservice.networking.inputStreamToString;
import static com.twicky.estebancarranza.reparto.webservice.staticData.SERRVER_PATH_SYNCHRONIZE_CLIENTS;
import static com.twicky.estebancarranza.reparto.webservice.staticData.SERVER_PATH_INSERT_SYNCHRONIZE_CLIENTS;
import static com.twicky.estebancarranza.reparto.webservice.staticData.TIMEOUT;

/**
 * Created by esteban.carranza on 15/05/2018.
 */

public class synchronizeClients {

    public static ArrayList<cliente> insertSynchronizeClient() {

        ArrayList<cliente> results = new ArrayList<>();

        String postParams = "";
        String response = "";
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(SERVER_PATH_INSERT_SYNCHRONIZE_CLIENTS);
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
                JSONArray jsonArray = new JSONArray(jsonResponse);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);

                    //int id, String rfc, String nombre, int idRegimenFiscal, String domicilio,
                    // String telefono, LatLng coordenada, estado_cliente estadoActual, String fechaUltimaModificacion)
                    LatLng coordenada = new LatLng
                            (
                                Double.parseDouble(object.getString(tbl_psm_cliente.column.name.latitude)),
                                Double.parseDouble(object.getString(tbl_psm_cliente.column.name.longitude))
                            );
                    results.add(new cliente
                            (
                                object.getInt(tbl_psm_cliente.column.name.idCliente),
                                object.getString(tbl_psm_cliente.column.name.rfc),
                                object.getString(tbl_psm_cliente.column.name.razonSocial),
                                object.getInt(tbl_psm_cliente.column.name.idRegimenFiscal),
                                object.getString(tbl_psm_cliente.column.name.direccion),
                                object.getString(tbl_psm_cliente.column.name.telefono),
                                coordenada,
                                convert_estado_cliente.toEstadoCliente(object.getString(tbl_psm_cliente.column.name.estadoCliente)),
                                object.getString(tbl_psm_cliente.column.name.fechaUltimaModificacion)
                            ));
                }


               return results;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
    public static ArrayList<results> updateSynchronizeClient(cliente cliente) {

        ArrayList<results> results = new ArrayList<>();
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
                "&estadoCliente=" + confirmacion +
                "&fechaUltimaModificacion=" + cliente.getFechaUltimaModificacion()

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


                results.add(0, new results("result", jsonObject.optString("result")));
                results.add(1, new results("detail", jsonObject.optString("detail")));
                results.add(2, new results("totalClients", jsonObject.optString("totalClients")));
                results.add(3, new results("fechaUltimaModificacion", jsonObject.optString("fechaUltimaModificacion")));
                results.add(4, new results(tbl_psm_cliente.column.name.rfc, jsonObject.optString(tbl_psm_cliente.column.name.rfc)));
                results.add(5, new results(tbl_psm_cliente.column.name.razonSocial, jsonObject.optString(tbl_psm_cliente.column.name.razonSocial)));
                results.add(6, new results(tbl_psm_cliente.column.name.idRegimenFiscal, jsonObject.optString(tbl_psm_cliente.column.name.idRegimenFiscal)));
                results.add(7, new results(tbl_psm_cliente.column.name.direccion, jsonObject.optString(tbl_psm_cliente.column.name.direccion)));
                results.add(8, new results(tbl_psm_cliente.column.name.telefono, jsonObject.optString(tbl_psm_cliente.column.name.telefono)));
                results.add(9, new results(tbl_psm_cliente.column.name.latitude, jsonObject.optString(tbl_psm_cliente.column.name.latitude)));
                results.add(10, new results(tbl_psm_cliente.column.name.longitude, jsonObject.optString(tbl_psm_cliente.column.name.longitude)));
                results.add(11, new results(tbl_psm_cliente.column.name.estadoCliente, jsonObject.optString(tbl_psm_cliente.column.name.estadoCliente)));



                return results;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
