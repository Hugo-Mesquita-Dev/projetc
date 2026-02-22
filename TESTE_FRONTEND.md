## 🧪 GUIA DE TESTES - FRONTEND LOVE MAKEUP BL

### ✅ Pré-requisitos de Teste

Antes de testar o frontend, certifique-se de que:
1. ✅ A aplicação está rodando: `http://localhost:8080`
2. ✅ Maven compilou sem erros
3. ✅ JavaScript está habilitado no navegador
4. ✅ localStorage está habilitado (necessário para carrinho)

---

## 🏠 Teste 1: Homepage

**URL:** `http://localhost:8080/`

**O que testar:**
- [ ] Logo "Love Makeup BL" aparece no header
- [ ] Menu de navegação está visível
- [ ] Ícone de carrinho aparece no canto superior direito
- [ ] Ícone de perfil/usuário aparece no header
- [ ] Seção Hero com título "Bem-vindo à Love Makeup BL"
- [ ] Botão "Explorar Produtos" funciona e leva a `/produtos`
- [ ] Footer com informações de contato
- [ ] Link WhatsApp no footer funciona

**Resultado esperado:**
Design responsivo, cores rosa (#d4477d) e branco, fontes Poppins, layout similar ao Boca Rosa.

---

## 🛍️ Teste 2: Catálogo de Produtos

**URL:** `http://localhost:8080/produtos`

**O que testar:**
- [ ] 4 abas aparecem: Batons, Sombras, Bases, Pincéis
- [ ] Aba "Batons" carrega por padrão
- [ ] Clicar em outra aba carrega os produtos correspondentes
- [ ] Cada produto mostra: imagem, nome, categoria, preço, descrição
- [ ] Campo de quantidade está presente
- [ ] Botão "Adicionar ao Carrinho" é funcional

**Teste de Funcionalidade:**
```
1. Acesse /produtos
2. Clique no botão "Adicionar ao Carrinho" de qualquer produto
3. Verificar se o ícone do carrinho no header mostra "1"
4. Clique em outro produto e adicione
5. O contador do carrinho deve mostrar "2"
```

**Resultado esperado:**
- Abas funcionam corretamente
- Produtos adicionados ao carrinho via JavaScript
- Contador no carrinho atualiza em tempo real

---

## 🛒 Teste 3: Carrinho de Compras

**URL:** `http://localhost:8080/carrinho`

**O que testar:**
- [ ] Se carrinho vazio: mensagem "Seu carrinho está vazio" com link para continuar comprando
- [ ] Se com itens: lista de produtos com:
  - [ ] Imagem do produto
  - [ ] Nome do produto
  - [ ] Preço unitário
  - [ ] Botões +/- para quantidade
  - [ ] Campo de quantidade editável
  - [ ] Botão "Remover"
- [ ] Resumo do carrinho à direita com:
  - [ ] Subtotal
  - [ ] Valor do frete (grátis se > R$ 100)
  - [ ] Total
  - [ ] Botão "Finalizar no WhatsApp"
  - [ ] Botão "Limpar Carrinho"

**Teste de Funcionalidade:**
```
1. Adicione 2 produtos diferentes no catálogo
2. Acesse o carrinho
3. Clique em + para aumentar quantidade de um produto
4. Clique em - para diminuir (mínimo 1)
5. Clique em "Remover" em um produto
6. Verifique se o subtotal e total atualizam corretamente
7. Limpe o carrinho com o botão
8. Verificar mensagem de carrinho vazio
```

**Resultado esperado:**
- Carrinho persiste (usa localStorage)
- Cálculos de total e frete corretos
- Botões funcionam sem recarregar página

---

## 📞 Teste 4: Página de Contato

**URL:** `http://localhost:8080/contato`

**O que testar:**
- [ ] Título "Entre em Contato"
- [ ] Dois cards: "WhatsApp" e "Localização"
- [ ] Link WhatsApp funciona (abre conversa)
- [ ] Informações de localização corretas:
  - Rua Nova, nº 4 - Centro
  - Belágua - Maranhão
- [ ] Formulário de contato com campos:
  - [ ] Nome (obrigatório)
  - [ ] E-mail (obrigatório)
  - [ ] Telefone (opcional)
  - [ ] Assunto (obrigatório)
  - [ ] Mensagem (obrigatório)
- [ ] Botão "Enviar Mensagem"
- [ ] Seção de horário de atendimento

**Teste de Funcionalidade:**
```
1. Preencha o formulário com dados fictícios
2. Clique em "Enviar Mensagem"
3. Deve abrir WhatsApp com mensagem pré-formatada
4. Verifique se todos os dados aparecem na mensagem
```

**Resultado esperado:**
- Formulário funciona
- Abre WhatsApp com mensagem personalizada
- Design responsivo

---

## ℹ️ Teste 5: Página Quem Somos

**URL:** `http://localhost:8080/sobre`

**O que testar:**
- [ ] Seção "Nossa História" com texto
- [ ] Seção "Por que Escolher a Gente?" com 6 cards
- [ ] Seção "Nossa Equipe"
- [ ] Seção "Nossa Localização" com:
  - [ ] Nome da loja
  - [ ] Endereço completo
  - [ ] Botão de contato WhatsApp
- [ ] Seção "Valores que nos Guiam" com:
  - [ ] Qualidade
  - [ ] Confiança
  - [ ] Satisfação
  - [ ] Paixão
- [ ] Design responsivo com cards

**Resultado esperado:**
Layout profissional com todas as informações da loja apresentadas claramente.

---

## 👤 Teste 6: Página de Perfil/Login

**URL:** `http://localhost:8080/perfil`

**Teste 6.1 - Criando uma Conta**
```
1. Clique na aba "Criar Conta"
2. Preencha:
   - Nome: "Maria Silva"
   - E-mail: "maria@example.com"
   - Telefone: "(98) 98406-7365"
   - Senha: "senha123"
   - Confirmar: "senha123"
3. Clique "Criar Conta"
```

**O que testar:**
- [ ] Validação de campos vazios
- [ ] Validação de senhas diferentes
- [ ] Mensagem de sucesso ao criar
- [ ] Dados salvos em localStorage
- [ ] Redireciona para login automaticamente

**Teste 6.2 - Fazendo Login**
```
1. Use os dados criados acima
2. E-mail: "maria@example.com"
3. Senha: "senha123"
4. Clique "Entrar"
```

**O que testar:**
- [ ] Validação de campos vazios
- [ ] Mensagem de erro para credenciais inválidas
- [ ] Mensagem de sucesso para login correto
- [ ] Exibição de dados do usuário:
  - [ ] Nome
  - [ ] E-mail
  - [ ] Telefone
  - [ ] Data de membro
- [ ] Botão "Sair" funciona

**Teste 6.3 - Logout**
```
1. Após login, clique "Sair"
2. Confirme no diálogo
```

**O que testar:**
- [ ] Volta para tela de login
- [ ] localStorage é limpo
- [ ] Dados não aparecem mais

**Resultado esperado:**
Sistema de autenticação funciona corretamente com validações.

---

## 🔄 Teste 7: Integração WhatsApp

**URLs afetadas:** Carrinho, Contato, Perfil (links)

**O que testar:**
```
1. No carrinho, clique "Finalizar no WhatsApp"
2. Deve abrir: https://wa.me/5598984067365
3. Mensagem deve conter:
   - Lista de produtos
   - Quantidades
   - Valores
   - Total
```

**Resultado esperado:**
Abre WhatsApp Web ou aplicativo com mensagem pré-preenchida e formatada.

---

## 📱 Teste 8: Responsividade (Mobile)

**Ferramenta:** Abra DevTools (F12) → Modo responsivo

**Breakpoints a testar:**
- [ ] 320px (iPhone SE)
- [ ] 480px (Android pequeno)
- [ ] 768px (Tablet)
- [ ] 1024px (Laptop)

**O que testar em cada breakpoint:**
- [ ] Menu navegável
- [ ] Produtos em grid responsivo
- [ ] Carrinho legível
- [ ] Formulários usáveis
- [ ] Ícones visíveis
- [ ] Texto legível

**Resultado esperado:**
Layout adapta corretamente em todos os tamanhos.

---

## 🚀 Teste 9: Performance & Carregamento

**O que testar:**
```
1. Abra DevTools → Network
2. Recarregue a página
3. Verifique:
   - [ ] CSS (style.css) carrega sem erro
   - [ ] JS (app.js) carrega sem erro
   - [ ] Fontes Google Fonts carregam
   - [ ] Ícones Font Awesome carregam
   - [ ] Imagens placeholder carregam
```

**Resultado esperado:**
Nenhum erro de 404 ou CORS, página carrega rápido.

---

## 🔒 Teste 10: LocalStorage & Persistência

**O que testar:**
```
1. Adicione produtos ao carrinho
2. Recarregue a página (F5)
3. Verifique se o carrinho mantém os itens
4. Crie uma conta
5. Recarregue a página
6. Verifique se dados do usuário persistem
7. Limpe o cache (Ctrl+Shift+Delete)
8. Recarregue
9. Verifique se tudo foi limpo
```

**Resultado esperado:**
Dados persistem com localStorage, limpa com cache.

---

## ✅ Checklist Final

- [ ] Todas as páginas carregam sem erro
- [ ] Design é consistente
- [ ] Cores corretas (rosa #d4477d)
- [ ] Responsividade funciona
- [ ] JavaScript funciona sem erros (DevTools Console)
- [ ] LocalStorage funciona
- [ ] WhatsApp integra corretamente
- [ ] Formulários validam
- [ ] Carrinho calcula corretamente
- [ ] Menu navega corretamente
- [ ] Performance é aceitável

---

## 🐛 Relatório de Erros

Se encontrar algum problema:

1. Abra DevTools (F12)
2. Vá em Console
3. Procure por mensagens de erro (em vermelho)
4. Anote o erro completo
5. Verifique em `src/main/resources/static/js/app.js`

**Erros Comuns:**
- "Cannot read property 'cart-count'" → Elemento HTML não existe
- "Cannot convert to number" → Problema com conversão de preço
- "localStorage not found" → Navegador não suporta (use Chrome/Firefox)

---

## 📝 Notas

- Todos os dados são locais (não vai para servidor)
- WhatsApp precisa estar configurado no celular
- Imagens são placeholders (substituir por reais)
- Dados de teste não persistem se limpar cache

---

**Desenvolvido com ❤️ para Love Makeup BL**

