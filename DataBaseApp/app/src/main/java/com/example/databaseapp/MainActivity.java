package com.example.databaseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText txNombre, tphone;
    private BDUsuarios bd;
    private RecyclerView listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txNombre = findViewById(R.id.txNombre);
        tphone = findViewById(R.id.tphone);
        bd = new BDUsuarios(this);
        listaUsuarios = findViewById(R.id.listaUsuarios);
    }

    public void insertar(View v) {
        int telefono = Integer.parseInt(tphone.getText().toString());
        String nombre = txNombre.getText().toString();
        try {
            bd.insertarUsuario(telefono, nombre);
            Toast.makeText(this, "Usuario insertado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
            System.out.println(e.toString());
        }
    }
    public void delete(View v) {
        try{
            bd.eliminarUsuario();
            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
            System.out.println(e.toString());
        }
    }
    public void update(View v) {
        int telefonoNuevo = Integer.parseInt(tphone.getText().toString());
        String nombreNuevo = txNombre.getText().toString();
        try {
            bd.actualizarUsuario("Laura", nombreNuevo, telefonoNuevo);
            Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
            System.out.println(e.toString());
        }
    }
    public void consultar(View view) {
        try{
        //Con esto fijamos el tamaño del recyclerView
        listaUsuarios.setHasFixedSize(true);
        //Creamos un layoutManager para el recylcerView
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listaUsuarios.setLayoutManager(llm);

            ArrayList<ElementoUsuario> usuarios = bd.mostrarUsuarios();
            if (usuarios == null || usuarios.isEmpty()) {
                Toast.makeText(this, "No se encontraron usuarios", Toast.LENGTH_SHORT).show();
                return;
            }

            AdaptadorEjemplo adapter = new AdaptadorEjemplo(this,usuarios);
            listaUsuarios.setAdapter(adapter);
            adapter.refrescar();

        }catch (Exception e){
            Toast.makeText(this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
            System.out.println(e.toString());
        }
    }
}