package vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ejemplo2json.R;

import java.util.ArrayList;

import modelo.Persona;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.HolderPersona>
{
    /**
     * Clase interna equivalente al Holder de los elementos
     */
    public static class HolderPersona extends RecyclerView.ViewHolder
    {
        TextView tDNI, tNombre, tTelefono, tEmail;

        HolderPersona(View itemView)
        {
            super(itemView);
            tDNI = itemView.findViewById(R.id.tDNI);
            tNombre = itemView.findViewById(R.id.tNombre);
            tTelefono = itemView.findViewById(R.id.tTelefono);
            tEmail = itemView.findViewById(R.id.tEmail);
        }
    };

    // Atributos de la clase AdaptadorPersona
    private ArrayList<Persona> datos;
    private Context contexto;

    public AdaptadorPersona(Context contexto, ArrayList<Persona> datos)
    {
        this.contexto = contexto;
        this.datos = datos;
    }

    /**
     * Agrega los datos que queramos mostrar
     * @param datos Datos a mostrar
     */
    public void add(ArrayList<Persona> datos)
    {
        datos.clear();
        datos.addAll(datos);
    }

    /**
     * Actualiza los datos del ReciclerView
     */
    public void refrescar()
    {
        notifyDataSetChanged();
    }

    @Override
    public HolderPersona onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_contact, parent, false);
        HolderPersona pvh = new HolderPersona(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(HolderPersona personadeturno, int position)
    {
        personadeturno.tDNI.setText(datos.get(position).getDNI());
        personadeturno.tNombre.setText(datos.get(position).getNombre() + " " + datos.get(position).getApellidos());
        personadeturno.tTelefono.setText(datos.get(position).getTelefono());
        personadeturno.tEmail.setText(datos.get(position).getEmail());
    }

    @Override
    public int getItemCount()
    {
        return datos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
