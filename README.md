# 🛍️ Loja de Maquiagem - Backend API

Um projeto completo de backend em **Java Spring Boot** para gerenciar uma loja de maquiagem com funcionalidades de CRUD de produtos e gestão de compras.

## 📋 Funcionalidades

✅ **Gerenciamento de Produtos**
- Criar novos produtos
- Listar todos os produtos
- Buscar produtos por ID, categoria ou marca
- Atualizar informações do produto
- Deletar produtos

✅ **Gestão de Compras**
- Realizar novas compras
- Listar todas as compras
- Buscar compras por cliente ou status
- Cancelar compras (restaura estoque automaticamente)
- Deletar compras
- Validação automática de estoque

✅ **Recursos Adicionais**
- Banco de dados H2 em memória
- CORS habilitado para acesso remoto
- Console H2 para visualizar dados
- Controle automático de datas (criação e atualização)
- DTOs para transferência de dados
- Validação de estoque nas compras

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database**
- **Lombok**
- **Maven**

## 📁 Estrutura do Projeto

```
projetc/
├── src/
│   ├── main/
│   │   ├── java/com/java/projetc/
│   │   │   ├── controller/
│   │   │   │   ├── ProdutoController.java
│   │   │   │   └── CompraController.java
│   │   │   ├── service/
│   │   │   │   ├── ProdutoService.java
│   │   │   │   └── CompraService.java
│   │   │   ├── repository/
│   │   │   │   ├── ProdutoRepository.java
│   │   │   │   └── CompraRepository.java
│   │   │   ├── model/
│   │   │   │   ├── Produto.java
│   │   │   │   └── Compra.java
│   │   │   ├── dto/
│   │   │   │   ├── ProdutoDTO.java
│   │   │   │   └── CompraDTO.java
│   │   │   └── ProjetcApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── insomnia_collection.json
├── API_DOCUMENTATION.md
└── README.md
```

## 🚀 Como Executar

### 1. Pré-requisitos

- Java 17 ou superior instalado
- Maven 3.6+ instalado (ou use o Maven Wrapper incluído)
- Git instalado (para versionamento do código)

## 📦 Configuração do Git e Deploy no GitHub

### Passo 1: Configurar Git Localmente

Se ainda não configurou o Git, execute:

```bash
git config --global user.name "Seu Nome"
git config --global user.email "seu.email@example.com"
```

Para configurar apenas para este projeto:

```bash
cd projetc
git config user.name "Seu Nome"
git config user.email "seu.email@example.com"
```

### Passo 2: Criar um Repositório no GitHub

