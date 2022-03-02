select res.id,res.valor,res.fecha_creacion,res.fecha_reserva,res.usuario_id,
res.reserva_estado_id, ree.nombre as reserva_estado_nombre
from reserva res
inner join usuario usu on res.usuario_id = usu.id
inner join reserva_estado ree on res.reserva_estado_id = ree.id
where usu.numero_documento = :numeroDocumento