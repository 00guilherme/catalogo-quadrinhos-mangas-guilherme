# 📚 Catálogo de Quadrinhos e Mangás

Projeto acadêmico completo usando:

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- HTML
- CSS
- JavaScript
- Maven

## 1. Pré-requisitos

Instale:

1. JDK 17 ou superior
2. VS Code
3. Extensões do VS Code:
   - Extension Pack for Java
   - Spring Boot Extension Pack
4. MySQL Server

## 2. Criar o banco

Abra o MySQL Workbench ou terminal e execute:

```sql
CREATE DATABASE catalogo_quadrinhos
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
```

Não precisa criar as tabelas. O Hibernate fará isso automaticamente porque o projeto usa:

```properties
spring.jpa.hibernate.ddl-auto=update
```

## 3. Configurar a senha do MySQL

Abra:

`src/main/resources/application.properties`

Troque:

```properties
spring.datasource.password=COLOQUE_SUA_SENHA_AQUI
```

pela senha do seu usuário `root`.

Se seu MySQL não usa senha, deixe:

```properties
spring.datasource.password=
```

Se o usuário não for `root`, altere também:

```properties
spring.datasource.username=seu_usuario
```

## 4. Abrir no VS Code

Abra a pasta:

`catalogo-quadrinhos`

No VS Code, espere o Maven carregar as dependências.

## 5. Executar

No terminal do VS Code:

### Windows

```bash
mvnw.cmd spring-boot:run
```

Se o Maven estiver instalado:

```bash
mvn spring-boot:run
```

Depois acesse:

http://localhost:8080

## 6. O que será criado no MySQL

O Hibernate criará:

- genero
- editora
- obra

A tabela `obra` terá relacionamento com:

- genero_id
- editora_id

## 7. Funcionalidades

### Obras

- Criar
- Listar
- Buscar por ID
- Editar
- Excluir
- Pesquisar por título
- Pesquisar por autor
- Filtrar por gênero
- Filtrar por editora

### Gêneros

- Criar
- Listar
- Buscar
- Editar
- Excluir

### Editoras

- Criar
- Listar
- Buscar
- Editar
- Excluir

## 8. Endpoints

### Obras

GET `/api/obras`

GET `/api/obras/{id}`

POST `/api/obras`

PUT `/api/obras/{id}`

DELETE `/api/obras/{id}`

Pesquisa:

`GET /api/obras?busca=oda`

Filtro:

`GET /api/obras?generoId=1`

Filtro:

`GET /api/obras?editoraId=1`

Combinação:

`GET /api/obras?busca=naruto&generoId=1&editoraId=2`

### Gêneros

GET `/api/generos`

POST `/api/generos`

PUT `/api/generos/{id}`

DELETE `/api/generos/{id}`

### Editoras

GET `/api/editoras`

POST `/api/editoras`

PUT `/api/editoras/{id}`

DELETE `/api/editoras/{id}`

## 9. Primeiros dados para testar

Depois de iniciar o sistema, você pode usar o frontend para cadastrar:

Gêneros:

- Ação
- Romance
- Fantasia
- Terror

Editoras:

- Panini
- JBC
- NewPOP

Obras:

- One Piece — volume 105 — Eiichiro Oda — Ação — Panini — 9.5 — Lido
- Naruto — volume 20 — Masashi Kishimoto — Ação — JBC — 9.0 — Lendo
- Berserk — volume 1 — Kentaro Miura — Fantasia — Panini — 10.0 — Lido

## 10. Checklist da atividade

- [x] CRUD
- [x] Relacionamento obra → gênero
- [x] Relacionamento obra → editora
- [x] Pesquisa por título
- [x] Pesquisa por autor
- [x] Filtro por gênero
- [x] Filtro por editora
- [x] Cadastro de título
- [x] Cadastro de volume
- [x] Cadastro de autor
- [x] Registro de nota
- [x] Registro de status
- [x] Interface web

## 11. Se aparecer erro de conexão com MySQL

Confira:

1. O serviço MySQL está iniciado.
2. O banco `catalogo_quadrinhos` existe.
3. Usuário e senha estão corretos.
4. A porta está como `3306`.
5. O Java usado pelo VS Code é o JDK 17 ou superior.

## 12. Apresentação

Explique assim:

> "O sistema foi desenvolvido para organizar uma coleção pessoal de quadrinhos e mangás. O backend foi desenvolvido com Java e Spring Boot, utilizando JPA para persistência no MySQL. As entidades principais são Obra, Gênero e Editora, com relacionamentos simples de muitos-para-um. O sistema possui operações CRUD, pesquisa por título ou autor e filtros por gênero e editora. O frontend foi desenvolvido em HTML, CSS e JavaScript e consome a API REST."

