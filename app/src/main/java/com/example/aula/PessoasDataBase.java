package com.example.aula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PessoasDataBase extends SQLiteOpenHelper {
    private static final int versao = 1;
    private static final String nomeBD = "bd_pessoas";

    private static final String tb_pessoas = "pessoas";
    private static final String c_cod = "cod";
    private static final String c_nome = "nome";
    private static final String c_tel = "tel";
    private static final String c_email = "email";

    public PessoasDataBase(Context context) {
        super(context, nomeBD, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tb_pessoas + "(" +
                c_cod + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                c_nome + " TEXT, " +
                c_tel + " TEXT, " +
                c_email + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tb_pessoas);
        onCreate(db);
    }

    public void AddPessoa(Pessoas pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(c_nome, pessoa.getNome());
        values.put(c_tel, pessoa.getTel());
        values.put(c_email, pessoa.getEmail());
        db.insert(tb_pessoas, null, values);
        db.close();
    }

    public void ApagarPessoa(Pessoas pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_pessoas, c_cod + " = ?", new String[]{String.valueOf((pessoa.getCod()))});
        db.close();
    }

    public Pessoas selecionarPessoa(int cod) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tb_pessoas,
                new String[]{c_cod, c_nome, c_tel, c_email},
                c_cod + " = ?",
                new String[]{String.valueOf(cod)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Pessoas pessoa1 = new Pessoas(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            );
            cursor.close();
            return pessoa1;
        }

        return null;
    }

    public List<Pessoas> listarTodos() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Pessoas> lista = new ArrayList<>();
        String query = "SELECT * FROM " + tb_pessoas;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Pessoas pessoa = new Pessoas();
                pessoa.setCod(cursor.getInt(0));
                pessoa.setNome(cursor.getString(1));
                pessoa.setTel(cursor.getString(2));
                pessoa.setEmail(cursor.getString(3));
                lista.add(pessoa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public void atualizaPessoa(Pessoas pessoa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(c_nome, pessoa.getNome());
        values.put(c_tel, pessoa.getTel());
        values.put(c_email, pessoa.getEmail());
        db.update(tb_pessoas, values, c_cod + " = ?",
                new String[]{String.valueOf(pessoa.getCod())});
        db.close();
    }
}
