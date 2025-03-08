ALTER TABLE db_cine_pipoca.tbl_ingresso
ADD COLUMN cliente_id_ingresso BIGINT;

ALTER TABLE db_cine_pipoca.tbl_ingresso
ADD CONSTRAINT fk_cliente_ingresso
FOREIGN KEY (cliente_id_ingresso)
REFERENCES db_cine_pipoca.tbl_cliente(id);