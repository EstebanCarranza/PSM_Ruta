package com.twicky.estebancarranza.reparto.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.models.cliente;
import com.twicky.estebancarranza.reparto.webservice.NetCallback;
import com.twicky.estebancarranza.reparto.webservice.networking;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 19/05/2018.
 */

public class synchronizeClientsInBackground extends Service{

    private Context thisContext = this;
    private int vida = 0;
    private boolean cont = false;
    @Override
    public void onCreate()
    {//Crear el servicio

    }
    private void contar()
    {
        Toast.makeText(thisContext, "Contando...", Toast.LENGTH_SHORT).show();

        vida++;

    }
    MediaPlayer mediaPlayer;

    boolean search = true;
    int total = 0;
    private void sincronizar(final Context context)
    {

            new networking(context, new NetCallback() {

                @Override
                public void onWorkFinish(Object data) {
                    //final ArrayList<results> results = (ArrayList<results>) data;
                    final ArrayList<cliente> clientes = (ArrayList<cliente>) data;

                    clienteSQL db = new clienteSQL(getApplicationContext());
                    db.insertIfNotExists(clientes);
                    Toast.makeText(context, "Clientes sincronizados correctamente", Toast.LENGTH_SHORT).show();

                }
            }).execute("getSynchronizeClient");


            clienteSQL db = new clienteSQL(getApplicationContext());
            final ArrayList<cliente> clientes = db.getCliente(0, 0);
            int i = 0;

            new networking(context, new NetCallback() {

                @Override
                public void onWorkFinish(Object data) {
                    //final ArrayList<results> results = (ArrayList<results>) data;
                    final String results = (String) data;

                    Toast.makeText(context, results, Toast.LENGTH_SHORT).show();

                }
            }).execute("SynchronizeClient", clientes);


    }

    @Override
    public int onStartCommand(Intent intent, int flag, int idProcess)
    {//Aqui es donde empieza cuando se lanza el servicio

        Toast.makeText(thisContext, "El servicio está corriendo", Toast.LENGTH_SHORT).show();
        /*cont = true;
        contar();*/

         mediaPlayer = MediaPlayer.create(thisContext, R.raw.flyaway);
         mediaPlayer.start();

       // sincronizar(thisContext);


        return START_STICKY; //identifica el proceso que se inicia
    }
    @Override
    public void onDestroy()
    {//Destruir el servicio
        //cont = false;
        Toast.makeText(thisContext, "El servicio fue detenido", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
        search = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
