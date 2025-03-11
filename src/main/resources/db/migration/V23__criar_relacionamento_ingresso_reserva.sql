ALTER TABLE db_cine_pipoca.tbl_ingresso
ADD COLUMN reserva_id BIGINT NOT NULL;

ALTER TABLE db_cine_pipoca.tbl_ingresso
ADD CONSTRAINT fk_reserva_ingresso
FOREIGN KEY (reserva_id)
REFERENCES db_cine_pipoca.tbl_reserva(id);
