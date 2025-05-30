package com.example.jsonprofesor;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Vista2 extends AppCompatActivity {

    String url = "https://mdn.github.io/learning-area/javascript/oojs/json/superheroes.json";
    private TextView tw1, tw2;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vista2);

        tw1 = findViewById(R.id.textView);
        tw2 = findViewById(R.id.textView2);

        requestQueue = Volley.newRequestQueue(this);

        obtenerDatos();
    }

    public void obtenerDatos(){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Hero hero = new Hero();

                    String squadName = response.getString("squadName");
                    String homeTown = response.getString("homeTown");

                    int formed = response.getInt("formed");
                    String secretBase = response.getString("secretBase");
                    boolean active = response.getBoolean("active");

                    hero.setSquadName(squadName);
                    hero.setHomeTown(homeTown);
                    hero.setFormed(formed);
                    hero.setSecretBase(secretBase);
                    hero.setActive(active);

                    tw1.setText(hero.getSquadName() + hero.getHomeTown() + " " +
                            hero.getFormed() + " " + hero.getSecretBase()   + " " + hero.getActive());


                    JSONArray members = response.getJSONArray("members");

                    for(int i = 0; i < members.length(); i++){

                        JSONObject obj = members.getJSONObject(i);

                        String name = obj.getString("name");
                        int age = obj.getInt("age");
                        String secretIdentity = obj.getString("secretIdentity");

                        tw2.setText(tw2.getText()+ "\n" + (i + 1)+ " " +  name + " " + age + " " + secretIdentity);

                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Vista2.this, "Ha habido un error", Toast.LENGTH_SHORT).show();
            }
        }

        );
        requestQueue.add(request);
    }

}