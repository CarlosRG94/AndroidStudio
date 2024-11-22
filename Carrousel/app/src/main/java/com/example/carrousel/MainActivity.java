package com.example.carrousel;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ViewFlipper v_flipper;
    private MediaPlayer mp;
    private int [] canciones = {R.raw.cancion, R.raw.cancion2, R.raw.cancion3, R.raw.cancion4};
    private int indiceCancion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images[] = {R.drawable.imagen1, R.drawable.imagen2, R.drawable.imagen3};

        v_flipper = findViewById(R.id.v_flipper);
        for (int image : images) {
            flipperImages(image);
        }

    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void pararImagen(View view) {
        v_flipper.stopFlipping();

    }
    public void empezarImagen(View view) {
        if(!v_flipper.isFlipping()) {
            v_flipper.startFlipping();
        }

    }

    public void start(View view) {
        if(mp == null){
            mp = MediaPlayer.create(this, canciones[indiceCancion]);
            mp.start();
        }else if(!mp.isPlaying()){
            mp.start();
        }
    }
    public void pause(View view) {
        if (mp != null && mp.isPlaying()) {
            mp.pause();
        }
    }
    public void stop(View view) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
    public void anterior(View view) {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        indiceCancion = (indiceCancion - 1 + canciones.length) % canciones.length; // Mover al índice anterior
        mp = MediaPlayer.create(this, canciones[indiceCancion]);
        mp.start();
    }
    public void siguiente(View view) {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        indiceCancion = (indiceCancion + 1) % canciones.length; // Mover al siguiente índice
        mp = MediaPlayer.create(this, canciones[indiceCancion]);
        mp.start();
    }



        public void avanzar(View view) {
        if(mp != null) {
            int currentPosition = mp.getCurrentPosition(); // Obtener la posición actual en tiempo real
            int duration = mp.getDuration(); // Obtener la duración actual de la canción
            if (currentPosition + 10000 <= duration) {
                mp.seekTo(currentPosition + 10000); // Avanzar 10 segundos
            } else {
                mp.seekTo(duration); // Ir al final
            }
        }
        }

        public void retroceder(View view) {
        if(mp != null) {
            int currentPosition = mp.getCurrentPosition();
            // Obtener la posición actual en tiempo real
            if (currentPosition - 10000 >= 0) {
                mp.seekTo(currentPosition - 10000); // Retroceder 10 segundos
            } else {
                mp.seekTo(0); // Ir al inicio
            }
        }
        }
}