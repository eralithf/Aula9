package com.example.aula;

import android.app.people.PeopleManager;
import android.content.Context;
import  android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;
public class PessoasDataBase extends SQLiteOpenHelper {
    private static final int versao = 1;
    private static final String nomeBD = "bd_pessoas";

    public PessoasDataBase(Context context) {
        super(context, nomeBD, null, versao);
    }

    public PessoasDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


























}
