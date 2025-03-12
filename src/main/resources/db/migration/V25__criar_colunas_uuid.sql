ALTER TABLE tbl_assento ADD COLUMN uuid CHAR(36) NOT NULL;
ALTER TABLE tbl_filme ADD COLUMN uuid CHAR(36) NOT NULL;
ALTER TABLE tbl_ingresso ADD COLUMN uuid CHAR(36) NOT NULL;
ALTER TABLE tbl_reserva ADD COLUMN uuid CHAR(36) NOT NULL;
ALTER TABLE tbl_sala ADD COLUMN uuid CHAR(36) NOT NULL;
ALTER TABLE tbl_sessao ADD COLUMN uuid CHAR(36) NOT NULL;

UPDATE tbl_assento SET uuid = UUID();
UPDATE tbl_filme SET uuid = UUID();
UPDATE tbl_ingresso SET uuid = UUID();
UPDATE tbl_reserva SET uuid = UUID();
UPDATE tbl_sala SET uuid = UUID();
UPDATE tbl_sessao SET uuid = UUID();