# SpringBootApiCrudPessoa

# API REST SPRING BOOT PARA CADASTRAR PESSOAS e SEUS CONTATOS

# Sistema criado para atender as seguintes necessidades:

### Objetivo

Criar uma API Rest de um cadastro de Pessoas, utilizando Spring Boot e Java

### Requisitos:

* Possuir ao menos os endpoints: GET(Buscar uma única Pessoa), GET (Busca paginada opção de filtro para retornar várias pessoas), POST, PUT, DELETE
* O cadastro de pessoa deve ter os campos: Id, Nome, CPF, Data de nascimento.
* A pessoa deve possuir uma lista de contatos (relacionamento um para muitos) com os campos: Id, Nome, Telefone e Email.
* Os dados devem ser persistidos utilizando um banco de dados relacional.

### Validações:

* Todos os campos são obrigatórios, tanto da pessoa como do contato
* A Pessoa deve possuir ao menos um contato
* O CPF deve ser um CPF válido
* A Data de nascimento não pode ser uma data futura
* Validar sintaxe do email do contato

### Requisitos técnicos:

* Deverão ser criados testes unitários
* Publicar o código em repositório público

### É opcional e será um diferencial:

* Publicar a aplicação na internet utilizando algum provedor, para que possa ser acessado sem necessidade de rodar o projeto local
### Foi usado o provedor HEROKU para publicar a API.

## Informações para teste da API:

###  1) Postman
* Os testes também poderão ser realizados via Postman com as suas respectivas rotas.

###  4) HEROKU
* https://spring-boot-crud-pessoa-app.herokuapp.com/

# Tecnologias usadas

* Spring Boot
* Spring Data
* Maven
* JPA
* Injeção de dependências
* Testes unitários com JUNIT
* Banco de dados H2 e POSTGRESSQL
* REST
* HEROKU
