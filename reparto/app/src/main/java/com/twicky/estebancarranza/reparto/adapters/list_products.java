package com.twicky.estebancarranza.reparto.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.datos.producto;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by esteban.carranza on 12/03/2018.
 */

public class list_products  extends RecyclerView.Adapter<list_products.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<producto> productos;

    public list_products(ArrayList<producto> productos) {
        this.productos = productos;
    }

    public list_products() {
    }


    @Override
    public list_products.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pcr_products_item_list, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(list_products.ViewHolderDatos holder, int position) {
        holder.asignarDatos(this.productos.get(position));
        holder.btnRestar.setOnClickListener(this);
        holder.btnSumar.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

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

    public class ViewHolderDatos  extends RecyclerView.ViewHolder{

        TextView tvNombre;
        TextView tvDescripcion;
        EditText txtTotal;
        Button btnRestar;
        Button btnSumar;



        public ViewHolderDatos(View itemView) {
            super(itemView);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            txtTotal = (EditText) itemView.findViewById(R.id.txtTotal);
            btnRestar = (Button) itemView.findViewById(R.id.btnRestar);
            btnSumar = (Button) itemView.findViewById(R.id.btnSumar);


        }

        public void asignarDatos(producto producto)
        {
            tvNombre.setText(producto.getTitulo());
            txtTotal.setText(String.valueOf(producto.getTotal()));
            tvDescripcion.setText(producto.getDescripcion());
        }
    }
}
