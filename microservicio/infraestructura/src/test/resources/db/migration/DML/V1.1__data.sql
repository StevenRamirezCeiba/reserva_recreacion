INSERT INTO usuario(id, nombre, apellido, clave, numero_documento, reservas_acumulado, categoria_id,
fecha_creacion) VALUES(1, 'test', 'test', '1234', 123456789, 0, 1, now());

INSERT INTO reserva(id, valor, fecha_creacion, fecha_reserva, usuario_id, reserva_estado_id)
VALUES(1, 50000, now(), now() + INTERVAL 3 DAY, 1, 1);