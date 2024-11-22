package com.example.navegacionmenu.ui.slideshow;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.navegacionmenu.R;
import com.example.navegacionmenu.databinding.FragmentSlideshowBinding;
import com.example.navegacionmenu.ui.nuevo.BlankFragment;

import java.util.Random;

public class SlideshowFragment extends Fragment implements View.OnClickListener {


    private Button bToast1, bToast2, bToast3;

    public static SlideshowFragment newInstance() {
        return new SlideshowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
    ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Obtenemos el layout de la pantalla para buscar los elementos
        View root = inflater.inflate(R.layout.fragment_slideshow, container,
                false);

        // Obtenemos los elementos del fragment
        // IMPORTANTÍSIMO hacer el findViewById desde el root
        bToast1 = root.findViewById(R.id.bToast1);
        bToast1.setOnClickListener(this);
        bToast2 = root.findViewById(R.id.bToast2);
        bToast2.setOnClickListener(this);
        bToast3 = root.findViewById(R.id.bToast3);
        bToast3.setOnClickListener(this);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
// TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bToast1)  {
            Toast.makeText(getContext(), "Toast por defecto", Toast.LENGTH_SHORT).show();
        }else if (v.getId() == R.id.bToast2) {
            Random aleatorio = new Random();
            int random = aleatorio.nextInt(4);

            // Inflar el diseño personalizado del Toast
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_layout, getView().findViewById(R.id.toast_text));
            TextView text = layout.findViewById(R.id.toast_text);
            text.setText("Toast con gravity");

            Toast t = new Toast(getContext());
            t.setDuration(Toast.LENGTH_SHORT);
            t.setView(layout);

            // Configurar la posición usando setGravity
            if (random == 0) {
                t.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
            } else if (random == 1) {
                t.setGravity(Gravity.CENTER | Gravity.RIGHT, 0, 0);
            } else if (random == 2) {
                t.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
            } else if (random == 3) {
                t.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
            }

            t.show();
        } else if (v.getId() == R.id.bToast3) {
            Toast t2 = new Toast(getContext());
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_layout_2, getView().findViewById(R.id.toast_text2));
            TextView text = layout.findViewById(R.id.toast_text2);
            text.setText("Toast Personalizado");
            t2.setDuration(Toast.LENGTH_SHORT);
            t2.setView(layout);
            t2.show();

        }
    }
}