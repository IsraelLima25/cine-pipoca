CREATE TABLE tbl_reserva (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_sessao_reserva BIGINT NOT NULL,
    id_assento_reserva BIGINT NOT NULL
);

ALTER TABLE db_cine_pipoca.tbl_reserva
ADD CONSTRAINT fk_reserva_sessao
FOREIGN KEY (id_sessao_reserva)
REFERENCES db_cine_pipoca.tbl_sessao(id);

ALTER TABLE db_cine_pipoca.tbl_reserva
ADD CONSTRAINT fk_reserva_assento
FOREIGN KEY (id_assento_reserva)
REFERENCES db_cine_pipoca.tbl_assento(id);