ALTER TABLE db_cine_pipoca.tbl_ingresso
ADD COLUMN sessao_id BIGINT;

ALTER TABLE db_cine_pipoca.tbl_ingresso
ADD CONSTRAINT fk_sessao
FOREIGN KEY (sessao_id)
REFERENCES db_cine_pipoca.tbl_sessao(id);