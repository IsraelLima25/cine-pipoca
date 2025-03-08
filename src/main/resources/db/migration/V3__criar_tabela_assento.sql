CREATE TABLE tbl_assento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    cliente_id BIGINT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES tbl_cliente(id)
);