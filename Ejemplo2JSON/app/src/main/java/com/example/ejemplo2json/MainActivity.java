package com.example.ejemplo2json;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import controlador.ControladorPersona;
import modelo.Persona;
import modelo.ServidorPHPException;
import modelo.SpaceItemDecoration;
import vista.AdaptadorPersona;

public class MainActivity extends AppCompatActivity {
    private ControladorPersona controladorp;
    private RecyclerView lista;
    private AdaptadorPersona adaptador;
    private TextView tDNI2, tNombre2, tTelefono2, tEmail2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ***** Ligamos los recursos de la aplicación *****
        lista = findViewById(R.id.lista);
        tDNI2 = findViewById(R.id.tDNI2);
        tNombre2 = findViewById(R.id.tNombre2);
        tTelefono2 = findViewById(R.id.tTelefono2);
        tEmail2 = findViewById(R.id.tEmail2);
        // *************************************************
        controladorp = new ControladorPersona();

        try
        {
            // Obtengo los datos de las personas del servidor
            ArrayList<Persona> personas = controladorp.obtenerPersonas();

            // Hay que crear en la caperta values un fichero dimens.xml y crear ahi list_space
            lista.addItemDecoration(new SpaceItemDecoration(this, R.dimen.list_space, true, true));
            // Con esto el tamaño del recyclerwiew no cambiará
            lista.setHasFixedSize(true);
            // Creo un layoutmanager para el recyclerview
            LinearLayoutManager llm = new LinearLayoutManager(this);
            lista.setLayoutManager(llm);

            adaptador = new AdaptadorPersona(this, personas);
            lista.setAdapter(adaptador);
            adaptador.refrescar();

            // Obtengo los datos de una persona
            Persona persona = controladorp.obtenerDatosPersona("24158749L");
            tDNI2.setText(persona.getDNI());
            tNombre2.setText(persona.getNombre() + " " + persona.getApellidos());
            tTelefono2.setText(persona.getTelefono());
            tEmail2.setText(persona.getEmail());
        }
        catch (ServidorPHPException e)
        {
            System.out.println("Error obteniendo las personas -> " + e.toString());
        }

    }
}