package controlador;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.JSONParser;
import modelo.Persona;
import modelo.ServidorPHPException;

public class ControladorPersona
{
    private final int RESULTADO_OK = 1;
    private final int RESULTADO_ERROR = 2;
    private final int RESULTADO_ERROR_DESCONOCIDO = 3;

    private final String urlservidor = "http://192.168.1.136/personas/";
    private final String urlobtenertodaspersonas = urlservidor + "obtenerTodasPersonas.php";
    private final String urlobtenerpersonapordni = urlservidor + "obtenerPersonaDNI.php";

    /**
     * Constructor
     */
    public ControladorPersona()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /**
     * Obtiene todos los datos de las personas existentes en el servidor
     * @return Array con los datos de las personas
     * @throws ServidorPHPException
     */
    public ArrayList<Persona> obtenerPersonas() throws ServidorPHPException
    {
        ArrayList<Persona> personas = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONArray datos;

        try
        {
            // Obtengo los datos de las personas del servidor
            // Como no tiene parámetros la llamada al servidor
            // el segundo parámetro de la llamada es null
            datos = parser.getJSONArrayFromUrl(urlobtenertodaspersonas, null);

            if( datos != null )
            {
                int resultadoobtenido = datos.getJSONObject(0).getInt("estado");
                //System.out.println("EL RESULTADO ES " + resultadoobtenido);

                switch(resultadoobtenido)
                {
                    case RESULTADO_OK:
                        JSONArray mensaje = datos.getJSONObject(0).getJSONArray("mensaje");
                        for(int i = 0; i < mensaje.length(); i++)
                        {
                            // Obtengo los datos de cada persona
                            String dni = mensaje.getJSONObject(i).getString("DNI");
                            String nombre = mensaje.getJSONObject(i).getString("nombre");
                            String apellidos = mensaje.getJSONObject(i).getString("apellidos");
                            String telefono = mensaje.getJSONObject(i).getString("telefono");
                            String email = mensaje.getJSONObject(i).getString("email");

                            Persona p = new Persona(dni, nombre, apellidos, telefono, email);
                            personas.add(p);
                        }
                        break;
                    case RESULTADO_ERROR:
                        throw new ServidorPHPException("Error, datos incorrectos.");
                    case RESULTADO_ERROR_DESCONOCIDO:
                        throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                }

            }
            else
            {
                System.out.println("Error obteniendo los datos del servidor.");
            }
        }
        catch (IOException | JSONException e)
        {
            throw new ServidorPHPException(e.toString());
        }

        return personas;
    }

    /**
     * Obtiene los datos de una persona del servidor por su DNI
     * @param DNI DNI de la persona a buscar
     * @return Persona identificada por ese DNI
     * @throws ServidorPHPException
     */
    public Persona obtenerDatosPersona(String DNI) throws ServidorPHPException
    {
        JSONParser parser = new JSONParser();
        Persona devolver = new Persona(DNI, "", "", "", "");
        JSONArray datos;
        // Declaro el array de los parámetros
        HashMap<String, String> parametros = new HashMap<>();

        // Meto los parámetros
        parametros.put("DNI", DNI);

        try
        {
            // Obtengo los datos de la persona del servidor
            datos = parser.getJSONArrayFromUrl(urlobtenerpersonapordni, parametros);

            if( datos != null )
            {
                int resultadoobtenido = datos.getJSONObject(0).getInt("estado");
                //System.out.println("EL RESULTADO ES " + resultadoobtenido);

                switch(resultadoobtenido)
                {
                    case RESULTADO_OK:
                        JSONArray mensaje = datos.getJSONObject(0).getJSONArray("mensaje");

                        // Obtengo los datos de la persona
                        String nombre = mensaje.getJSONObject(0).getString("nombre");
                        String apellidos = mensaje.getJSONObject(0).getString("apellidos");
                        String telefono = mensaje.getJSONObject(0).getString("telefono");
                        String email = mensaje.getJSONObject(0).getString("email");

                        devolver.setNombre(nombre);
                        devolver.setApellidos(apellidos);
                        devolver.setTelefono(telefono);
                        devolver.setEmail(email);
                        break;
                    case RESULTADO_ERROR:
                        throw new ServidorPHPException("Error, datos incorrectos.");
                    case RESULTADO_ERROR_DESCONOCIDO:
                        throw new ServidorPHPException("Error obteniendo los datos del servidor.");
                }
            }
            else
            {
                System.out.println("Error obteniendo los datos del servidor.");
            }
        }
        catch (IOException | JSONException e)
        {
            throw new ServidorPHPException(e.toString());
        }

        return devolver;
    }
}
