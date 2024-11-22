package com.example.listaelementos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorEjemplo extends RecyclerView.Adapter<AdaptadorEjemplo.HolderEjemplo> {

    //Clase interna equivalente al holder de los elementos
    public static class HolderEjemplo extends RecyclerView.ViewHolder {
        TextView tEjemplo1;
        TextView tEjemplo2;
        HolderEjemplo(View itemView) {
            //En el constructor obtendremos los recursos del fichero de recursos xml que tenemos
            //asociados a la clase, en este caso fichero elemento.xml
            super(itemView);
            tEjemplo1 = itemView.findViewById(R.id.tEjemplo1);
            tEjemplo2 = itemView.findViewById(R.id.tEjemplo2);
            //Si hubiera que sobrecargar eventos se haria aqui
        }
    };

    //Atributos de la clase AdaptadorEjemplo
    private ArrayList<ElementoLista> elementos;
    private Context contexto;

    //Constructor de la clase, recibe el contexto y los datos de los elementos
    public AdaptadorEjemplo(Context contexto, ArrayList<ElementoLista> elementos){
        this.contexto = contexto;
        this.elementos = elementos;
    }

    //Agrega los datos que queramos mostrar
    public void add(ArrayList<ElementoLista> datos){
        elementos.clear();
        elementos.addAll(datos);
    }

    //Actualiza los datos del recyclerView
    public void refrescar(){notifyDataSetChanged();}

    //Este método irá creando uno a uno los elementos de lista optimizando el uso de
    //memoria del dispositivo
    @Override
    public HolderEjemplo onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento, parent, false);
        HolderEjemplo pvh = new HolderEjemplo(v);
        return pvh;

    }

    //Este método mostrará los datos de cada elemento que esté visible en la lista
    @Override
    public void onBindViewHolder(HolderEjemplo elementoactual, int position) {
        elementoactual.tEjemplo1.setText(elementos.get(position).getTexto1());
        elementoactual.tEjemplo2.setText(elementos.get(position).getTexto2());
    }

    //Este método devuelve el número de elementos que tiene la lista
    @Override
    public int getItemCount() {
        return elementos.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
