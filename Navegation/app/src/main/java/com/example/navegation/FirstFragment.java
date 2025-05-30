package com.example.navegation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.navegation.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = FragmentFirstBinding.inflate(inflater, container, false);

        // Navegar al SecondFragment cuando se haga clic en el botón
        binding.buttonNext.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_FirstFragment_to_SecondFragment);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
