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

-- Creación de la tabla Instructor
CREATE TABLE instructor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    especialidad VARCHAR(255) NOT NULL,
    experiencia INT NOT NULL,
    contacto VARCHAR(255) NOT NULL
);

-- Creación de la tabla Pago
CREATE TABLE pago (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    reserva_id BIGINT, -- Opcional, puede estar vacío
    monto DECIMAL(10, 2) NOT NULL,
    fecha DATE NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (reserva_id) REFERENCES reserva(id) ON DELETE SET NULL
);

-- Creación de la tabla Notificacion
CREATE TABLE notificacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('RECORDATORIO', 'CONFIRMACION') NOT NULL,
    mensaje TEXT NOT NULL,
    destinatario_id BIGINT NOT NULL,
    fecha_envio TIMESTAMP NOT NULL,
    estado ENUM('ENVIADO', 'PENDIENTE') NOT NULL,
    FOREIGN KEY (destinatario_id) REFERENCES usuario(id)
);

-- Creación de la tabla Paquete
CREATE TABLE paquete (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_paquete VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    duracion INT NOT NULL  -- duración en días
);

-- Tabla intermedia para la relación entre Paquete y ClaseDeBaile
CREATE TABLE paquete_clase (
    paquete_id BIGINT NOT NULL,
    clase_id BIGINT NOT NULL,s
    PRIMARY KEY (paquete_id, clase_id),
    FOREIGN KEY (paquete_id) REFERENCES paquete(id),
     FOREIGN KEY (clase_id) REFERENCES clase_de_baile(id)
);

-- Guarda el tipo de membresía que un usuario puede adquirir para acceder a clases o beneficios exclusivos.
CREATE TABLE membresia (
    id_membresia BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    nombre_membresia VARCHAR(255) NOT NULL,
    beneficios TEXT,
    descuento DECIMAL(3, 2) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

-- Guarda las reseñas o calificaciones que los usuarios dejan sobre las clases o estudios de baile.
CREATE TABLE resenia(
    id_resenia BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_clase BIGINT,  -- Opcional, puede estar Nulo
    id_estudio BIGINT,  -- Opcional, puede estar Nulo
    puntuacion ENUM('1', '2', '3', '4', '5') NOT NULL,
    comentario TEXT,
    fecha DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_clase) REFERENCES clase_de_baile(id) ON DELETE SET NULL,
    FOREIGN KEY (id_estudio) REFERENCES estudio_de_baile(id) ON DELETE SET NULL
);


