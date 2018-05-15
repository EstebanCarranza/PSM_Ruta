package com.twicky.estebancarranza.reparto.webservice;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.twicky.estebancarranza.reparto.webservice.networking.inputStreamToString;
import static com.twicky.estebancarranza.reparto.webservice.staticData.SERVER_PATH;
import static com.twicky.estebancarranza.reparto.webservice.staticData.TIMEOUT;

/**
 * Created by esteban.carranza on 15/05/2018.
 */

public class synchronizeClients {

    public String synchronize() {
        String address = "";
        String postParams = "";
        String response = "";
        HttpURLConnection conn = null;
        URL url = null;
        try {
            url = new URL(SERVER_PATH);
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

                /*
                JSONObject jsonObject = new JSONObject(jsonResponse);
                Object jsonResults = jsonObject.get("results");



                //JSONArray jsonResultArray = JSONConverts.objectToJSONArray(jsonResults);


                  JSONObject jsonResults1 = JSONConverts.objectToJSONObject(jsonResults);
                  //jsonResults1 = jsonResultArray.getJSONObject(0);


*/
                int data = 0;
                //  for (int i = 0; i < jsonArray.length(); i++) {
                //JSONObject object = jsonArray.get("formatted_address");
                //address = object.getString("formatted_address");

                //imagesList.add(new MyImage(name, path));
                // }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}
