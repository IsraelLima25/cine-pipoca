CREATE TABLE tbl_ingresso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meia_entrada TINYINT(1) NOT NULL,
    valor_total DECIMAL(8,2) NOT NULL
);