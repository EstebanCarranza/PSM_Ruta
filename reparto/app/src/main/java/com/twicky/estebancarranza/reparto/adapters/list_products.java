package com.twicky.estebancarranza.reparto.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.datos.custom_parameter;
import com.twicky.estebancarranza.reparto.datos.producto;
import com.twicky.estebancarranza.reparto.estaticos.layout;
import com.twicky.estebancarranza.reparto.estaticos.list_products_options;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.twicky.estebancarranza.reparto.estaticos.list_products_options.restar;
import static com.twicky.estebancarranza.reparto.estaticos.list_products_options.sumar;

/**
 * Created by esteban.carranza on 12/03/2018.
 */

//clase que crea el adaptador para productos
public class list_products
//extensión de un recycler view, ya que ahí es donde se agregará el adaptador
extends RecyclerView.Adapter<list_products.ViewHolderDatos>
//se implementa el evento del click
implements View.OnClickListener
{

    ArrayList<producto> productos;
    custom_parameter opciones_adicionales;

    public list_products(ArrayList<producto> productos) {
        this.productos = productos;
    }
    public list_products(ArrayList<producto> productos, custom_parameter opciones_adicionales) {
        this.productos = productos;
        this.opciones_adicionales = opciones_adicionales;
    }

    public list_products() {
    }


    @Override
    public list_products.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pcr_products_item_list, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    protected void data(Object elemento, int position, list_products_options type)
    {
        float total = 0;


        EditText editText = elemento instanceof EditText ? ((EditText) elemento) : null;
        if(editText != null)
        {
            if(!editText.getText().toString().isEmpty())
            {
                try
                {
                    total = Float.parseFloat(editText.getText().toString());
                    switch (type)
                    {
                        case sumar:
                            total++;
                        break;
                        case restar:
                            total--;
                        break;
                        default:total= -1; break;
                    }

                }
                catch (NumberFormatException e)
                {
                    total = -1;
                }
                editText.setText(String.valueOf(total));
            }
        }


    }
    @Override
    public void onBindViewHolder(final list_products.ViewHolderDatos holder, final int position) {


        holder.asignarDatos(this.productos.get(position));
        /*
        holder.btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                data(holder.txtTotal, position, restar);
            }
        });
        holder.btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                data(holder.txtTotal, position, sumar);

            }
        });
        */



        holder.btnConfirmarPPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productos.get(position).setColor(R.color.green);
                notifyDataSetChanged();
            }
        });
        holder.btnRechazarPPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productos.get(position).setColor(R.color.red);
                notifyDataSetChanged();
            }
        });

       // holder.cvProducto.setBackgroundColor(productos.get(position).getColor());


        holder.fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productos.remove(position);
                notifyDataSetChanged();
            }
        });


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
        /*
        Button btnRestar;
        Button btnSumar;
        */
        Button btnConfirmarPPC;
        Button btnRechazarPPC;
        FloatingActionButton fab_delete;
        CardView cvProducto;




        public ViewHolderDatos(View itemView) {
            super(itemView);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            txtTotal = (EditText) itemView.findViewById(R.id.txtTotal);
            btnConfirmarPPC = (Button) itemView.findViewById(R.id.btnConfirmarPPC);
            btnRechazarPPC =  (Button) itemView.findViewById(R.id.btnRechazarPPC);
            cvProducto = (CardView) itemView.findViewById(R.id.cvProducto);

            /*
            btnRestar = (Button) itemView.findViewById(R.id.btnRestar);
            btnSumar = (Button) itemView.findViewById(R.id.btnSumar);
            */
            fab_delete = (FloatingActionButton) itemView.findViewById(R.id.fab_delete);

            if(opciones_adicionales != null)
            {
                switch (opciones_adicionales.getTipoLayout()) {
                    case producto_por_cliente:
                        fab_delete.hide();


                        break;
                    case producto_sin_cliente:

                    break;
                    case producto_vista:
                        btnRechazarPPC.setVisibility(View.GONE);
                        btnConfirmarPPC.setVisibility(View.GONE);

                        fab_delete.hide();

                    break;

                    case producto_por_entregar:
                        btnRechazarPPC.setVisibility(View.GONE);
                        btnConfirmarPPC.setVisibility(View.GONE);

                        fab_delete.hide();
                    break;
                    default:
                        break;
                }
            }
        }

        public void asignarDatos(producto producto)
        {
            tvNombre.setText(producto.getTitulo());
            txtTotal.setText(String.valueOf(producto.getTotal()));
            tvDescripcion.setText(producto.getDescripcion());
            cvProducto.setBackgroundColor(producto.getColor());
        }
    }
}
