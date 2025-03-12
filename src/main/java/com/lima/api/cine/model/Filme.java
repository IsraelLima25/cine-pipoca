package com.lima.api.cine.model;

import com.lima.api.cine.controller.response.FilmeResponse;
import com.lima.api.cine.enums.TipoIdiomaFilme;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

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
        this.uuid = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDuracao() {
        return duracao;
    }

    public TipoIdiomaFilme getIdioma() {
        return idioma;
    }

    public FilmeResponse toRepresentacaoView(){
        return new FilmeResponse(uuid, titulo, idioma, duracao);
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
