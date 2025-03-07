package com.lima.api.cine.model;

import com.lima.api.cine.enun.IdiomaFilme;

public class Filme {

    private String titulo;
    private IdiomaFilme idiomaFilme;
    private String duracao;

    public Filme(String titulo, IdiomaFilme idiomaFilme, String duracao) {
        this.titulo = titulo;
        this.idiomaFilme = idiomaFilme;
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", idiomaFilme=" + idiomaFilme +
                ", duracao='" + duracao + '\'' +
                '}';
    }
}
