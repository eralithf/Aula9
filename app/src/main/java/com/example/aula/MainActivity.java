package com.example.aula;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editCod, editNome, editEmail, editTel;
    Button btnSalvar, btnExcluir, btnLimpar;
    ListView lista;
    PessoasDataBase bd = new PessoasDataBase(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar views
        editCod = findViewById(R.id.PlainCod);
        editNome = findViewById(R.id.PlainNome);
        editTel = findViewById(R.id.PlainTel);
        editEmail = findViewById(R.id.PlainEmail);
        btnLimpar = findViewById(R.id.btn1);
        btnSalvar = findViewById(R.id.btn2);
        btnExcluir = findViewById(R.id.btn3);
        lista = findViewById(R.id.lista);

        // Configurar listeners
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampo();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCod.getText().toString();
                String nome = editNome.getText().toString();
                String tel = editTel.getText().toString();
                String email = editEmail.getText().toString();

                if (nome.isEmpty()) {
                    editNome.setError("Campo Obrigatório!");
                } else if (codigo.isEmpty()) {
                    bd.AddPessoa(new Pessoas(nome, tel, email));
                    Toast.makeText(MainActivity.this, "Adicionado com Sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampo();
                    listarPessoas();
                } else {
                    bd.atualizaPessoa(new Pessoas(Integer.parseInt(codigo), nome, tel, email));
                    Toast.makeText(MainActivity.this, "Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampo();
                    listarPessoas();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCod.getText().toString();
                if (codigo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Não há nada selecionado!", Toast.LENGTH_SHORT).show();
                } else {
                    Pessoas pessoa = new Pessoas();
                    pessoa.setCod(Integer.parseInt(codigo));
                    bd.ApagarPessoa(pessoa);
                    Toast.makeText(MainActivity.this, "Apagado com Sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampo();
                    listarPessoas();
                }
            }
        });

        // Configurar lista
        listarPessoas();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) lista.getItemAtPosition(position);
                String codigo = conteudo.substring(0, conteudo.indexOf("-"));
                Pessoas pessoa = bd.selecionarPessoa(Integer.parseInt(codigo));
                editCod.setText(String.valueOf(pessoa.getCod()));
                editNome.setText(pessoa.getNome());
                editTel.setText(pessoa.getTel());
                editEmail.setText(pessoa.getEmail());
            }
        });
    }

    public void listarPessoas() {
        List<Pessoas> pessoas = bd.listarTodos();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, arrayList);
        lista.setAdapter(adapter);
        for (Pessoas p : pessoas) {
            arrayList.add(p.getCod() + " - " + p.getNome());
            adapter.notifyDataSetChanged();
        }
    }

    public void limparCampo() {
        editCod.setText("");
        editNome.setText("");
        editTel.setText("");
        editEmail.setText("");
        editNome.requestFocus();
    }
}