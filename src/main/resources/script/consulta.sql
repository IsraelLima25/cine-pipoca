SELECT * FROM
db_cine_pipoca.tbl_reserva tr
INNER JOIN
db_cine_pipoca.tbl_sessao ts
ON
tr.id_sessao_reserva = ts.id
INNER JOIN
db_cine_pipoca.tbl_assento ta
ON
tr.id_assento_reserva = ta.id
INNER JOIN
db_cine_pipoca.tbl_ingresso ti
ON
ti.reserva_id = tr.id
WHERE
ts.filme_id = 6
AND
ta.uuid = "9f89cf76-ff78-11ef-9240-aecc67e49cb4";