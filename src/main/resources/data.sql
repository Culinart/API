-- Inserts para a tabela Usuario
INSERT INTO Usuario (nome, email, senha, permissao, is_ativo) VALUES
  ('Nome1', 'email1@example.com', 'senha1', 1, 1),
  ('Nome2', 'email2@example.com', 'senha2', 2, 1),
  ('Nome3', 'email3@example.com', 'senha3', 1, 1);

-- Inserts para a tabela Endereco
INSERT INTO Endereco (logradouro, cep, cidade, uf, bairro, complemento, numero) VALUES
  ('Rua 1', '12345-678', 'Cidade1', 'UF1', 'Bairro1', 'Complemento1', 123),
  ('Rua 2', '54321-876', 'Cidade2', 'UF2', 'Bairro2', 'Complemento2', 456),
  ('Rua 3', '98765-432', 'Cidade3', 'UF3', 'Bairro3', 'Complemento3', 789);

-- Inserts para a tabela EnderecoUsuario
INSERT INTO Endereco_Usuario (endereco_id, usuario_id, is_ativo) VALUES
  (1, 1, 1),
  (2, 2, 1),
  (3, 3, 1);

-- Inserts para a tabela Plano
INSERT INTO Plano (qtd_pessoas, qtd_refeicoes_dia, valor_plano, valor_ajuste, qtd_dias_semana, hora_entrega, dia_semana, is_ativo, usuario_id) VALUES
  (2, 3, 100.00, 10.00, 5, '2023-11-09T12:00:00', 'SEGUNDA', 1, 1),
  (1, 2, 80.00, 8.00, 3, '2023-11-09T18:00:00', 'QUARTA', 1, 2),
  (3, 4, 120.00, 12.00, 7, '2023-11-09T20:00:00', 'SEXTA', 1, 3);

-- Inserts para a tabela Pedido
INSERT INTO Pedido (data_criacao, valor, status,  data_entrega, plano_id) VALUES
  ('2023-11-09', 150.00, 'PROCESSAMENTO','2023-11-16', 1),
  ('2023-11-09', 100.00, 'CANCELADO', '2023-11-10', 2),
  ('2023-11-09', 200.00, 'NEXT', '2023-11-12', 3);

-- Inserts para a tabela Receita
INSERT INTO Receita (nome, tempo_preparo, descricao) VALUES
  ('Receita1', 30.0, 'Descrição da Receita1'),
  ('Receita2', 45.0, 'Descrição da Receita2'),
  ('Receita3', 60.0, 'Descrição da Receita3');

INSERT INTO Pedido_Receita (pedido_id, receita_id) VALUES
(3,1),
(3,2),
(3,3)