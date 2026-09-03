-- Cria o banco somente se ele ainda não existir, usando suporte completo a UTF-8.
CREATE DATABASE IF NOT EXISTS catalogo_quadrinhos
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Seleciona o banco que receberá as tabelas geradas pelo Hibernate.
USE catalogo_quadrinhos;

-- O Spring Boot cria as tabelas automaticamente.
-- Estes INSERTs são opcionais e servem apenas para ter dados iniciais.

-- Dados iniciais opcionais usados para testar os filtros da aplicação.
INSERT INTO genero (nome) VALUES
('Ação'),
('Romance'),
('Fantasia'),
('Terror');

INSERT INTO editora (nome) VALUES
('Panini'),
('JBC'),
('NewPOP');

-- Depois que os IDs forem criados, exemplos de obras:
-- INSERT INTO obra
-- (titulo, volume, autor, nota, status, genero_id, editora_id)
-- VALUES
-- ('One Piece', 105, 'Eiichiro Oda', 9.5, 'LIDO', 1, 1);
