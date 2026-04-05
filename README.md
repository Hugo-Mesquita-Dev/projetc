# 🛍️ Loja de Maquiagem - Love Makeup BL

Um projeto **Full Stack** completo em **Java Spring Boot** com frontend **Thymeleaf** para gerenciar uma loja de maquiagem com funcionalidades de CRUD de produtos, gestão de compras e e-commerce interativo.

## 📋 Funcionalidades

### Backend API
✅ **Gerenciamento de Produtos**
- Criar novos produtos
- Listar todos os produtos
- Buscar produtos por ID, categoria ou marca
- Atualizar informações do produto
- Deletar produtos

✅ **Gestão de Compras**
- Realizar novas compras com endereço de entrega
- Listar todas as compras
- Buscar compras por cliente ou status
- Cancelar compras (restaura estoque automaticamente)
- Deletar compras
- Validação automática de estoque
- Integração com endereço de entrega do cliente

### Frontend Web
✅ **Interface E-commerce Completa**
- **Homepage** com apresentação elegante da loja
- **Catálogo Dinâmico de Produtos** com:
  - Filtro por categorias (Bases, Sombras, Batons, Alisadores, Acessórios)
  - Busca em tempo real
  - Modal com detalhes do produto
  - Seletor de quantidade
- **Carrinho de Compras** com:
  - Gerenciamento via localStorage (persistente)
  - Cálculo automático de totais
  - Campo para endereço de entrega
  - Atualizar quantidade de itens
  - Remover produtos
- **Integração com WhatsApp** com:
  - Envio automático de pedidos com lista completa
  - Endereço de entrega incluído na mensagem
  - Número da loja pré-preenchido
- **Sistema de Perfil do Cliente** com:
  - Meus Dados (nome, email, telefone, CPF, data de nascimento)
  - Endereço de Entrega (salvo para futuras compras)
  - Preferências (newsletter, SMS, WhatsApp)
  - Armazenamento via localStorage
- **Página de Contato** com:
  - Formulário de mensagem enviado por email
  - Múltiplas formas de contato (WhatsApp, Email, Telefone)
  - Informações de localização e horário
- **Página Quem Somos** com:
  - História da loja
  - Valores (Qualidade, Confiança, Comunidade, Inovação, Segurança, Satisfação)
  - Localização e horário de funcionamento
  - Informações da equipe
- **Responsivo** e totalmente otimizado para mobile e desktop

✅ **Recursos Adicionais**
- Banco de dados H2 em memória
- CORS habilitado para acesso remoto
- Console H2 para visualizar dados
- Controle automático de datas (criação e atualização)
- DTOs para transferência de dados
- Validação de estoque nas compras
- Design moderno inspirado em layouts premium
- Suporte a múltiplas categorias de produtos
- Endereço de entrega persistente no cliente
- Envio de emails via formulário de contato
- Segurança com Spring Security (preparado)

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database**
- **Lombok**
- **Maven**

### Frontend
- **Thymeleaf** (Template Engine)
- **HTML5**
- **CSS3** (com design responsivo)
- **JavaScript ES6+**
- **Font Awesome** (ícones)
- **Google Fonts** (Poppins)
- **LocalStorage API** (carrinho persistente)

## 📁 Estrutura do Projeto

```
projetc/
├── src/
│   ├── main/
│   │   ├── java/com/java/projetc/
│   │   │   ├── controller/
│   │   │   │   ├── ProdutoController.java
│   │   │   │   ├── CompraController.java
│   │   │   │   └── WebController.java
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
│   │       ├── application.properties
│   │       ├── templates/
│   │       │   ├── layout.html
│   │       │   ├── index.html
│   │       │   ├── produtos.html
│   │       │   ├── carrinho.html
│   │       │   ├── contato.html
│   │       │   ├── sobre.html
│   │       │   └── perfil.html
│   │       └── static/
│   │           ├── css/
│   │           │   └── style.css
│   │           ├── js/
│   │           │   └── app.js
│   │           └── img/ (imagens de produtos)
│   └── test/
├── pom.xml
├── mvnw / mvnw.cmd
├── insomnia_collection.json
└── README.md
```

