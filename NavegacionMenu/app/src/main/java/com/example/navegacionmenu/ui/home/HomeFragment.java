package com.example.navegacionmenu.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.navegacionmenu.La_Actividad;
import com.example.navegacionmenu.R;
import com.example.navegacionmenu.databinding.FragmentHomeBinding;
import com.example.navegacionmenu.ui.nuevo.BlankFragment;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private Button buttonhom;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable
    ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Obtenemos el layout de la pantalla para buscar los elementos
        View root = inflater.inflate(R.layout.fragment_home, container,
                false);

        // Obtenemos los elementos del fragment
        // IMPORTANTÍSIMO hacer el findViewById desde el root
        buttonhom = root.findViewById(R.id.buttonhom);
        buttonhom.setOnClickListener(this);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
// TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonhom) {
            Intent intent = new Intent(getActivity(), La_Actividad.class);
            startActivity(intent);
        }
    }

}