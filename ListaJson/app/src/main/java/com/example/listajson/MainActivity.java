package com.example.listajson;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listaPescados;
    private AdaptadorPescado adaptador;

    private final String CONTENIDO = "[{\"fish_name\":\"Indian Mackerel\",\"cat_name\":\"Marine  Fish\",\"size_name\":\"Medium\",\"price\":\"100\"},\n" +
            "{\"fish_name\":\"Manthal Repti\",\"cat_name\":\"Marine Fish\",\"size_name\":\"Small\",\"price\":\"200\"},\n" +
            "{\"fish_name\":\"Baby Sole Fish\",\"cat_name\":\"Marine Fish\",\"size_name\":\"Small\",\"price\":\"600\"},\n" +
            "{\"fish_name\":\"Silver Pomfret\",\"cat_name\":\"Marine Fish\",\"size_name\":\"Large\",\"price\":\"300\"},\n" +
            "{\"fish_name\":\"Squid\",\"cat_name\":\"Shell Fish\",\"size_name\":\"Small\",\"price\":\"800\"},\n" +
            "{\"fish_name\":\"Clam Meat\",\"cat_name\":\"Shell Fish\",\"size_name\":\"Small\",\"price\":\"350\"},\n" +
            "{\"fish_name\":\"Indian Prawns\",\"cat_name\":\"Shell Fish\",\"size_name\":\"Medium\",\"price\":\"270\"},\n" +
            "{\"fish_name\":\"Mud Crab\",\"cat_name\":\"Shell Fish\",\"size_name\":\"Medium\",\"price\":\"490\"},\n" +
            "{\"fish_name\":\"Grey Mullet\",\"cat_name\":\"Backwater Fish\",\"size_name\":\"Small\",\"price\":\"670\"},\n" +
            "{\"fish_name\":\"Baasa\",\"cat_name\":\"Backwater Fish\",\"size_name\":\"Large\",\"price\":\"230\"},\n" +
            "{\"fish_name\":\"Pearl Spot\",\"cat_name\":\"Backwater Fish\",\"size_name\":\"Small\",\"price\":\"340\"},\n" +
            "{\"fish_name\":\"Anchovy\",\"cat_name\":\"Marine Fish\",\"size_name\":\"Small\",\"price\":\"130\"},\n" +
            "{\"fish_name\":\"Sole Fish\",\"cat_name\":\"Marine Fish\",\"size_name\":\"Medium\",\"price\":\"250\"},\n" +
            "{\"fish_name\":\"Silver Croaker\",\"cat_name\":\"Marine Fish\",\"size_name\":\"Small\",\"price\":\"220\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ***** Ligamos los elementos de la actividad *****
        listaPescados = findViewById(R.id.listaPescados);
        // *************************************************

        try
        {
            ArrayList<Pescado> datospescados = new ArrayList<>();
            // Creo el JSONArray con los datos en formato JSON
            JSONArray jArray = new JSONArray(CONTENIDO);
            // Extraigo los datos que están en JSON
            for (int i = 0; i < jArray.length(); i++)
            {
                JSONObject json_data = jArray.getJSONObject(i);
                String nombre = json_data.getString("fish_name");
                String categoria = json_data.getString("cat_name");
                String tamano = json_data.getString("size_name");;
                int precio = json_data.getInt("price");
                Pescado pescado = new Pescado(nombre, categoria, tamano, precio);
                datospescados.add(pescado);
            }

            // Mostramos la lista
            // Con esto el tamaño del recyclerwiew no cambiará
            listaPescados.setHasFixedSize(true);
            // Creo un layoutmanager para el recyclerview
            LinearLayoutManager llm = new LinearLayoutManager(this);
            listaPescados.setLayoutManager(llm);
            adaptador = new AdaptadorPescado(this, datospescados);
            listaPescados.setAdapter(adaptador);
            adaptador.refrescar();
        }
        catch (JSONException e)
        {
            System.out.println("Error: " + e.toString());
        }

    }
}