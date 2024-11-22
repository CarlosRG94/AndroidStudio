package com.example.pruebas;

import android.view.View;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private TextView tsaludo;
    private EditText editText;
    private TextView textView2, texto1, texto2;
    private Button button, button2;
    private RadioGroup grupo1, grupo2;
    private RadioButton radio1, radio2, radio3, radio4, radio5, radio6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tsaludo = findViewById(R.id.tsaludo);
        editText = findViewById(R.id.editText);
        tsaludo.setText("Holaaa");
        // Agregar un TextWatcher para escuchar cambios de texto en editText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Cambiar el texto de tsaludo cada vez que el usuario escriba algo
                tsaludo.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No se necesita implementar
            }
        });
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);

        grupo1 = findViewById(R.id.grupo1);
        grupo2 = findViewById(R.id.grupo2);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radio5 = findViewById(R.id.radio5);
        radio6 = findViewById(R.id.radio6);

        grupo1.setOnCheckedChangeListener(this);
        grupo2.setOnCheckedChangeListener(this);
        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);



    }
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int groupId = group.getId();

            if (groupId == R.id.grupo1) {
                if (checkedId == R.id.radio1) {
                    texto1.setText("radio1");
                } else if (checkedId == R.id.radio2) {
                    texto1.setText("radio2");
                } else if (checkedId == R.id.radio3) {
                    texto1.setText("radio3");
                }
            } else if (groupId == R.id.grupo2) {
                if (checkedId == R.id.radio4) {
                    texto2.setText("radio4");
                } else if (checkedId == R.id.radio5) {
                    texto2.setText("radio5");
                } else if (checkedId == R.id.radio6) {
                    texto2.setText("radio6");
                }
            }

        }

            @Override
        public void onClick(View v){
        if (v.getId() == R.id.button){
            textView2.setText(R.string.pulsado1);
        } else if (v.getId() == R.id.button2){
            textView2.setText(R.string.pulsado2);
        }
        }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
