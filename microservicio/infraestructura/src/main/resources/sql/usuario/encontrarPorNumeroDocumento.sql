select usu.id, usu.nombre, usu.apellido, usu.numero_documento, usu.reservas_acumulado,
usu.categoria_id, cat.nombre as categoria_nombre, usu.fecha_creacion
from usuario usu
inner join categoria cat on usu.categoria_id = cat.id
where numero_documento = :numeroDocumento