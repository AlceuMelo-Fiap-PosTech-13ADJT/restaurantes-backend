# Restaurantes Backend - Tech Challenge Fase 1

![Java](https://img.shields.io/badge/java-%23F29111.svg?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236db33f.svg?style=flat&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%233E6E93.svg?style=flat&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%231D63ED.svg?style=flat&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/swagger-%23173647.svg?style=flat&logo=swagger&logoColor=white)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue)](https://opensource.org/licenses/MIT)

> **Pós Tech em Arquitetura e Desenvolvimento em JAVA - FIAP**  
> Projeto realizado como requisito do __Tech Challenge__ da **Fase 1**  
> Turma **13ADJT**  
> Grupo **30**  
> ... Integrantes (1) : Alceu Luís de Andrade Melo

Este projeto consiste no backend de um sistema de gestão para um grupo de restaurantes. O foco desta primeira fase é a estruturação robusta do domínio de "Usuários", permitindo a gestão de Clientes e Proprietários de Restaurantes, garantindo segurança, padronização e escalabilidade.

## 🚀 Tecnologias e Ferramentas

- **Linguagem**: Java 17+
- **Framework**: Spring Boot 3+
- **Persistência**: JdbcTemplate (Abordagem SQL pura, sem JPA)
- **Bancos de Dados**: MySQL 8.0 (Ambiente de Produção) e H2 (Ambiente de Teste/Dev)
- **Conteinerização**: Docker e Docker Compose
- **Documentação**: Swagger/OpenAPI
- **Testes**: Postman Collection

## 📂 Estrutura de Diretórios

```text
restaurantes-backend/
├── collection/                 # Collection do postman: testes dos endpoints
├── src/main/
│   ├── java/br/com/fiap/fase1tc/restaurantes_backend/
│   │   ├── config/             # Configurações globais (Swagger, Security, etc.)
│   │   ├── controllers/        # Camada de Entrada: Define os endpoints e mapeia requisições
│   │   │   └── handlers/       # Tratamento global de exceções (ProblemDetail RFC 7807)
│   │   ├── dtos/               # Data Transfer Objects: Objetos para entrada e saída de dados da API
│   │   ├── entities/           # Modelagem de Dados: Representação das tabelas do banco
│   │   │   └── enums/          # Enumerações para tipos de perfil e status
│   │   ├── factories/          # Padrões Criacionais: Lógica de criação de objetos complexos
│   │   ├── mappers/            # Conversão de dados entre Entidades e DTOs
│   │   ├── repositories/       # Camada de Acesso a Dados: Consultas via JdbcTemplate
│   │   └── services/           # Camada de Negócio: Onde reside a regra de ouro e validações
│   │       └── exceptions/     # Exceções customizadas
│   └── resources/              # Propriedades da aplicação e scripts de inicialização do BD
└── swagger/                    # Documentação Swagger/OpenAPI
```

## 🏗️ Arquitetura do Sistema

O projeto utiliza uma **Arquitetura em Camadas (Layered Architecture)** para garantir a separação de responsabilidades e facilidade de manutenção:

```text
[Cliente/Postman]
       ↓
[Controller] (Validação de Request -> Mapeamento para DTO)
       ↓
[Service] (Regras de Negócio -> Validação de Unicidade -> Auditoria)
       ↓
[Repository] (Queries SQL -> Mapeamento para Entity)
       ↓
[Banco de Dados] (MySQL / H2)
```

## 🛠️ Instalação e Execução

### Pré-requisitos
- Docker e Docker Compose instalados
- JDK 21

### Execução via Docker (Produção)
Para subir a aplicação com o banco de dados MySQL configurado:
```bash
docker compose up --build
```

### Execução Local (Desenvolvimento)
Para rodar a aplicação utilizando o banco H2 em memória:
```bash
# Execute via Maven
./mvnw -D"spring-boot.run.profiles=dev" spring-boot:run
```

## ⚙️ Configurações de Ambiente

- **Ambiente de Desenvolvimento (Dev)**: Utiliza `application-dev.properties` com banco de dados **H2**.
- **Ambiente de Produção (Prod)**: Utiliza `application-prod.properties` com banco de dados **MySQL**.
- **Auditoria**: Implementada via campos `created_at` e `updated_at` para rastreabilidade total das alterações.

## 🧩 Padrões de Projeto e SOLID

### Padrões Criacionais
- **Factory Method**: Implementado em `UsuarioFactory.java` para centralizar a criação de diferentes tipos de `Usuario` (Cliente vs Proprietário).
- **Singleton**: Utilizado através da gestão de Beans do Spring para garantir instâncias únicas de serviços e repositórios.

### Padrões Estruturais e Comportamentais
- **Adapter**: Utilizado na camada de Repositório para isolar a complexidade das consultas SQL.
- **Strategy / Chain of Responsibility**: Aplicados na `UsuarioService` para gerenciar diferentes fluxos de validação e persistência.

### Conformidade com SOLID
- **S (Single Responsibility)**: Cada classe possui uma única responsabilidade (ex: `UsuarioMapper` apenas converte dados).
- **O (Open/Closed)**: A `UsuarioFactory` permite estender tipos de usuários sem modificar as regras de negócio existentes.
- **L (Liskov Substitution)**: `Cliente` e `Proprietario` herdam de `Usuario` e podem ser tratados polimorficamente.
- **I (Interface Segregation)**: O uso de `IUsuarioRepository` garante contratos específicos e limpos.
- **D (Dependency Inversion)**: O Service depende da abstração (Interface) do Repositório, não da implementação concreta.

## ⚠️️️ Tratamento de Erros (RFC 7807)

Todas as falhas são capturadas pelo `ControllerExceptionHandler` e retornadas no formato `ProblemDetail`:

| Exceção | Status HTTP | Descrição |
|-----------|-------------|------------|
| `EmailJaCadastradoException` | 409 Conflict | E-mail já existente no sistema. |
| `LoginJaCadastradoException` | 409 Conflict | Login já utilizado por outro usuário. |
| `SenhaEConfirmacaoDiferentesException` | 409 Conflict | Divergência entre senha e confirmação. |
| `EntidadeNaoEncontradaException` | 404 Not Found | O recurso solicitado não existe. |
| `ParametroFaltandoException` | 400 Bad Request | Falta de campos obrigatórios no JSON. |
| `CredenciaisIncorretasException` | 403 Forbidden | Login ou senha inválidos. |
| `FalhaEmManipularUsuarioException` | 400 Bad Request | Erro interno ao processar dados do usuário. |

## 📖 Documentação e Testes

- **Swagger UI**: 
  - *Online*: Acesse [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html) para explorar todos os endpoints e exemplos de sucesso/falha.
  - *Em arquivo*: Documentação Swagger completa disponível no diretório `/swagger`.
- **Postman Collection**: 
  - *Em arquivo*: Coleção completa de testes disponível no diretório `/collection`.

---

## ©️ Licença

O código deste projeto é disponibilizado sob a [licença MIT](https://opensource.org/licenses/MIT).

*© 2026 Alceu Luís de Andrade Melo*