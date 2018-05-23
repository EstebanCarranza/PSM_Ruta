package com.twicky.estebancarranza.reparto.sensores;

import android.content.Context;
import android.os.AsyncTask;

import com.twicky.estebancarranza.reparto.webservice.NetCallback;

/**
 * Created by esteban.carranza on 22/05/2018.
 */

public class luzTask extends AsyncTask<Object, Integer, Object>  {

    Context m_context;
    private NetCallback mListenerNetCall;

    public luzTask(Context applicationContext, NetCallback netCallback) {
        this.m_context = m_context;
        this.mListenerNetCall = mListenerNetCall;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    @Override
    protected Object doInBackground(Object... objects) {

        String action = (String) objects[0];
        if(action.equals("updateNumber")) {
            String address=  "";
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
}
