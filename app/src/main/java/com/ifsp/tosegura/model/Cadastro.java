package com.ifsp.tosegura.model;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ifsp.tosegura.R;

public class Cadastro{

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("senha")
    @Expose
    private String senha;

    @SerializedName("nome")
    @Expose
    private String nome;

    //GET AND SET
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //CONSTRUTOR
    public Cadastro(Integer id, String email, String senha, String nome) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    public Cadastro(){}

}
