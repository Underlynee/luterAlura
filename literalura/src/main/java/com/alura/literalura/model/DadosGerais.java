package com.alura.literalura.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosGerais(
        @JsonAlias("results") List<DadosLivro> resultados
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DadosLivro(
            @JsonAlias("title") String titulo,
            @JsonAlias("id") Integer numeroId,
            @JsonAlias("languages") List<String> idioma,
            @JsonAlias("authors") List<DadosAutor> autores // Lista de Autores
    ) {}

    // NÍVEL 3: O Autor (Fica DENTRO do Gerais também, para ser usado pelo Livro)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DadosAutor(
            @JsonAlias("name") String nome, // Mudei para 'nome' pra não confundir com o objeto
            @JsonAlias("birth_year") Integer anoNascimento,
            @JsonAlias("death_year") Integer anoFalecimento
    ) {}
}