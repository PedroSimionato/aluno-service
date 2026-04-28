# Aluno Service

Este projeto é um microserviço para gerenciamento de alunos, desenvolvido seguindo os princípios da **Arquitetura Hexagonal (Ports and Adapters)** para garantir desacoplamento, testabilidade e facilidade de manutenção.

## 🏛️ Arquitetura

O projeto utiliza a Arquitetura Hexagonal, dividindo-se em:

*   **Domain**: O núcleo da aplicação, contendo os modelos de negócio, exceções e, principalmente, as **Ports** (interfaces). As Ports definem como o mundo externo pode interagir com a aplicação (Input) e como a aplicação interage com recursos externos (Output).
*   **Application**: Contém os serviços que implementam a lógica de negócio (Use Cases), coordenando as operações entre as portas de entrada e saída.
*   **Adapters**: Camada externa que implementa as interfaces definidas no domínio.
    *   **In (Input)**: Adaptadores que recebem chamadas externas (ex: Web/REST Controllers, DTOs).
    *   **Out (Output)**: Adaptadores que realizam chamadas para recursos externos (ex: Persistência em Banco de Dados, mensageria).

## 📁 Estrutura de Pastas

```text
src/main/java/br/com/simionato/aluno_service/
├── adapters/               # Adaptadores de entrada e saída
│   ├── in/                # Entrada (Web, Controllers, DTOs)
│   └── out/               # Saída (Persistência, Clientes HTTP)
├── application/            # Camada de Aplicação
│   └── service/           # Serviços (Implementação dos Casos de Uso)
├── domain/                 # Núcleo do Domínio (Regras de Negócio)
│   ├── model/             # Entidades de Domínio
│   ├── ports/             # Interfaces de Entrada e Saída (Ports)
│   └── exception/         # Exceções de Domínio
└── AlunoServiceApplication.java
```

## 🛠️ Tecnologias Usadas

*   **Java 21**
*   **Spring Boot 3.4.x** (Spring Web, Data JPA, Validation)
*   **PostgreSQL** (Banco de dados relacional)
*   **Lombok** (Produtividade/Redução de boilerplate)
*   **MapStruct** (Mapeamento de objetos entre camadas)
*   **Resilience4j** (Circuit Breaker para resiliência)
*   **SpringDoc OpenAPI (Swagger)** (Documentação da API)
*   **Docker** (Containerização)

## 🚀 Como Rodar o Projeto

### Pré-requisitos
*   Docker instalado
*   JDK 21
*   Maven

### 1. Subir o Banco de Dados (PostgreSQL) com Docker

Como o projeto está configurado para buscar o banco em `localhost:5432` (conforme `application.yaml`), você pode subir um container rapidamente com o seguinte comando:

```bash
docker run --name postgres-aluno -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=postgres -e POSTGRES_DB=postgres -p 5432:5432 -d postgres
```

Ou, se preferir criar um arquivo `docker-compose.yml` na raiz:

```yaml
version: '3.8'
services:
  db:
    image: postgres
    container_name: postgres-aluno
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: admin
      POSTGRES_DB: postgres
    ports:
      - "5432:5432"
```
E rodar: `docker-compose up -d`

### 2. Executar a Aplicação

Com o banco rodando, execute o comando Maven:

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080/student-manager`

### 3. Documentação (Swagger)

Acesse a documentação da API em:
`http://localhost:8080/student-manager/swagger-ui/index.html`
