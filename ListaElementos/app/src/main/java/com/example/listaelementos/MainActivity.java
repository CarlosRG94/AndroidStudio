package com.example.listaelementos;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listaEjemplo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ligamos los elementos gráficos
        listaEjemplo = findViewById(R.id.listaEjemplo);

        ArrayList<ElementoLista> elementos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ElementoLista elemento = new ElementoLista("Elemento " + i +"AA" , "Elemento " + i+ "BB");
            elementos.add(elemento);

        }

        //Con esto el tamaño del recyclerView no cambia
        listaEjemplo.setHasFixedSize(true);

        //Creo un layout manager para el recyclerView
        GridLayoutManager ltm;
        ltm = new GridLayoutManager(this,3);
        listaEjemplo.setLayoutManager(ltm);

        //Creo un adaptador para el recyclerView y lo enlazo con el recyclerView
        AdaptadorEjemplo adaptador = new AdaptadorEjemplo(this, elementos);
        listaEjemplo.setAdapter(adaptador);
        adaptador.refrescar();
    }
}