package com.twicky.estebancarranza.reparto.webservice;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.twicky.estebancarranza.reparto.util.JSONConverts;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.twicky.estebancarranza.reparto.webservice.getAddressWithLatLng.getAddress;
import static com.twicky.estebancarranza.reparto.webservice.staticData.SERVER_PATH;
import static com.twicky.estebancarranza.reparto.webservice.staticData.TIMEOUT;

/**
 * Created by esteban.carranza on 15/05/2018.
 */

public class networking extends AsyncTask<Object, Integer, Object> {
Context m_context;

    public networking(Context m_context) {
        this.m_context = m_context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /*m_progressDialog = new ProgressDialog(m_context);
        m_progressDialog.setTitle("Conectando");
        m_progressDialog.setMessage("Espere...");
        m_progressDialog.setCancelable(false);
        m_progressDialog.show();;*/
    }

    @Override
    protected Object doInBackground(Object... objects) {
        String action = (String) objects[0];
        if(action.equals("getAddress")) {
            // Llamamos a nuestro callback
            String address = getAddress();
            NetCallback netCallback = (NetCallback) objects[1];
            netCallback.onWorkFinish(address);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //m_progressDialog.dismiss();
    }

    // Metodo que lee un String desde un InputStream (Convertimos el InputStream del servidor en un String)
    public static String inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder response = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            while((rLine = rd.readLine()) != null)
            {
                response.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

}
