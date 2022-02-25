select id,nombre,apellido,numero_documento,reservas_acumulado,categoria_id,fecha_creacion,
null as categoria_nombre
from usuario where id = :id