## 🚀 Como Executar

### 1. Pré-requisitos

- Java 17 ou superior instalado
- Maven 3.6+ instalado (ou use o Maven Wrapper incluído)
- Git instalado (para versionamento do código)

### 2. Clone o Repositório

```bash
git clone https://github.com/SEU_USUARIO/projetc.git
cd projetc
```

### 3. Compile o Projeto

**Com Maven Wrapper (recomendado):**
```bash
./mvnw clean install
```

**Ou com Maven instalado:**
```bash
mvn clean install
```

### 4. Execute a Aplicação

**Com Maven Wrapper:**
```bash
./mvnw spring-boot:run
```

**Ou diretamente:**
```bash
mvn spring-boot:run
```

A aplicação iniciará em `http://localhost:8080`

### 5. Acessando a Aplicação

#### Frontend (Thymeleaf)
- **Home**: http://localhost:8080/
- **Produtos**: http://localhost:8080/produtos
- **Carrinho**: http://localhost:8080/carrinho
- **Perfil/Login**: http://localhost:8080/perfil
- **Contato**: http://localhost:8080/contato
- **Quem Somos**: http://localhost:8080/sobre

#### Backend API
- **API Produtos**: http://localhost:8080/api/produtos
- **API Compras**: http://localhost:8080/api/compras

#### Ferramentas
- **Console H2**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - User Name: `sa`
  - Password: (deixe em branco)

## 💻 Como Usar o Frontend

### Navegação Principal

O site possui uma navegação intuitiva com:
- **Header** com logo, carrinho e ícone de perfil
- **Menu** com acesso às principais seções
- **Footer** com informações de contato

### 🛒 Carrinho de Compras

1. Acesse **Produtos** (`/produtos`)
2. Escolha um produto e clique em **"Adicionar ao Carrinho"**
3. O número de itens aparecerá no ícone do carrinho
4. Acesse **Carrinho** (`/carrinho`) para revisar
5. Clique em **"Finalizar no WhatsApp"** para enviar o pedido

#### Funcionalidades do Carrinho:
- ✅ Aumentar/diminuir quantidade
- ✅ Remover itens
- ✅ Cálculo automático de frete
- ✅ Frete grátis para compras acima de R$ 100

### 👤 Sistema de Login/Registro

1. Acesse **Perfil** (`/perfil`)
2. Para **nova conta**: clique em "Criar Conta"
3. Preencha os dados: Nome, E-mail, Telefone, Senha
4. Para **login existente**: use as credenciais cadastradas
5. Seu perfil será exibido com opção de logout

#### Dados são Armazenados em:
- localStorage do navegador (cliente-side)
- Dados persistem enquanto o navegador não limpar cache

### 📦 Categorias de Produtos

Os produtos estão organizados em abas:
- **Batons** - Vários tons e acabamentos
- **Sombras** - Opções matte, shimmer e cintilante
- **Bases** - Líquidas, pó e complementos
- **Pincéis** - Kabuki, sombra, blush e kits

### 📞 Contato e Suporte

- **WhatsApp**: (98) 98406-7365
- **Localização**: Rua Nova, nº 4 - Centro, Belágua - MA
- **Formulário de contato**: Disponível em `/contato`

## 🎨 Personalização

### Alterar Número do WhatsApp

Edite o arquivo `src/main/resources/static/js/app.js`:

```javascript
// Procure por:
window.open(`https://wa.me/5598984067365?text=${msg}`, '_blank');

