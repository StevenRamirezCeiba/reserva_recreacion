INSERT INTO categoria(id, nombre, tarifa) VALUES(1, 'BRONCE', 50000);

INSERT INTO usuario(id, nombre, apellido, clave, numero_documento, reservas_acumulado, categoria_id, fecha_creacion) VALUES(1,'test', 'test', '1234', 123456789, 0, 1, now());