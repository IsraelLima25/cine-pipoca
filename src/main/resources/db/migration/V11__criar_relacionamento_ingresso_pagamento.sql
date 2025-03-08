ALTER TABLE db_cine_pipoca.tbl_ingresso
ADD COLUMN pagamento_id BIGINT;

ALTER TABLE db_cine_pipoca.tbl_ingresso
ADD CONSTRAINT fk_pagamento
FOREIGN KEY (pagamento_id)
REFERENCES db_cine_pipoca.tbl_pagamento(id);