package com.twicky.estebancarranza.reparto.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 02/03/2018.
 */

public class list_buttons extends RecyclerView.Adapter<list_buttons.ViewHolderDatos> implements View.OnClickListener{
    ArrayList<String> listDatos;
    private View.OnClickListener listener;

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

    public static class Boton
    {
        String titulo;
        String descripcion;
        String idBoton;

        public Boton()
        {

        }
        public Boton(String titulo, String descripcion, String idBoton) {
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.idBoton = idBoton;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getIdBoton() {
            return idBoton;
        }
        public void setIdBoton(String idBoton) {
            this.idBoton = idBoton;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getDescripcion() {
            return descripcion;
        }



    };

    ArrayList<Boton> botones;

    public list_buttons()
    {

    }
    /*
    public recycler_list(ArrayList<String> listDatos)
    {
        this.listDatos = listDatos;
    }*/

    public list_buttons(ArrayList<Object> listDatos)
    {
        if(listDatos.get(0) instanceof Boton)
        {
            this.botones = (ArrayList<Boton>)(ArrayList<?>)(listDatos);
        }
        if(listDatos.get(0) instanceof String)
        {
            this.listDatos = (ArrayList<String>)(ArrayList<?>)(listDatos);
        }


    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_list, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        //holder.asignarDatos(listDatos.get(position));
        holder.asignarDatos(botones.get(position).titulo.toString(), botones.get(position).descripcion.toString());


        //Toast.makeText(getApplicationContext(), "a", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        //return listDatos.size();
        return botones.size();
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

        TextView lblTitulo;
        TextView lblDescripcion;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            lblTitulo = (TextView) itemView.findViewById(R.id.lblTitulo);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);

        }

        public void asignarDatos(String s) {
            lblTitulo.setText(s);
        }
        public void asignarDatos(String titulo, String descripcion)
        {
            lblTitulo.setText(titulo);
            lblDescripcion.setText(descripcion);
        }
    }
}
