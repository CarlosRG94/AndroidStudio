package com.example.carrouselimagen;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private MotionLayout motionLayout;
    private Handler handler = new Handler();
    private boolean isTransitionToEnd = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Vincular el MotionLayout
        motionLayout = findViewById(R.id.main);

        // Iniciar la transición automática
        startAutoTransition();
    }
    private void startAutoTransition() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isTransitionToEnd) {
                    motionLayout.transitionToEnd(); // Mover al estado final
                } else {
                    motionLayout.transitionToStart(); // Mover al estado inicial
                }
                isTransitionToEnd = !isTransitionToEnd; // Alternar estados
                startAutoTransition(); // Llamar de nuevo para mantener el ciclo
            }
        }, 3000); // Tiempo entre transiciones en milisegundos (3 segundos)
    }
}