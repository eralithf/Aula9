package com.example.aula;

public class Pessoas {
    int cod;
    String nome, tel, email;

    public Pessoas() {

    }

    public Pessoas(int cod, String nome, String tel, String email) {
        this.cod = cod;
        this.nome = nome;
        this.tel = tel;
        this.email = email;
    }

    public Pessoas(String nome, String tel, String email) {
        this.nome = nome;
        this.tel = tel;
        this.email = email;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}