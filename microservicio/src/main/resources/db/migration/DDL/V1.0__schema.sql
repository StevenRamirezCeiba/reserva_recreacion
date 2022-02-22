-- Creacion de tablas
CREATE TABLE categoria (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  tarifa DECIMAL(20,2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE usuario (
  id INT NOT NULL AUTO_INCREMENT,
  clave VARCHAR(45) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  numero_documento INT(10) NOT NULL,
  reservas_acumulado TINYINT(1) NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  categoria_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE reserva_estado (
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reserva (
  id INT NOT NULL AUTO_INCREMENT,
  valor DECIMAL(20,2) NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  fecha_reserva DATETIME NOT NULL,
  usuario_id INT NOT NULL,
  reserva_estado_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  FOREIGN KEY (reserva_estado_id) REFERENCES reserva_estado(id)
);

-- Insertar valores por defecto

-- categoria
INSERT INTO categoria(nombre, tarifa) VALUES('BRONCE', 50000);

-- reserva_estado
INSERT INTO reserva_estado(nombre) VALUES('CONFIRMADA');
INSERT INTO reserva_estado(nombre) VALUES('CANCELADA');

-- usuario
INSERT INTO usuario(clave, nombre, apellido, numero_documento, reservas_acumulado, fecha_creacion, categoria_id) VALUES('12345', 'CHRISTIAN', 'RAMIREZ', 1075318997, 0, NOW(), 1);
