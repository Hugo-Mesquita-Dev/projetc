-- Dados de exemplo para a Loja de Maquiagem
-- Execute essas queries no H2 Console após iniciar a aplicação

-- Inserir Produtos de Exemplo
INSERT INTO PRODUTOS (NOME, DESCRICAO, PRECO, ESTOQUE, CATEGORIA, MARCA) VALUES
('Batom Vermelho Clássico', 'Batom com pigmentação intensa e acabamento matte', 45.90, 50, 'Lábios', 'Mac'),
('Sombra Dourada Shimmer', 'Sombra com acabamento brilhante para realçar olhos', 35.00, 30, 'Olhos', 'Urban Decay'),
('Base Líquida Corretora', 'Base com cobertura média e fórmula hidratante', 89.90, 25, 'Rosto', 'Fenty Beauty'),
('Rímel Volumizador', 'Rímel que proporciona volume extremo e não embaça', 55.00, 40, 'Olhos', 'Charlotte Tilbury'),
('Blush Rosa Pastel', 'Blush com acabamento mate para um look natural', 42.50, 35, 'Rosto', 'Bobbi Brown'),
('Corretivo Anti-Olheiras', 'Corretivo com cobertura total e longa duração', 48.00, 28, 'Rosto', 'Nars'),
('Lápis de Olho Preto', 'Lápis à prova d\'água com ponta precisão', 29.90, 60, 'Olhos', 'Clinique'),
('Gloss Labial Rosa Bebê', 'Gloss com brilho intenso e aroma agradável', 35.50, 45, 'Lábios', 'Dior'),
('Iluminador Rose Gold', 'Iluminador com pigmentação intensa e brilhante', 52.00, 32, 'Rosto', 'Benefit'),
('Máscara de Cílios Premium', 'Máscara rejuvenescedora com ingredientes naturais', 78.00, 20, 'Olhos', 'Charlotte Tilbury');

-- Inserir Compras de Exemplo
INSERT INTO COMPRAS (CLIENTE, EMAIL, TELEFONE, PRODUTO_ID, QUANTIDADE, VALOR_TOTAL, STATUS) VALUES
('Maria Silva', 'maria@email.com', '11987654321', 1, 1, 45.90, 'CONFIRMADA'),
('João Santos', 'joao@email.com', '21987654321', 2, 2, 70.00, 'CONFIRMADA'),
('Ana Oliveira', 'ana@email.com', '31987654321', 3, 1, 89.90, 'CONFIRMADA'),
('Pedro Costa', 'pedro@email.com', '41987654321', 4, 1, 55.00, 'CONFIRMADA'),
('Juliana Mendes', 'juliana@email.com', '51987654321', 5, 3, 127.50, 'CANCELADA'),
('Carlos Ferreira', 'carlos@email.com', '61987654321', 1, 2, 91.80, 'CONFIRMADA');

-- Consultas úteis para verificar dados:

-- Ver todos os produtos
-- SELECT * FROM PRODUTOS;

-- Ver todas as compras
-- SELECT * FROM COMPRAS;

-- Ver total de vendas por categoria
-- SELECT p.CATEGORIA, COUNT(*) as TOTAL_VENDAS, SUM(c.VALOR_TOTAL) as RECEITA_TOTAL
-- FROM COMPRAS c
-- JOIN PRODUTOS p ON c.PRODUTO_ID = p.ID
-- WHERE c.STATUS = 'CONFIRMADA'
-- GROUP BY p.CATEGORIA;

-- Ver cliente que mais comprou
-- SELECT c.CLIENTE, COUNT(*) as TOTAL_COMPRAS, SUM(c.VALOR_TOTAL) as TOTAL_GASTO
-- FROM COMPRAS c
-- WHERE c.STATUS = 'CONFIRMADA'
-- GROUP BY c.CLIENTE
-- ORDER BY TOTAL_GASTO DESC
-- LIMIT 1;

-- Ver produtos com baixo estoque (menos de 25)
-- SELECT NOME, ESTOQUE, CATEGORIA FROM PRODUTOS WHERE ESTOQUE < 25;

-- Ver produto mais vendido
-- SELECT p.NOME, COUNT(*) as VEZES_VENDIDO, SUM(c.QUANTIDADE) as TOTAL_QUANTIDADE
-- FROM COMPRAS c
-- JOIN PRODUTOS p ON c.PRODUTO_ID = p.ID
-- WHERE c.STATUS = 'CONFIRMADA'
-- GROUP BY p.ID, p.NOME
-- ORDER BY TOTAL_QUANTIDADE DESC
-- LIMIT 1;

