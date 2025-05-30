package com.example.navegacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.navegacion.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        // Navegar al ThirdFragment cuando se haga clic en el botón
        binding.buttonNext.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_SecondFragment_to_ThirdFragment);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
