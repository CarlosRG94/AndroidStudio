package com.example.obtenerjsonurl;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import controlador.ControladorPersona;
import modelo.ServidorPHPException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista;
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

        ControladorPersona controladorp = new ControladorPersona(this, lista);

        try
        {
            controladorp.obtenerTodasPersonas();
            controladorp.obtenerPersonaDNI("24355122s", tDNI2, tNombre2, tTelefono2, tEmail2);
        }
        catch (ServidorPHPException e)
        {
            e.printStackTrace();
        }

    }
}