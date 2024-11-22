package com.example.conversionnumero;
import android.view.View;
import android.view.Window;
import android.os.Bundle;
import android.os.Build;
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

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private EditText textoeditar;
    private Button button;
    private RadioGroup radioGroup;
    private RadioButton radioButton, radioButton2, radioButton3, radioButton4;
    private String resbinario = "", resoctal="", resdecimal="", reshexadecimal="";
    private TextView converbi, converoct, converdec, converhex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    textoeditar = findViewById(R.id.textoeditar);
    radioGroup = findViewById(R.id.radioGroup);
    radioGroup.setOnCheckedChangeListener(this);
    converbi = findViewById(R.id.converbi);
    converoct = findViewById(R.id.converoct);
    converdec = findViewById(R.id.converdec);
    converhex = findViewById(R.id.converhex);
    button = findViewById(R.id.button);

    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (resdecimal.equals("decimal")) {
                String texto = textoeditar.getText().toString();
                String resultadobi = decimalABinario(texto);
                String resultadooct = decimalAoctal(texto);
                String resultadohex = decimalAhexadecimal(texto);
                converbi.setText(resultadobi);
                converoct.setText(resultadooct);
                converdec.setText(texto);
                converhex.setText(resultadohex);


            } else if (resbinario.equals("binario")) {
                String texto = textoeditar.getText().toString();
                String resultadodec = binarioAdecimal(texto);
                String resultadooct = decimalAoctal(resultadodec);
                String resultadohex = decimalAhexadecimal(resultadodec);
                converbi.setText(texto);
                converoct.setText(resultadooct);
                converdec.setText(resultadodec);
                converhex.setText(resultadohex);

            } else if (resoctal.equals("octal")) {
                String texto = textoeditar.getText().toString();
                String resultadodec = octalAdecimal(texto);
                String resultadobi = decimalABinario(resultadodec);
                String resultadohex = decimalAhexadecimal(resultadodec);
                converbi.setText(resultadobi);
                converoct.setText(texto);
                converdec.setText(resultadodec);
                converhex.setText(resultadohex);

            } else if (reshexadecimal.equals("hexadecimal")) {
                String texto = textoeditar.getText().toString();
                String resultadodec = hexadecimalAdecimal(texto);
                String resultadooct = decimalAoctal(resultadodec);
                String resultadobi = decimalABinario(resultadodec);
                converbi.setText(resultadobi);
                converoct.setText(resultadooct);
                converdec.setText(resultadodec);
                converhex.setText(texto);
            }
        }
    });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (group.getId() == R.id.radioGroup) {
            resbinario = "";
            resoctal = "";
            resdecimal = "";
            reshexadecimal = "";
            if (checkedId == R.id.radioButton) {
                resbinario ="binario";
            } else if (checkedId == R.id.radioButton2) {
                resoctal = "octal";
            } else if (checkedId == R.id.radioButton3) {
                resdecimal = "decimal";
            } else if (checkedId == R.id.radioButton4) {
                reshexadecimal = "hexadecimal";
            }
        }

    }


    public String decimalAhexadecimal(String texto){
        int numero = Integer.parseInt(texto);
        String hexadecimal = Integer.toHexString(numero);
        return hexadecimal;
    }
    public String decimalAoctal(String texto){
        int numero = Integer.parseInt(texto);
        String octal = Integer.toOctalString(numero);
        return octal;
    }
    public String decimalABinario(String texto){
        int numero = Integer.parseInt(texto);
        String binario = Integer.toBinaryString(numero);
        return binario;
    }
    public String binarioAdecimal(String texto){
        int numero = Integer.parseInt(texto, 2);
        String decimal = Integer.toString(numero);
        return decimal;

    }
    public String octalAdecimal(String texto){
        int numero = Integer.parseInt(texto, 8);
        String decimal = Integer.toString(numero);
        return decimal;
        }
    public String hexadecimalAdecimal(String texto) {
        int numero = Integer.parseInt(texto, 16);
        String decimal = Integer.toString(numero);
        return decimal;
    }
}