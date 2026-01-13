package com.alura.literalura.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "autores")

public class Autor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true) // O nome não pode repetir
        private String nome;
        private Integer anoNascimento;
        private Integer anoFalecimento;

        @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Livro> livros = new ArrayList<>();


        public Autor(){}

        public Autor(DadosGerais.DadosAutor dadosAutor) {
            this.nome = dadosAutor.nome();
            this.anoNascimento = dadosAutor.anoNascimento();
            this.anoFalecimento = dadosAutor.anoFalecimento();
        }



        public void setLivros(List<Livro> livros) {
            this.livros = livros;
        }

    public List<Livro> getLivros() {
        return livros;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return
                ", nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento +
                ", livros=" + livros;
    }
}



