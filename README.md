# 📚 LiterAlura - Catálogo de Livros


![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green?style=for-the-badge&logo=spring)
![Postgres](https://img.shields.io/badge/PostgreSQL-blue?style=for-the-badge&logo=postgresql)

## 📝 Descrição

O **LiterAlura** é uma aplicação de console desenvolvida em Java com Spring Boot que tem como objetivo consumir a API do **Gutendex** para buscar e gerenciar dados de livros e autores.

O projeto permite a persistência de dados em um banco relacional (PostgreSQL), realizando operações complexas de busca e filtragem, como identificar autores vivos em determinado ano e listar livros por idioma.

## 🔨 Funcionalidades

O projeto oferece um menu interativo com as seguintes opções:

1.  **Buscar livro pelo título**: Conecta à API Gutendex, busca os dados, converte para objetos Java e salva no banco de dados (verificando duplicidade de autores).
2.  **Listar livros registrados**: Exibe todos os livros já salvos no banco local.
3.  **Listar autores registrados**: Exibe os autores salvos e seus respectivos livros.
4.  **Listar autores vivos em um determinado ano**: Realiza uma consulta complexa no banco para filtrar autores com base nas datas de nascimento e falecimento.
5.  **Listar livros em um determinado idioma**: Filtra os livros salvos pelo idioma escolhido (PT, EN, ES, FR).

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot** (Framework principal)
* **Spring Data JPA** (Para persistência e consultas ao banco)
* **PostgreSQL** (Banco de dados)
* **Jackson** (Desserialização de JSON)
* **Maven** (Gerenciamento de dependências)
* **Gutendex API** (Fonte dos dados)

## 🧠 Aprendizados e Desafios

Durante o desenvolvimento deste projeto, foram aplicados conceitos fundamentais:

* **Consumo de API:** Uso da classe `HttpClient` e manipulação de JSON.
* **Modelagem de Dados:** Criação de `Records` (DTOs) para leitura da API e `Classes` (Entities) para o banco de dados.
* **Relacionamentos JPA:** Implementação de `@ManyToOne` (Livros) e `@OneToMany` (Autores), entendendo a importância do `CascadeType` e `FetchType`.
* **Consultas Personalizadas:** Uso de **Derived Queries** (ex: `findByIdioma`) e **JPQL** (`@Query`) para buscas complexas de datas.
* **Tratamento de Erros:** Prevenção de duplicidade de registros e tratamento de dados nulos da API.

## 🚀 Como executar o projeto

### Pré-requisitos

* Java 17 instalado
* PostgreSQL instalado e rodando
* Maven

### Passo a passo

1.  Clone o repositório:
    ```bash
    git clone https://github.com/Underlynee/luterAlura.git
    ```
2.  Configure o banco de dados no arquivo `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    ```
3.  Execute a classe `LiteraluraApplication.java` na sua IDE favorita (IntelliJ, Eclipse).

## 👤 Autor

Desenvolvido por **[RENNAN TAVARES]**.

Entre em contato!
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/rennan-tavares-)
