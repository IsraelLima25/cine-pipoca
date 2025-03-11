-- Filtros
-- select * from db_cine_pipoca.tbl_filme tf;
-- select * from db_cine_pipoca.tbl_sala ts;
-- select * from db_cine_pipoca.tbl_assento ta;
-- select * from db_cine_pipoca.tbl_sessao ts;
-- select * from db_cine_pipoca.tbl_ingresso ti;
-- select * from db_cine_pipoca.tbl_reserva tr;

-- Criar filme
insert into  db_cine_pipoca.tbl_filme (titulo, idioma, duracao) values ("O auto da compadecida", "DUBLADO", "2h");
insert into  db_cine_pipoca.tbl_filme (titulo, idioma, duracao) values ("A garota da vez", "LEGENDADO", "1h30m");
insert into  db_cine_pipoca.tbl_filme (titulo, idioma, duracao) values ("A procura da felicidade", "LEGENDADO", "2h10m");

-- Criar sala
insert into db_cine_pipoca.tbl_sala (nome, status) values ("A", "ABERTA");

-- Criar assento
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (1, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (2, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (3, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (4, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (5, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (6, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (7, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (8, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (9, "VAZIO", 2);
insert into db_cine_pipoca.tbl_assento (numero, status, sala_id) values (10, "VAZIO",2);

-- Criar sessão
insert into db_cine_pipoca.tbl_sessao (status, data_hora_inicio, data_hora_fim, valor, filme_id, sala_sessao_id)
values ("DISPONIVEL", '2025-03-10 14:30:00', '2025-03-10 16:30:00', '40.00', 4, 2);

-- Criar reserva
insert into db_cine_pipoca.tbl_reserva (id_sessao_reserva, id_assento_reserva) values (4, 25);

-- Comprar ingresso
insert into db_cine_pipoca.tbl_ingresso (meia_entrada, valor_total, sessao_id, forma_pagamento, expira_em, status_validade, status_pagamento, reserva_id)
values
(0, '40.00', 4, "PIX", '2025-03-11 12:00:00', "NAO_EXPIRADO", "AGUARDANDO", 3);