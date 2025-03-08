ALTER TABLE db_cine_pipoca.tbl_sessao
ADD COLUMN filme_id BIGINT;

ALTER TABLE db_cine_pipoca.tbl_sessao
ADD CONSTRAINT fk_filme
FOREIGN KEY (filme_id)
REFERENCES db_cine_pipoca.tbl_filme(id);