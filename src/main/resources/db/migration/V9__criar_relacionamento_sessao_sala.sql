ALTER TABLE db_cine_pipoca.tbl_sessao
ADD COLUMN sala_sessao_id BIGINT;

ALTER TABLE db_cine_pipoca.tbl_sessao
ADD CONSTRAINT fk_sala_sessao
FOREIGN KEY (sala_sessao_id)
REFERENCES db_cine_pipoca.tbl_sala(id);