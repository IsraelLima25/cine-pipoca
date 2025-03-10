package com.lima.api.cine.model;

import com.lima.api.cine.enums.TipoIdiomaFilme;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoIdiomaFilme idioma;

    @Column(name = "duracao")
    private String duracao;

    @Deprecated
    public Filme(){}

    public Filme(String titulo, TipoIdiomaFilme idioma, String duracao) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", idiomaFilme=" + idioma +
                ", duracao='" + duracao + '\'' +
                '}';
    }
}
