ALTER TABLE db_cine_pipoca.tbl_assento
ADD COLUMN sala_id BIGINT;

ALTER TABLE db_cine_pipoca.tbl_assento
ADD CONSTRAINT fk_sala
FOREIGN KEY (sala_id)
REFERENCES db_cine_pipoca.tbl_sala(id)
ON DELETE CASCADE;