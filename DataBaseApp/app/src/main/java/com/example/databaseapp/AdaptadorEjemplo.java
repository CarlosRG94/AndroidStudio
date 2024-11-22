package com.example.databaseapp;

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
            TextView tNombre;
            TextView tTelefono;
            HolderEjemplo(View itemView) {
                //En el constructor obtendremos los recursos del fichero de recursos xml que tenemos
                //asociados a la clase, en este caso fichero elemento.xml
                super(itemView);
                tNombre = itemView.findViewById(R.id.tNombre);
                tTelefono = itemView.findViewById(R.id.tTelefono);
                //Si hubiera que sobrecargar eventos se haria aqui
            }
        };

        //Atributos de la clase AdaptadorEjemplo
        private ArrayList<ElementoUsuario> elementos;
        private Context contexto;

        //Constructor de la clase, recibe el contexto y los datos de los elementos
        public AdaptadorEjemplo(Context contexto, ArrayList<ElementoUsuario> elementos){
            this.contexto = contexto;
            this.elementos = elementos;
        }

        //Agrega los datos que queramos mostrar
        public void add(ArrayList<ElementoUsuario> datos){
            elementos.clear();
            elementos.addAll(datos);
        }

        //Actualiza los datos del recyclerView
        public void refrescar(){notifyDataSetChanged();}

        //Este método irá creando uno a uno los elementos de lista optimizando el uso de
        //memoria del dispositivo
        @Override
        public HolderEjemplo onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
            HolderEjemplo pvh = new HolderEjemplo(v);
            return pvh;

        }

        //Este método mostrará los datos de cada elemento que esté visible en la lista
        @Override
        public void onBindViewHolder(HolderEjemplo elementoactual, int position) {
            elementoactual.tNombre.setText(elementos.get(position).getNombre());
            elementoactual.tTelefono.setText(String.valueOf(elementos.get(position).getTelefono()));
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
