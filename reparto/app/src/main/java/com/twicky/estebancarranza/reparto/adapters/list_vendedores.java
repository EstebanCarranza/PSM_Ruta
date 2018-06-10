package com.twicky.estebancarranza.reparto.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.vendedorSQL;
import com.twicky.estebancarranza.reparto.models.vendedor;

import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.estaticos.global.getVendedor;
import static com.twicky.estebancarranza.reparto.estaticos.global.setVendedorMSG;

/**
 * Created by esteban.carranza on 23/05/2018.
 */

public class list_vendedores extends RecyclerView.Adapter<list_vendedores.ViewHolderDatos> implements View.OnClickListener{
    private View.OnClickListener listener;
    private ArrayList<vendedor> vendedor;
    Context thisContext;

    @Override
    public void onClick(View view) {
        if(listener!=null)
        {
            listener.onClick(view);
        }
    }

    public list_vendedores(ArrayList<vendedor> vendedor) {
        this.vendedor = vendedor;
    }



    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public list_vendedores.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vendedor, null, false);
        view.setOnClickListener(this);



        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(final list_vendedores.ViewHolderDatos holder, int position) {
        holder.asignarDatos( vendedor.get(position));

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() { return vendedor.size(); }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView lblNombreVendedor;
        TextView lblID;
        TextView lblCorreo;

        public ViewHolderDatos(final View itemView) {
            super(itemView);

            lblNombreVendedor = itemView.findViewById(R.id.lblNombreVendedor);
            lblID = itemView.findViewById(R.id.lblID);
            lblCorreo = itemView.findViewById(R.id.lblCorreo);
            /*
            lblNombreVendedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Diste click", Toast.LENGTH_SHORT).show();
                }
            });
            */

        }

        public void asignarDatos(vendedor vendedor) {

            if(vendedor.getIdVendedor() == getVendedor().getIdVendedor())
            {
                lblNombreVendedor.setText("(Yo) " + vendedor.getNombres() + " " + vendedor.getAppat());
            }
            else
                lblNombreVendedor.setText(vendedor.getNombres() + " " + vendedor.getAppat());
           lblID.setText(String.valueOf(vendedor.getIdVendedor()));
            lblCorreo.setText(vendedor.getCorreo().toString());

        }
    }


}
