package com.example.listajson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPescado extends RecyclerView.Adapter<AdaptadorPescado.HolderEjemplo> {

    public static class HolderEjemplo extends RecyclerView.ViewHolder {
        TextView textFishName;
        TextView textPrice;
        TextView textSize;
        TextView textType;
        ImageView ivFish;

        HolderEjemplo(View itemView) {
            //En el constructor obtendremos los recursos del fichero de recursos xml que tenemos
            //asociados a la clase, en este caso fichero elemento_pescado.xml
            super(itemView);
            textFishName = itemView.findViewById(R.id.textFishName);
            textPrice = itemView.findViewById(R.id.textPrice);
            textSize = itemView.findViewById(R.id.textSize);
            textType = itemView.findViewById(R.id.textType);
            ivFish = itemView.findViewById(R.id.ivFish);
            //Si hubiera que sobrecargar eventos se haria aqui
        }
    };
    //Atributos de la clase AdaptadorEjemplo
    private ArrayList<Pescado> elementos;
    private Context contexto;

    //Constructor de la clase, recibe el contexto y los datos de los elementos
    public AdaptadorPescado(Context contexto, ArrayList<Pescado> elementos){
        this.contexto = contexto;
        this.elementos = elementos;
    }

    //Agrega los datos que queramos mostrar
    public void add(ArrayList<Pescado> datos){
        elementos.clear();
        elementos.addAll(datos);
    }

    //Actualiza los datos del recyclerView
    public void refrescar(){notifyDataSetChanged();}

    //Este método irá creando uno a uno los elementos de lista optimizando el uso de
    //memoria del dispositivo
    @Override
    public HolderEjemplo onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_pescado, parent, false);
        HolderEjemplo pvh = new HolderEjemplo(v);
        return pvh;

    }

    //Este método mostrará los datos de cada elemento que esté visible en la lista
    @Override
    public void onBindViewHolder(HolderEjemplo elementoactual, int position) {
        elementoactual.ivFish.setImageResource(R.drawable.ic_img_fish);
        elementoactual.textFishName.setText(elementos.get(position).getNombre());
        elementoactual.textPrice.setText(String.valueOf(elementos.get(position).getPrecio()));
        elementoactual.textSize.setText(elementos.get(position).getTamano());
        elementoactual.textType.setText(elementos.get(position).getCategoria());
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
