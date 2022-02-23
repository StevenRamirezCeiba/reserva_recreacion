SELECT cat.tarifa FROM usuario usu
INNER JOIN categoria cat ON usu.categoria_id = cat.id
WHERE usu.id = :id