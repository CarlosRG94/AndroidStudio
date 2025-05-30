package controlador;

import static modelo.Utilidades.RESULTADO_ERROR;
import static modelo.Utilidades.RESULTADO_ERROR_DESCONOCIDO;
import static modelo.Utilidades.RESULTADO_OK;
import static modelo.Utilidades.URLSERVIDOR;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.obtenerjsonurl.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Persona;
import modelo.ServidorPHPException;
import modelo.SpaceItemDecoration;
import modelo.Utilidades;
import vista.AdaptadorPersona;

public class ControladorPersona {

    private final String URLOBTENERPERSONAS = URLSERVIDOR+"obtenerTodasPersonas.php";
    private final String URLOBTENERPERSONA = URLSERVIDOR+"obtenerPersonaDNI.php";

    private Context contexto;
    private RecyclerView lista;
    private AdaptadorPersona adaptador;

    public ControladorPersona(Context contexto, RecyclerView lista)
    {
        this.contexto = contexto;
        this.lista = lista;
    }

    /**
     * Obtiene todas las personas del servidor
     * @throws ServidorPHPException Esta excepción se lanzará si ocurre algún error en la conexión
     */
    public void obtenerTodasPersonas() throws ServidorPHPException
    {
        try
        {
            // Inicializo la cola de peticiones
            RequestQueue colavolley = Volley.newRequestQueue(contexto);

            String url = URLOBTENERPERSONAS;

            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response)
                        {
                            // Procesamos el JSONArray
                            try
                            {
                                if( response != null )
                                {
                                    int resultadoobtenido = response.getJSONObject(0).getInt("estado");
                                    //System.out.println("EL RESULTADO ES " + resultadoobtenido);

                                    switch(resultadoobtenido)
                                    {
                                        case RESULTADO_OK:
                                            ArrayList<Persona> personas = new ArrayList<>();
                                            JSONArray datospersonas = response.getJSONObject(0).getJSONArray("mensaje");
                                            for (int i = 0; i < datospersonas.length(); i++)
                                            {
                                                JSONObject per = datospersonas.getJSONObject(i);
                                                String DNI = per.getString("DNI");
                                                String nombre = per.getString("Nombre");
                                                String apellidos = per.getString("Apellidos");
                                                String telefono = per.getString("Telefono");
                                                String email = per.getString("Email");
                                                Persona persona = new Persona(DNI, nombre, apellidos, telefono, email);
                                                personas.add(persona);
                                            }

                                            // Hay que crear en la caperta values un fichero dimens.xml y crear ahi list_space
                                            lista.addItemDecoration(new SpaceItemDecoration(contexto, R.dimen.list_space, true, true));
                                            // Con esto el tamaño del recyclerwiew no cambiará
                                            lista.setHasFixedSize(true);
                                            // Creo un layoutmanager para el recyclerview
                                            LinearLayoutManager llm = new LinearLayoutManager(contexto);
                                            lista.setLayoutManager(llm);

                                            adaptador = new AdaptadorPersona(contexto, personas);
                                            lista.setAdapter(adaptador);
                                            adaptador.refrescar();
                                            break;
                                        case RESULTADO_ERROR:
                                            throw new ServidorPHPException("Error, datos incorrectos.");
                                        case RESULTADO_ERROR_DESCONOCIDO:
                                            throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                                    }
                                }
                                else
                                {
                                    throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                                }
                            }
                            catch (JSONException | ServidorPHPException error)
                            {
                                System.out.println("Error -> " + error.toString());
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            System.out.println("Error -> " + error.toString());
                        }
                    }
            );

            // Agregamos la petición a la cola para que se ejecute
            colavolley.add(jsonObjectRequest);
        }
        catch(Exception error)
        {
            throw new ServidorPHPException(error.toString());
        }
    }

    /**
     * Obtiene una persona del servidor según su DNI
     * @param DNI DNI de la persona
     * @param tDNI Etiqueta donde se mostrará el DNI
     * @param tNombre Etiqueta donde se mostrará el nombre
     * @param tTelefono Etiqueta donde se mostrará el teléfono
     * @param tEmail Etiqueta donde se mostrara el email
     * @throws ServidorPHPException Esta excepción se lanzará si ocurre algún error en la conexión
     */
    public void obtenerPersonaDNI(String DNI, final TextView tDNI, final TextView tNombre, final TextView tTelefono, final TextView tEmail) throws ServidorPHPException
    {
        try
        {
            // Inicializo la cola de peticiones
            RequestQueue colavolley = Volley.newRequestQueue(contexto);

            // Declaro el array de los parámetros
            HashMap<String, String> parametros = new HashMap<>();
            // Meto los parámetros
            parametros.put("DNI", DNI);
            String urlfinal = Utilidades.buildURL(URLOBTENERPERSONA, parametros);

            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    urlfinal,
                    null,
                    new Response.Listener<JSONArray>()
                    {
                        @Override
                        public void onResponse(JSONArray response)
                        {
                            try
                            {
                                if( response != null )
                                {
                                    int resultadoobtenido = response.getJSONObject(0).getInt("estado");
                                    //System.out.println("EL RESULTADO ES " + resultadoobtenido);

                                    switch(resultadoobtenido)
                                    {
                                        case RESULTADO_OK:
                                            JSONArray datospersona = response.getJSONObject(0).getJSONArray("mensaje");
                                            JSONObject persona = datospersona.getJSONObject(0);

                                            tDNI.setText(persona.getString("DNI"));
                                            tNombre.setText(persona.getString("Nombre") + " " + persona.getString("Apellidos"));
                                            tTelefono.setText(persona.getString("Telefono"));
                                            tEmail.setText(persona.getString("Email"));
                                            break;
                                        case RESULTADO_ERROR:
                                            throw new ServidorPHPException("Error, datos incorrectos.");
                                        case RESULTADO_ERROR_DESCONOCIDO:
                                            throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                                    }
                                }
                                else
                                {
                                    throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                                }
                            }
                            catch (JSONException | ServidorPHPException e)
                            {
                                System.out.println("Error -> " + e.toString());
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            System.out.println("Error -> " + error.toString());
                        }
                    }
            );

            // Agregamos la petición a la cola para que se ejecute
            colavolley.add(jsonObjectRequest);
        }
        catch(Exception e)
        {
            throw new ServidorPHPException("Error -> " + e.toString());
        }
    }
}

