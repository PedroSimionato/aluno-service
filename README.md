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

## 🚀 Como Rodar o Projeto (com Docker Compose)

### Pré-requisitos
*   Docker e Docker Compose instalados

### 1. Gerar o JAR da Aplicação

Antes de construir a imagem Docker da aplicação, você precisa gerar o arquivo JAR executável. Navegue até a raiz do projeto e execute:

```bash
./mvnw clean install -DskipTests
```

### 2. Iniciar os Serviços com Docker Compose

Com o JAR gerado, você pode construir as imagens Docker e iniciar todos os serviços (aplicação e banco de dados) usando o Docker Compose. Navegue até a raiz do projeto (onde o `docker-compose.yml` está localizado) e execute:

```bash
docker-compose up --build -d
```

Este comando irá:
*   Construir a imagem Docker da aplicação (`app`) usando o `Dockerfile`.
*   Subir o container do PostgreSQL (`db`).
*   Iniciar a aplicação (`app`), que se conectará ao banco de dados.

A aplicação estará disponível em: `http://localhost:8080/student-manager`

### 3. Variáveis de Ambiente do Banco de Dados

As configurações do banco de dados são definidas no `docker-compose.yml` para o serviço `app`:
*   **DB_HOST**: `db` (nome do serviço do banco de dados no Docker Compose)
*   **DB_PORT**: `5432`
*   **DB_NAME**: `mydatabase`
*   **DB_USER**: `myuser`
*   **DB_PASSWORD**: `mypassword`

Certifique-se de que sua aplicação esteja configurada para usar essas variáveis de ambiente para conexão com o banco de dados.

### 4. Documentação (Swagger)

Acesse a documentação da API em:
`http://localhost:8080/student-manager/swagger-ui/index.html`
