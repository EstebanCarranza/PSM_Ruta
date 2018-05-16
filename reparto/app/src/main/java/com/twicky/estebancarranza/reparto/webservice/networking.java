package com.twicky.estebancarranza.reparto.webservice;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by esteban.carranza on 15/05/2018.
 */

public class networking extends AsyncTask<Object, Integer, Object> {
Context m_context;
private  NetCallback mListenerNetCall;
    public networking(Context m_context,NetCallback mListenerNetCall) {
        this.m_context = m_context;
        this.mListenerNetCall = mListenerNetCall;
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
            getAddressWithLatLng getAd = new getAddressWithLatLng();

            String address = getAd.getAddress((LatLng)objects[1]);
            if(mListenerNetCall != null)
            {
               // NetCallback netCallback = (NetCallback) objects[0];
                mListenerNetCall.onWorkFinish(address);
            }
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
