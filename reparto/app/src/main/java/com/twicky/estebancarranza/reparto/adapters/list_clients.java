package com.twicky.estebancarranza.reparto.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.datos.cliente;
import com.twicky.estebancarranza.reparto.datos.custom_parameter;
import com.twicky.estebancarranza.reparto.estaticos.estado_cliente;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 10/03/2018.
 */

public class list_clients extends RecyclerView.Adapter<list_clients.ViewHolderDatos> implements View.OnClickListener{

    private View.OnClickListener listener;
    custom_parameter opciones_adicionales;
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

    ArrayList<cliente> clientes;

    public list_clients()
    {

    }

    public list_clients(ArrayList<Object> listDatos)
    {
        if(listDatos.get(0) instanceof cliente)
        {
            this.clientes = (ArrayList<cliente>)(ArrayList<?>)(listDatos);
        }
    }
    public list_clients(ArrayList<Object> listDatos, custom_parameter opciones_adicionales)
    {
        if(listDatos.get(0) instanceof cliente)
        {
            this.clientes = (ArrayList<cliente>)(ArrayList<?>)(listDatos);
        }

        this.opciones_adicionales = opciones_adicionales;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pcr_clients_item_list, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {

        holder.asignarDatos
        (
            clientes.get(position).getNombre(),
            clientes.get(position).getDomicilio(),
            clientes.get(position).getEstadoActual()
        );

    }

    @Override
    public int getItemCount() {
        return clientes.size();
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

        TextView tvClienteNombre;
        TextView tvClienteDomicilio;
        ImageView ivEstadoActual;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            tvClienteNombre = (TextView) itemView.findViewById(R.id.tvClienteNombre);
            tvClienteDomicilio = (TextView) itemView.findViewById(R.id.tvClienteDomicilio);
            ivEstadoActual = (ImageView) itemView.findViewById(R.id.ivClienteConfirmacion);

            if(opciones_adicionales != null)
            {
                switch(opciones_adicionales.getTipoLayout())
                {
                    case cliente_lista_confirmar:

                    break;
                    case cliente_lista_CRU:
                        ivEstadoActual.setVisibility(View.GONE);
                    break;
                    default:break;
                }
            }
        }



        public void asignarDatos(String nombre, String domicilio, estado_cliente estadoActual) {
            tvClienteNombre.setText(nombre);
            tvClienteDomicilio.setText(domicilio);
            switch (estadoActual)
            {
                case confirmado:
                    ivEstadoActual.setImageResource(R.drawable.ic_check_circle_black_24dp);
                break;

                case sinConfirmar:
                    ivEstadoActual.setImageResource(R.drawable.ic_cancel_black_24dp);
                break;
                case sinAsignar:
                    ivEstadoActual.setImageResource(R.drawable.ic_warning_black_24dp);
                break;
            }
        }

    }
}
