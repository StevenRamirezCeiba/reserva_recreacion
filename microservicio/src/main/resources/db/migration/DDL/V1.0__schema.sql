CREATE TABLE categoria (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  tarifa DOUBLE(19,2) NOT NULL,
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

CREATE TABLE reserva (
  id INT NOT NULL AUTO_INCREMENT,
  valor DOUBLE(19,2) NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  fecha_reserva DATETIME NOT NULL,
  usuario_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);