1. Acesse [https://github.com/new](https://github.com/new)
2. Digite o nome do repositório: `projetc` (ou outro nome de sua preferência)
3. Adicione uma descrição: "Backend da Loja de Maquiagem em Spring Boot"
4. Selecione "Public" ou "Private" conforme preferência
5. **Não** adicione README, gitignore ou license (já temos)
6. Clique em "Create repository"

### Passo 3: Adicionar Remote do Repositório

Após criar o repositório no GitHub, você receberá um URL. Execute:

```bash
cd C:\Users\Hugo\Downloads\projetc
git remote add origin https://github.com/SEU_USUARIO/projetc.git
```

**Substitua `SEU_USUARIO` pelo seu nome de usuário do GitHub**

### Passo 4: Verificar a Configuração

```bash
git remote -v
```

Deve mostrar:
```
origin  https://github.com/SEU_USUARIO/projetc.git (fetch)
origin  https://github.com/SEU_USUARIO/projetc.git (push)
```

### Passo 5: Fazer Push para o GitHub

```bash
git branch -M main
git push -u origin main
```

Será solicitado seu login do GitHub. Se usar autenticação por senha:
- **Username:** seu nome de usuário do GitHub
- **Password:** seu token de acesso pessoal (PAT)

**Para gerar um Personal Access Token:**
1. Acesse [https://github.com/settings/tokens](https://github.com/settings/tokens)
2. Clique em "Generate new token"
3. Selecione os scopes: `repo` (acesso completo a repositórios)
4. Copie o token gerado (use como senha)

### Passo 6: Verificar Upload

Após o push, acesse seu repositório no GitHub:
```
https://github.com/SEU_USUARIO/projetc
```

Pronto! Seu projeto está no GitHub! 🎉

## 📝 Comandos Git Úteis

```bash
# Ver status do repositório
git status

# Ver histórico de commits
git log

# Ver branches
git branch -a

# Criar nova branch
git checkout -b feature/nova-funcionalidade

# Fazer commit
git add .
git commit -m "Descrição da mudança"

# Fazer push
git push origin main

# Puxar atualizações
git pull origin main

# Desfazer último commit (sem perder as mudanças)
git reset --soft HEAD~1

# Desfazer último commit (perdendo as mudanças)
git reset --hard HEAD~1
```

### 2. Opção A: Usar Scripts Batch (Recomendado para Windows)

**Para compilar:**
```bash
# Execute o arquivo compilar.bat
compilar.bat
```

**Para executar:**
```bash
# Execute o arquivo executar.bat
executar.bat
```

### 3. Opção B: Compilar Manualmente

```bash
cd projetc
mvnw clean compile
```

### 4. Opção C: Executar com Maven

```bash
mvnw spring-boot:run
```

### 5. Acessar a Aplicação

- **API Base URL:** `http://localhost:8080/api`
- **H2 Console:** `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:testdb`
  - User: `sa`
  - Password: (deixar em branco)

## 📡 Endpoints da API

### Base URL
```
http://localhost:8080/api
```

### Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/produtos` | Criar novo produto |
| GET | `/produtos` | Listar todos os produtos |
| GET | `/produtos/{id}` | Buscar produto por ID |
| GET | `/produtos/categoria/{categoria}` | Buscar por categoria |
| GET | `/produtos/marca/{marca}` | Buscar por marca |
| PUT | `/produtos/{id}` | Atualizar produto |
| DELETE | `/produtos/{id}` | Deletar produto |

### Compras

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/compras` | Realizar compra |
| GET | `/compras` | Listar todas as compras |
| GET | `/compras/{id}` | Buscar compra por ID |
| GET | `/compras/cliente/{cliente}` | Buscar por cliente |
| GET | `/compras/status/{status}` | Buscar por status |
| PUT | `/compras/{id}/cancelar` | Cancelar compra |
| DELETE | `/compras/{id}` | Deletar compra |

## 📥 Importar no Insomnia

1. Abra o Insomnia
2. Clique em **Import/Export** → **Import Data**
3. Selecione o arquivo `insomnia_collection.json`
4. A coleção "Loja de Maquiagem" será importada com todas as requisições

## 📝 Exemplos de Requisições

### Criar Produto

```json
POST /api/produtos
Content-Type: application/json

{
  "nome": "Batom Vermelho Clássico",
  "descricao": "Batom com pigmentação intensa e acabamento matte",
  "preco": 45.90,
  "estoque": 50,
  "categoria": "Lábios",
  "marca": "Mac"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "Batom Vermelho Clássico",
  "descricao": "Batom com pigmentação intensa e acabamento matte",
  "preco": 45.90,
  "estoque": 50,
  "categoria": "Lábios",
  "marca": "Mac"
}
```

### Realizar Compra

```json
POST /api/compras
Content-Type: application/json

{
  "cliente": "João Silva",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "produtoId": 1,
  "quantidade": 2
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "cliente": "João Silva",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "produtoId": 1,
  "produtoNome": "Batom Vermelho Clássico",
  "quantidade": 2,
  "valorTotal": 91.80,
  "dataCompra": "2026-02-21T10:30:00",
  "status": "CONFIRMADA"
}
```

### Cancelar Compra

```
PUT /api/compras/1/cancelar
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "cliente": "João Silva",
  "email": "joao@email.com",
  "telefone": "11999999999",
  "produtoId": 1,
  "produtoNome": "Batom Vermelho Clássico",
  "quantidade": 2,
  "valorTotal": 91.80,
  "dataCompra": "2026-02-21T10:30:00",
  "status": "CANCELADA"
}
```

## ⚠️ Códigos de Resposta HTTP

| Código | Significado |
|--------|------------|
| 200 | OK - Requisição bem-sucedida |
| 201 | Created - Recurso criado com sucesso |
| 204 | No Content - Recurso deletado com sucesso |
| 400 | Bad Request - Erro de validação (estoque insuficiente, produto não encontrado) |
| 404 | Not Found - Recurso não encontrado |
| 500 | Internal Server Error - Erro no servidor |

## 🔒 Validações

### Compras
- ✅ Valida se o produto existe
- ✅ Valida se há estoque suficiente
- ✅ Reduz automaticamente o estoque após compra
- ✅ Restaura estoque ao cancelar compra

### Produtos
- ✅ Require nome e preço
- ✅ Require estoque
- ✅ Rastreia data de criação e atualização

## 🗄️ Modelo de Dados

### Tabela: PRODUTOS

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | BIGINT | ID único (PK) |
| nome | VARCHAR | Nome do produto |
| descricao | TEXT | Descrição detalhada |
| preco | DECIMAL | Preço unitário |
| estoque | INT | Quantidade em estoque |
| categoria | VARCHAR | Categoria (Lábios, Olhos, etc) |
| marca | VARCHAR | Marca do produto |
| data_criacao | TIMESTAMP | Data de criação |
| data_atualizacao | TIMESTAMP | Data da última atualização |

### Tabela: COMPRAS

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | BIGINT | ID único (PK) |
| cliente | VARCHAR | Nome do cliente |
| email | VARCHAR | Email do cliente |
| telefone | VARCHAR | Telefone do cliente |
| produto_id | BIGINT | ID do produto (FK) |
| quantidade | INT | Quantidade comprada |
| valor_total | DECIMAL | Valor total da compra |
| data_compra | TIMESTAMP | Data da compra |
| status | VARCHAR | Status (CONFIRMADA, CANCELADA) |

## 🐛 Troubleshooting

### Problema: "A sintaxe do comando está incorreta" ao executar mvn

**Solução 1 - Usar os scripts batch fornecidos:**
```bash
compilar.bat    # Para compilar
executar.bat    # Para executar
```

**Solução 2 - Usar o Prompt de Comando (CMD) em vez de PowerShell:**
1. Abra o Prompt de Comando (não PowerShell)
2. Execute: `mvnw clean compile`

**Solução 3 - Usar Java direto:**
```bash
# Compilar com Maven Wrapper
mvnw.cmd clean compile

# Executar
mvnw.cmd spring-boot:run
```

### Porta 8080 já está em uso
```bash
mvnw.cmd spring-boot:run --server.port=8081
```

### Erro de compilação - Jakarta não encontrado
Certifique-se de estar usando Java 17+:
```bash
java -version
```

### Banco de dados não inicializa
O H2 é em memória, então os dados são perdidos ao reiniciar. Use um banco persistente modificando `application.properties`:
```properties
spring.datasource.url=jdbc:h2:file:./data/loja
spring.jpa.hibernate.ddl-auto=update
```

## 📚 Documentação Detalhada

Veja o arquivo `API_DOCUMENTATION.md` para exemplos completos de todas as requisições.

## 👨‍💻 Contribuição

Sinta-se à vontade para fazer fork, melhorar e enviar pull requests!

## 📄 Licença

Este projeto é de código aberto e disponível sob a licença MIT.

---

**Desenvolvido com ❤️ para gerenciamento de loja de maquiagem**

