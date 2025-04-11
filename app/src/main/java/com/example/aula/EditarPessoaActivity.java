package com.example.aula;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarPessoaActivity extends AppCompatActivity {

    EditText editNome, editTel, editEmail;
    Button btnSalvar, btnExcluir, btnVoltar;
    PessoasDataBase bd;
    Pessoas pessoaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pessoa);

        editNome = findViewById(R.id.editNome);
        editTel = findViewById(R.id.editTel);
        editEmail = findViewById(R.id.editEmail);
        btnSalvar = findViewById(R.id.btnSalvarEditar);
        btnExcluir = findViewById(R.id.btnExcluirEditar);
        btnVoltar = findViewById(R.id.btnVoltarEditar);

        bd = new PessoasDataBase(this);

        int cod = getIntent().getIntExtra("cod", -1);
        pessoaAtual = bd.selecionarPessoa(cod);

        if (pessoaAtual != null) {
            editNome.setText(pessoaAtual.getNome());
            editTel.setText(pessoaAtual.getTel());
            editEmail.setText(pessoaAtual.getEmail());
        }

        btnSalvar.setOnClickListener(v -> {
            pessoaAtual.setNome(editNome.getText().toString());
            pessoaAtual.setTel(editTel.getText().toString());
            pessoaAtual.setEmail(editEmail.getText().toString());
            bd.atualizaPessoa(pessoaAtual);
            Toast.makeText(this, "Pessoa atualizada com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnExcluir.setOnClickListener(v -> {
            bd.ApagarPessoa(pessoaAtual);
            Toast.makeText(this, "Pessoa excluída com sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnVoltar.setOnClickListener(v -> finish());
    }
}
