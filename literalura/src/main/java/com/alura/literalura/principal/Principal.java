package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DadosGerais;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumirAPI;
import com.alura.literalura.service.ConverterDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private final String ENDERECO_BASE = "https://gutendex.com/books?search=";
    private Scanner leitura = new Scanner(System.in);
    private ConsumirAPI consumo = new ConsumirAPI();
    private ConverterDados conversor = new ConverterDados();

    // Repositórios injetados via construtor
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    // Construtor obrigatório para injeção de dependência na Main
    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) { // LOOP CORRIGIDO: Roda enquanto não for 0

            var menu = """
                    
                    ----------------------------------
                    Escolha o número da sua opção:
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                    ----------------------------------
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine(); // Limpeza de buffer

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
//                case 2:
//                    listarLivrosRegistrados();
//                    break;
//                case 3:
//                    listarAutoresRegistrados();
//                    break;
//                case 4:
//                    listarAutoresVivos();
//                    break;
//                case 5:
//                    listarLivrosPorIdioma();
//                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    // OPÇÃO 1: BUSCAR NA WEB E SALVAR

    private void buscarLivroPeloTitulo() {
        System.out.println("Qual livro você deseja pesquisar?");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO_BASE + nomeLivro.replace(" ", "+"));
        DadosGerais dadosGerais = conversor.obterDados(json, DadosGerais.class);

        if (dadosGerais.resultados() != null && !dadosGerais.resultados().isEmpty()) {
            DadosGerais.DadosLivro dadosLivro = dadosGerais.resultados().get(0);

            // Validação: O livro tem autor?
            if (dadosLivro.autores().isEmpty()) {
                System.out.println("Livro sem autor definido na API. Não será salvo.");
                return;
            }

            // 1. Lógica do Autor (Verifica duplicidade)
            DadosGerais.DadosAutor dadosAutor = dadosLivro.autores().get(0);
            Autor autor = new Autor(dadosAutor);

            // Busca no banco se já existe
            Autor autorBanco = autorRepository.findByNome(autor.getNome());

            if (autorBanco != null) {
                // Se existe, usa o do banco (que tem ID)
                autor = autorBanco;
            } else {
                // Se não existe, salva o novo
                autor = autorRepository.save(autor);
            }

            // 2. Salva o Livro vinculado ao Autor
            // Importante: O construtor do Livro deve ter "this.autor = autor"
            Livro livro = new Livro(dadosLivro, autor);

            try {
                // Verifica se o livro já não está salvo (opcional, mas bom ter)
                // Se quiser permitir duplicados, pode tirar esse if
                livroRepository.save(livro);
                System.out.println("Livro salvo com sucesso!");
                System.out.println(livro);

            } catch (Exception e) {
                System.out.println("Erro ao salvar o livro: " + e.getMessage());
            }

        } else {
            System.out.println("Livro não encontrado na API.");
        }
    }



}