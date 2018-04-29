package com.twicky.estebancarranza.reparto.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.datos.custom_color;
import com.twicky.estebancarranza.reparto.datos.message;

import java.util.ArrayList;


/**
 * Created by esteban.carranza on 29/04/2018.
 */

public class list_messages extends RecyclerView.Adapter<list_messages.ViewHolderDatos> implements View.OnClickListener{

    private View.OnClickListener listener;
    private ArrayList<message> messages;
    private custom_color colorMe;
    private custom_color colorOther;

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if(listener!=null)
        {
            listener.onClick(view);
        }
    }


    public list_messages()
    {

    }

    public list_messages(ArrayList<message> listDatos, custom_color colorMe, custom_color colorOther)
    {

            this.messages = listDatos;
            this.colorMe = colorMe;
            this.colorOther = colorOther;

    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hcr_mensajes_item_list, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(final list_messages.ViewHolderDatos holder, int position) {


        holder.asignarDatos( messages.get(position));

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tvMessagesMSG;
        CardView cvMessagesMSGIL;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            tvMessagesMSG = (TextView) itemView.findViewById(R.id.tvMessageMSGIL);
            cvMessagesMSGIL = (CardView) itemView.findViewById(R.id.cvMessageMSGIL);

        }



        @SuppressLint("ResourceAsColor")
        public void asignarDatos(message msg) {

            tvMessagesMSG.setText(msg.getText());


            if(msg.getMe()) {
                cvMessagesMSGIL.setBackgroundColor(colorMe.getColor());
                cvMessagesMSGIL.setForegroundGravity(Gravity.RIGHT);
                cvMessagesMSGIL.setRadius(100);
            }
            else
                cvMessagesMSGIL.setBackgroundColor(colorOther.getColor());
                cvMessagesMSGIL.setForegroundGravity(Gravity.LEFT);

        }

    }
}
