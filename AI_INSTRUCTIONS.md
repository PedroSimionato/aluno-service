# 📚 aluno-service — Diretrizes para Assistentes de IA (Gemini)

## 🎯 Objetivo do Projeto

Este projeto tem como finalidade o desenvolvimento de um microsserviço chamado **aluno-service**, responsável pelo gerenciamento de alunos dentro de um sistema acadêmico.

O objetivo principal NÃO é apenas entregar funcionalidades, mas sim:

* Aprender e aplicar **Arquitetura Hexagonal (Ports and Adapters)**
* Desenvolver um domínio rico e desacoplado
* Garantir separação clara de responsabilidades
* Construir um sistema escalável e de fácil manutenção

---

## 🧠 Visão Arquitetural

O projeto segue os princípios da **Arquitetura Hexagonal**, onde o domínio é o centro da aplicação.

### Estrutura base:

```
domain/
  ├── model/
  ├── ports/
  │     ├── in/
  │     └── out/
  ├── exception/

application/
  └── service/

adapters/
  ├── in/
  └── out/
```

---

## 🧱 Princípios Fundamentais

### 1. O domínio é independente

* Não deve conter dependências de frameworks (Spring, JPA, etc.)
* Não deve conhecer banco de dados
* Não deve conhecer controllers

---

### 2. Entidades possuem identidade

* Entidades (como `Aluno`) possuem `id`
* Value Objects (como `Address`) NÃO possuem identidade

---

### 3. Regras de negócio ficam no domínio

* Validações críticas devem estar no domínio
* O sistema não deve depender apenas de validações externas (ex: `@Valid`)

---

### 4. Ports definem contratos

* `ports.in` → define o que o sistema faz (use cases)
* `ports.out` → define o que o sistema precisa (ex: repositórios)

---

### 5. Adapters implementam detalhes

* Controllers, banco de dados, integrações externas
* Devem depender do domínio, nunca o contrário

---

## ⚠️ Regras IMPORTANTES para o Assistente (Gemini)

### ❗ NÃO ALTERAR ARQUIVOS SEM AUTORIZAÇÃO

Você **NÃO deve modificar, criar ou deletar nenhum arquivo do projeto automaticamente**.

Só é permitido alterar código quando o usuário solicitar explicitamente, por exemplo:

> "pode alterar esse código"
> "gere a implementação completa"
> "crie esse arquivo"

Caso contrário:

* Apenas **sugira melhorias**
* Apenas **explique conceitos**
* Apenas **mostre exemplos isolados**

---

### ❗ NÃO QUEBRAR A ARQUITETURA

Você deve evitar:

* Inserir anotações do Spring (`@Entity`, `@Service`, etc.) no domínio
* Acessar banco diretamente no domínio
* Misturar camadas (controller chamando repository direto)
* Criar dependências do domínio para adapters

---

### ❗ NÃO SIMPLIFICAR DE FORMA ERRADA

Evite:

* Transformar entidades em DTOs
* Remover regras de negócio do domínio
* Centralizar lógica em controllers

---

## 🧠 Expectativa de Comportamento

O assistente deve:

* Sugerir soluções alinhadas com **Arquitetura Hexagonal**
* Priorizar **boas práticas de DDD**
* Questionar decisões que quebrem o design
* Explicar o "porquê" das sugestões

---

## 🚀 Objetivo Final

Construir um microsserviço:

* Desacoplado
* Testável
* Escalável
* Com domínio rico e consistente

E, principalmente, consolidar conhecimento sólido em arquitetura de software.

---
