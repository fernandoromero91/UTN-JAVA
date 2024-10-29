-- Creación de la tabla Usuario
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    rol ENUM('USER', 'ADMIN') NOT NULL
);

-- Creación de la tabla ClaseDeBaile
CREATE TABLE clase_de_baile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    instructor VARCHAR(255),
    nivelDificultad VARCHAR(50),
    duracion INT,
    horario VARCHAR(50),
    capacidad INT,
    precio DECIMAL(10, 2)
);

-- Creación de la tabla EstudioDeBaile
CREATE TABLE estudio_de_baile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    ubicacion VARCHAR(255),
    capacidad INT,
    precioHora DECIMAL(10, 2),
    disponibilidad BOOLEAN
);

-- Creación de la tabla Reserva
CREATE TABLE reserva (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT,
    clase_id BIGINT,
    estudio_id BIGINT,
    fecha DATE,
    hora TIME,
    duracion INT,
    estado ENUM('confirmada', 'cancelada', 'modificada'),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (clase_id) REFERENCES clase_de_baile(id),
    FOREIGN KEY (estudio_id) REFERENCES estudio_de_baile(id)
);