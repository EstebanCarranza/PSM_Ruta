package com.twicky.estebancarranza.reparto.webservice;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.models.results;
import com.twicky.estebancarranza.reparto.models.vendedor;
import com.twicky.estebancarranza.reparto.util.convert_estado_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.webservice.getAddressWithLatLng.getAddress;
import static com.twicky.estebancarranza.reparto.webservice.logIn.validateLogin;

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

            String address = getAddress((LatLng)objects[1]);
            if(mListenerNetCall != null)
            {
               // NetCallback netCallback = (NetCallback) objects[0];
                mListenerNetCall.onWorkFinish(address);
            }
        }else if(action.equals("RemoteLogin"))
        {
            vendedor login = validateLogin((String) objects[1], (String) objects[2]);
            if(mListenerNetCall != null)
            {
                // NetCallback netCallback = (NetCallback) objects[0];
                mListenerNetCall.onWorkFinish(login);
            }
        }else if(action.equals("SynchronizeClient"))
        {
            String result = "";
            ArrayList<ArrayList<results>> ServerTotalClients = new ArrayList<>();
            ArrayList<cliente> cliente = (ArrayList<cliente>) objects[1];
            int i = 1;
            int Total_OK_01_SCVMR = 0, Total_OK_01_SCVMV = 0, Total_OK_02_SCVMV = 0;

            ServerTotalClients.add(synchronizeClients.updateSynchronizeClient(cliente.get(0)));

            int TotalClients = Integer.parseInt(ServerTotalClients.get(0).get(2).getDetail());
            switch(ServerTotalClients.get(0).get(0).getDetail())
            {
                case "OK_01_SCVMR": Total_OK_01_SCVMR++; break;
                case "OK_01_SCVMV": Total_OK_01_SCVMV++; break;
                case "OK_02_SCVMV": Total_OK_02_SCVMV++; break;
                default:break;
            }

            while(i < cliente.size())
            {
                ServerTotalClients.add(synchronizeClients.updateSynchronizeClient(cliente.get(i)));
                switch(ServerTotalClients.get(i).get(0).getDetail())
                {
                    case "OK_01_SCVMR": Total_OK_01_SCVMR++; break;
                    case "OK_01_SCVMV": Total_OK_01_SCVMV++; break;
                    case "OK_02_SCVMV": Total_OK_02_SCVMV++; break;
                    default:break;
                }
                i++;
            }
            if(cliente.size() == TotalClients)
            {
                result = "Clientes sincronizados";
            }
            else {
                int diferencia = cliente.size() - Integer.parseInt(ServerTotalClients.get(0).get(2).getDetail());
                Double dif = Math.pow(diferencia, 2);
                dif = Math.sqrt(dif);
                result = "Faltan (" + dif + ") cliente(s) por sincronizar, " +
                        "| Se actualizaron en el servidor: (" + Total_OK_01_SCVMV + ") registros | " +
                        "| Se agregaron al servidor: (" + Total_OK_02_SCVMV + ") registros | " +
                        "| Archivos nuevos encontrados: (" + Total_OK_01_SCVMR + ") registros |" +
                        ")";

                ArrayList<cliente> ClientesSincronizados = new ArrayList<>();

                int s = 0;
                while (s < cliente.size())
                {
                    //int id, String rfc, String nombre, int idRegimenFiscal, String domicilio, String telefono,
                    // LatLng coordenada, estado_cliente estadoActual, String fechaUltimaModificacion)
                    if(ServerTotalClients.get(s).get(0).getDetail().equals("OK_01_SCVMR")) {
                        LatLng coordenada =
                            new LatLng
                                    (
                                            Double.parseDouble(ServerTotalClients.get(s).get(9).getDetail()),
                                            Double.parseDouble(ServerTotalClients.get(s).get(10).getDetail())
                                    );


                        clienteSQL db = new clienteSQL(m_context);
                        db.update(new cliente(
                                0,
                                ServerTotalClients.get(s).get(4).getDetail(),
                                ServerTotalClients.get(s).get(5).getDetail(),
                                Integer.parseInt(ServerTotalClients.get(s).get(6).getDetail()),
                                ServerTotalClients.get(s).get(7).getDetail(),
                                ServerTotalClients.get(s).get(8).getDetail(),
                                coordenada,
                                convert_estado_cliente.toEstadoCliente(ServerTotalClients.get(s).get(9).getDetail()),
                                ServerTotalClients.get(s).get(3).getDetail()
                        ));
                    }
                    s ++;
                }

            }
            if(mListenerNetCall != null)
            {
                mListenerNetCall.onWorkFinish(result);
            }
        }else if(action.equals("getSynchronizeClient"))
        {

            ArrayList<cliente> clienteSync = synchronizeClients.insertSynchronizeClient();

            if(mListenerNetCall != null) {
                mListenerNetCall.onWorkFinish(clienteSync);
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

    public void synchronize()
    {

    }

}
