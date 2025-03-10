-- Filtros
-- select * from db_cine_pipoca.tbl_filme tf;
-- select * from db_cine_pipoca.tbl_sala ts;
-- select * from db_cine_pipoca.tbl_assento ta;
-- select * from db_cine_pipoca.tbl_sessao ts;
-- select * from db_cine_pipoca.tbl_cliente tc;
-- select * from db_cine_pipoca.tbl_ingresso ti;

-- Criar cliente
insert into db_cine_pipoca.tbl_cliente (nome) values ("Ryan");

-- Criar filme
insert into  db_cine_pipoca.tbl_filme (titulo, idioma, duracao) values ("O auto da compadecida", "DUBLADO", "2h");
insert into  db_cine_pipoca.tbl_filme (titulo, idioma, duracao) values ("A garota da vez", "LEGENDADO", "1h30m");
insert into  db_cine_pipoca.tbl_filme (titulo, idioma, duracao) values ("A procura da felicidade", "LEGENDADO", "2h10m");

-- Criar sala
insert into db_cine_pipoca.tbl_sala (nome, status) values ("A", "FECHADA");

-- Criar assento
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (1, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (2, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (3, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (4, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (5, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (6, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (7, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (8, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (9, "VAZIO", null, 1);
insert into db_cine_pipoca.tbl_assento (numero, status, cliente_id, sala_id) values (10, "VAZIO", null, 1);

-- Criar sessão
insert into db_cine_pipoca.tbl_sessao (status, data_hora_inicio, data_hora_fim, valor, filme_id, sala_sessao_id)
values ("DISPONIVEL", '2025-03-10 14:30:00', '2025-03-10 16:30:00', '40.00', 1, 1);

-- Comprar ingresso
insert into db_cine_pipoca.tbl_ingresso (meia_entrada, valor_total, cliente_id_ingresso, sessao_id, forma_pagamento) values (0, '40.00', 1, 1, "PIX");