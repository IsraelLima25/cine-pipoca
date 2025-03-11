ALTER TABLE db_cine_pipoca.tbl_assento
DROP FOREIGN KEY fk_cliente;

ALTER TABLE db_cine_pipoca.tbl_assento
DROP COLUMN cliente_id;

ALTER TABLE db_cine_pipoca.tbl_ingresso
DROP FOREIGN KEY fk_cliente_ingresso;

ALTER TABLE db_cine_pipoca.tbl_ingresso
DROP COLUMN cliente_id_ingresso;

DROP TABLE tbl_cliente;