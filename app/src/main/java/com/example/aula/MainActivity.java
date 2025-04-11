package com.example.aula;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editNome, editTel, editEmail;
    Button btnSalvar, btnLimpar;
    ListView lista;
    PessoasDataBase bd;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    List<Pessoas> listaPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.PlainNome);
        editTel = findViewById(R.id.PlainTel);
        editEmail = findViewById(R.id.PlainEmail);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        lista = findViewById(R.id.lista);

        bd = new PessoasDataBase(this);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString();
            String tel = editTel.getText().toString();
            String email = editEmail.getText().toString();

            if (nome.isEmpty()) {
                Toast.makeText(this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
                return;
            }

            bd.AddPessoa(new Pessoas(nome, tel, email));
            Toast.makeText(this, "Pessoa adicionada com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();
            listarPessoas();
        });

        btnLimpar.setOnClickListener(v -> limparCampos());

        lista.setOnItemClickListener((parent, view, position, id) -> {
            Pessoas pessoaSelecionada = listaPessoas.get(position);
            Intent intent = new Intent(MainActivity.this, EditarPessoaActivity.class);
            intent.putExtra("cod", pessoaSelecionada.getCod());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarPessoas();
    }

    private void listarPessoas() {
        listaPessoas = bd.listarTodos();
        arrayList = new ArrayList<>();

        for (Pessoas p : listaPessoas) {
            arrayList.add(p.getCod() + " - " + p.getNome());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        lista.setAdapter(adapter);
    }

    private void limparCampos() {
        editNome.setText("");
        editTel.setText("");
        editEmail.setText("");
        editNome.requestFocus();
    }
}
