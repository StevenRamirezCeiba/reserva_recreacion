INSERT INTO categoria(nombre, tarifa) VALUES('BRONCE', 50000);

INSERT INTO usuario(nombre, apellido, clave, numero_documento, reservas_acumulado, categoria_id, fecha_creacion) VALUES('test', 'test', '1234', 123456789, 0, 1, now());