// E altere para seu número:
window.open(`https://wa.me/55XX999999999?text=${msg}`, '_blank');
```

### Alterar Cores da Loja

Edite `src/main/resources/static/css/style.css`:

```css
:root {
    --primary-color: #d4477d;      /* Cor principal (rosa) */
    --secondary-color: #f8d7e8;    /* Cor secundária */
    --dark-color: #333;             /* Cor escura */
}
```

### Adicionar Mais Produtos

Edite `src/main/resources/templates/produtos.html` e adicione novos cards dentro das abas:

```html
<div class="product-card" data-product-id="17">
    <img src="URL_DA_IMAGEM" alt="Produto" class="product-image">
    <div class="product-info">
        <h3 class="product-name">Nome do Produto</h3>
        <p class="product-category">Categoria</p>
        <p class="product-price">R$ 00,00</p>
        <p class="product-description">Descrição breve</p>
        <div class="product-quantity">
            <input type="number" class="quantity-input" value="1" min="1">
        </div>
        <button class="add-to-cart-btn" onclick="addToCart(17)">Adicionar ao Carrinho</button>
    </div>
</div>
```

## 🔧 Configurações da Aplicação

### application.properties

```properties
# Server
server.port=8080

# H2 Database
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
```

## 📊 Endpoints da API

### Produtos

```bash
# Listar todos
GET /api/produtos

# Buscar por ID
GET /api/produtos/{id}

# Buscar por categoria
GET /api/produtos/categoria/{categoria}

# Criar novo
POST /api/produtos
Content-Type: application/json
{
  "nome": "Batom Rosa",
  "categoria": "Batons",
  "preco": 29.90,
  "estoque": 50
}

# Atualizar
PUT /api/produtos/{id}

# Deletar
DELETE /api/produtos/{id}
```

### Compras

```bash
# Listar todas
GET /api/compras

# Buscar por ID
GET /api/compras/{id}

# Realizar compra
POST /api/compras
Content-Type: application/json
{
  "cliente": "João Silva",
  "email": "joao@example.com",
  "telefone": "119999999",
  "produtoId": 1,
  "quantidade": 2
}

# Cancelar compra
PUT /api/compras/{id}/cancelar

# Deletar
DELETE /api/compras/{id}
```

## 📦 Configuração do Git e Deploy no GitHub

### Passo 1: Configurar Git Localmente

```bash
cd C:\Users\Hugo\Projetos\projetc
git config user.name "Seu Nome"
git config user.email "seu.email@example.com"
```

### Passo 2: Criar um Repositório no GitHub

1. Acesse [https://github.com/new](https://github.com/new)
2. Digite o nome: `projetc`
3. Descrição: "E-commerce de Maquiagem - Love Makeup BL"
4. Clique em "Create repository"

### Passo 3: Fazer Push para o GitHub

```bash
git remote add origin https://github.com/SEU_USUARIO/projetc.git
git branch -M main
git push -u origin main
```

## 📝 Comandos Git Úteis

```bash
# Ver status
git status

# Ver histórico
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

# Desfazer último commit (mantém mudanças)
git reset --soft HEAD~1

# Desfazer último commit (descarta mudanças)
git reset --hard HEAD~1
```

## 🚀 Deploy em Produção

Opções recomendadas:

- **Heroku**: Platform as a Service simples
- **AWS**: Escalabilidade e robustez
- **DigitalOcean**: Custo-benefício
- **Google Cloud**: Integração com Google

## 🐛 Troubleshooting

### Porta 8080 já em uso
```bash
# Windows - encontre o processo
netstat -ano | findstr :8080

# Mude a porta em application.properties
server.port=8081
```

### Erro de dependências Maven
```bash
./mvnw clean
./mvnw install -U
```

### Limpar cache do navegador
```
Ctrl + Shift + Delete (Windows/Linux)
Cmd + Shift + Delete (Mac)
```

### Erro "A sintaxe do comando está incorreta"
Use o Maven Wrapper fornecido:
```bash
mvnw.cmd clean compile
mvnw.cmd spring-boot:run
```

## 📞 Suporte

Para dúvidas ou sugestões:
- 📧 Email: contato@lovemakeupbl.com
- 📱 WhatsApp: (98) 98406-7365
- 📍 Localização: Belágua - Maranhão

## 📄 Licença

Este projeto é fornecido como está para fins educacionais e comerciais. Todos os direitos reservados ao Love Makeup BL.

---

**Desenvolvido com ❤️ para gerenciamento de loja de maquiagem**

