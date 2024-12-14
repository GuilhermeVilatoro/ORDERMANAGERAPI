# **SpringBootApiGerenciamentoPedidos**

## **API REST SPRING BOOT PARA GERENCIAR PEDIDOS E INTEGRAÇÃO EXTERNA**

---

### **Objetivo**

Criar um serviço REST responsável por gerenciar pedidos recebidos de um sistema externo (**Produto Externo A**) e enviá-los a outro sistema externo (**Produto Externo B**) após o processamento.

---

## **Requisitos**

### **Funcionalidades Implementadas:**

- **POST `/api/orders`**  
  Receber e armazenar um pedido enviado pelo sistema **Produto Externo A**.

- **GET `/api/orders`**  
  Listar todos os pedidos processados, com suporte a **filtro** e **paginação**.

---

## **Detalhes Técnicos**

### **Concorrência e Controle de Dados Duplicados**
- Implementado **controle de concorrência** usando **Optimistic Locking** através da anotação `@Version` do JPA.
- Tratamento para evitar **pedidos duplicados** baseado no identificador único dos pedidos.

### **Desempenho e Disponibilidade**
- Utilizado **@Cacheable** do Spring Framework para **cachear os pedidos** e garantir maior desempenho e disponibilidade do serviço, principalmente sob alta volumetria.

### **Testes de Desempenho**
- Realizados testes de **carga com JMeter** para avaliar o comportamento do sistema e verificar se o banco de dados escolhido pode lidar com a volumetria esperada sem gargalos.

---

## **Tecnologias Usadas**

- **Java 17**
- **Spring Boot** (Web, Data JPA, Cache)
- **Liquibase** (versionamento do banco de dados)
- **Gradle** (gerenciamento de dependências)
- **PostgreSQL** (banco de dados)
- **H2** (banco em memória para testes)
- **JMeter** (testes de carga)
- **JUnit** (testes unitários)
- **Postman** (testes manuais dos endpoints)

---

## **Validações Implementadas**

- **Pedidos Duplicados**: Tratamento para evitar armazenamento de pedidos repetidos.
- **Concorrência**: Controle com Optimistic Lock para evitar inconsistência em ambientes multi-thread.
- **Cache**: Aplicado cache para otimizar a leitura dos dados e reduzir a carga no banco.
- **Alta Volumetria**: Testes de carga validados para garantir a performance do sistema.

---

## **Testes**

### **Testes Funcionais**
- Realizados com **Postman** para validar os endpoints.  
  - **Collection do Postman** disponível no arquivo:  
    `ORDERMANAGER.postman_collection.json`.

### **Testes Unitários**
- Implementados com **JUnit**.

### **Testes de Carga**
- Testes executados com **JMeter** para garantir que o sistema suporte grandes volumes de pedidos sem degradação no desempenho.

---

## **Melhorias Futuras**

- Implementar autenticação com **JWT**.
- Deploy em ambiente cloud como **Heroku** ou **AWS**.
- Adicionar métricas de monitoramento com **Spring Actuator**.