ALTER TABLE tbl_cliente
ADD COLUMN cpf VARCHAR(11) NOT NULL,
ADD CONSTRAINT UNIQUE (cpf);