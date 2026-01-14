package com.alura.literalura.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer numeroId;
    private String titulo;
    private String idioma;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro(){}

    public Livro(DadosGerais.DadosLivro dadosLivro, Autor autor) {
        this.titulo = dadosLivro.titulo();
        this.numeroId = dadosLivro.numeroId();
        this.autor = autor;

        if (dadosLivro.idioma() != null && !dadosLivro.idioma().isEmpty()) {

            this.idioma = dadosLivro.idioma().get(0);
        } else {
            this.idioma = "Idioma desconhecido";
        }
    }


    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(Integer numeroId) {
        this.numeroId = numeroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return
                ", numeroId=" + numeroId +
                ", titulo=" + titulo + '\'' +
                ", autor:" + autor + '\'' +
                ", idioma='" + idioma + '\'';
    }
}

