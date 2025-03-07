package com.lima.api.cine.model;

import com.lima.api.cine.enun.StatusSala;

import java.util.ArrayList;
import java.util.List;

public class Sala {

    private String nome;
    private List<Assento> assentos = new ArrayList<>();
    private StatusSala status;

    public Sala(String nome){
        this.nome = nome;
        this.status = StatusSala.FECHADA;
        criarAssentos(10);
    }

    public List<Assento> getAssentos() {
        return assentos;
    }

    public void abrirSala(){
        this.status = StatusSala.ABERTA;
    }

    public void fecharSala(){
        this.status = StatusSala.FECHADA;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "nome='" + nome + '\'' +
                ", assentos=" + assentos +
                ", status=" + status +
                '}';
    }

    private void criarAssentos(int quantidadeAssentos) {
        for(int i = 1; i <= quantidadeAssentos; i++){
            this.assentos.add(new Assento(i));
        }
    }

}
