# GymSys

API REST para gerenciamento de academias e estúdios de artes marciais, desenvolvida com Spring Boot e arquitetura em camadas.

## Sobre o Projeto

O GymSys uma API REST que centraliza as operações administrativas de uma academia: cadastro de alunos, matrículas, planos, modalidades, graduações, controle financeiro e frequência.

## Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot**: framework principal da aplicação
- **Spring Data JPA / Hibernate**: persistência de dados
- **Spring Data JPA Specifications**: filtros dinâmicos de busca (ex.: alunos)
- **Bean Validation**: validação de DTOs

### Banco de Dados
- **PostgreSQL 16**: banco de dados relacional
- **Flyway**: migrations para controle do schema

## Funcionalidades

### Alunos
- Cadastro completo (dados pessoais, contato e endereço)
- Listagem paginada com filtros dinâmicos (nome, e-mail, celular, cidade, estado)
- Atualização e remoção

### Modalidades e Graduações
- CRUD de modalidades (ex.: Jiu-Jitsu, Muay Thai)
- CRUD de graduações vinculadas a uma modalidade (ex.: faixas)

### Planos
- CRUD de planos, vinculados a uma modalidade, com valor mensal

### Matrículas
- CRUD de matrículas de alunos, com dia de vencimento e status (`ATIVA`, `ENCERRADA`, `CANCELADA`)
- Associação de matrícula a múltiplas modalidades (`matriculas-modalidades`), cada uma com sua própria graduação e plano
- Histórico de evolução de graduação por matrícula/modalidade

### Financeiro
- CRUD de faturas por matrícula, com status (`ABERTA`, `PAGA`, `CANCELADA`, `VENCIDA`)

### Frequência
- Registro de entrada e saída de alunos (assiduidade)

### Relatórios
- Faturamento mensal
- Distribuição de alunos por cidade
- Faturas em aberto

## Pré-requisitos

- Java 17+
- Docker e Docker Compose
- Git

## Como Executar

### Passo a Passo

Clone o repositório:
```bash
git clone https://github.com/Matheuslpr/GymSys.git
cd GymSys
```

Suba o banco de dados com Docker:
```bash
docker compose up -d
```

Execute a aplicação:
```bash
./mvnw spring-boot:run
```

Ou, no Windows:
```bash
mvnw.cmd spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## Documentação da API

Com a aplicação em execução, a documentação interativa (Swagger) fica disponível em:

http://localhost:8080/swagger-ui/index.html

## Endpoints

### Alunos
- `POST /alunos` — Cadastrar aluno
- `GET /alunos` — Listar alunos (paginado, com filtros)
- `GET /alunos/{id}` — Buscar aluno por ID
- `PUT /alunos/{id}` — Atualizar aluno
- `DELETE /alunos/{id}` — Remover aluno

### Modalidades
- `POST /modalidades` — Criar modalidade
- `GET /modalidades` — Listar modalidades
- `GET /modalidades/{id}` — Buscar modalidade por ID
- `PUT /modalidades/{id}` — Atualizar modalidade
- `DELETE /modalidades/{id}` — Remover modalidade

### Graduações
- `POST /graduacoes` — Criar graduação
- `GET /graduacoes` — Listar graduações
- `GET /graduacoes/{id}` — Buscar graduação por ID
- `PUT /graduacoes/{id}` — Atualizar graduação
- `DELETE /graduacoes/{id}` — Remover graduação

### Planos
- `POST /planos` — Criar plano
- `GET /planos` — Listar planos
- `GET /planos/{id}` — Buscar plano por ID
- `PUT /planos/{id}` — Atualizar plano
- `DELETE /planos/{id}` — Remover plano

### Matrículas
- `POST /matriculas` — Criar matrícula
- `GET /matriculas` — Listar matrículas
- `GET /matriculas/{id}` — Buscar matrícula por ID
- `PUT /matriculas/{id}` — Atualizar matrícula
- `DELETE /matriculas/{id}` — Remover matrícula

### Matrículas x Modalidades
- `POST /matriculas-modalidades` — Associar modalidade/plano/graduação a uma matrícula
- `GET /matriculas-modalidades` — Listar associações
- `GET /matriculas-modalidades/{id}` — Buscar associação por ID
- `GET /matriculas-modalidades/{id}/historico-graduacoes` — Histórico de evolução de graduação
- `PUT /matriculas-modalidades/{id}` — Atualizar associação
- `DELETE /matriculas-modalidades/{id}` — Remover associação

### Faturas
- `POST /faturas-matriculas` — Criar fatura
- `GET /faturas-matriculas` — Listar faturas
- `GET /faturas-matriculas/{id}` — Buscar fatura por ID
- `PUT /faturas-matriculas/{id}` — Atualizar fatura
- `DELETE /faturas-matriculas/{id}` — Remover fatura

### Assiduidade
- `POST /assiduidades` — Registrar entrada/saída
- `GET /assiduidades` — Listar registros
- `GET /assiduidades/{id}` — Buscar registro por ID
- `PUT /assiduidades/{id}` — Atualizar registro
- `DELETE /assiduidades/{id}` — Remover registro

### Relatórios
- `GET /relatorios/faturamento-mensal` — Faturamento agrupado por mês
- `GET /relatorios/alunos-por-cidade` — Quantidade de alunos por cidade
- `GET /relatorios/faturamento-em-aberto` — Faturas em aberto

## Docker

O `docker-compose.yml` sobe apenas o banco de dados PostgreSQL:

```yaml
services:
  db:
    image: postgres:16-alpine
    container_name: gymsys
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: gym
    ports:
      - "5431:5434"
```

```bash
docker compose up -d    # subir o banco
docker compose down     # parar
docker logs -f gymsys   # ver logs
```


## Licença

Este projeto está sob a licença Apache 2.0 — veja [https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0) para detalhes.