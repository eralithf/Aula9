package com.example.aula;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editCod, editNome, editEmail, editTel;
    Button btnSalvar, btnExcluir, btnLimpar;
    ListView lista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editCod = findViewById(R.id.PlainCod);
        editNome = findViewById(R.id.PlainNome);
        editTel = findViewById(R.id.PlainTel);
        editEmail = findViewById(R.id.PlainEmail);
        btnLimpar = findViewById(R.id.btn1);
        btnSalvar = findViewById(R.id.btn2);
        btnExcluir = findViewById(R.id.btn3);
        lista = findViewById(R.id.lista);


    }
}