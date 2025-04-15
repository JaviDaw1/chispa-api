-- Crear la base de datos "chispa" si no existe
CREATE
DATABASE IF NOT EXISTS chispa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos "chispa"
USE
chispa;

-- Crear las tablas si no existen
CREATE TABLE IF NOT EXISTS user_ (
                                     id
                                     INT
                                     AUTO_INCREMENT
                                     PRIMARY
                                     KEY,
                                     firstname
                                     VARCHAR
(
                                     100
) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') DEFAULT 'USER'
    );

CREATE TABLE IF NOT EXISTS profile (
                                       id
                                       INT
                                       AUTO_INCREMENT
                                       PRIMARY
                                       KEY,
                                       user_id
                                       INT
                                       NOT
                                       NULL,
                                       name
                                       VARCHAR
(
                                       100
) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    birthDate DATE NOT NULL,
    location VARCHAR(255),
    bio TEXT,
    interests TEXT,
    profilePhoto VARCHAR(255),
    isOnline TINYINT(1) DEFAULT 0,
    lastActive DATETIME,
    preferredRelationship ENUM('FRIENDSHIP', 'CASUAL', 'SERIOUS') DEFAULT 'FRIENDSHIP',
    FOREIGN KEY (user_id) REFERENCES user_(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS preferences (
                                           id
                                           INT
                                           AUTO_INCREMENT
                                           PRIMARY
                                           KEY,
                                           user_id
                                           INT
                                           NOT
                                           NULL,
                                           minAgeRange
                                           INT
                                           NOT
                                           NULL,
                                           maxAgeRange
                                           INT
                                           NOT
                                           NULL,
                                           maxDistance
                                           INT
                                           NOT
                                           NULL,
                                           favoriteGender
                                           ENUM
(
                                           'MALE',
                                           'FEMALE',
                                           'OTHER'
) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS matches (
                                       id
                                       INT
                                       AUTO_INCREMENT
                                       PRIMARY
                                       KEY,
                                       user1_id
                                       INT
                                       NOT
                                       NULL,
                                       user2_id
                                       INT
                                       NOT
                                       NULL,
                                       matchDate
                                       DATETIME
                                       DEFAULT
                                       CURRENT_TIMESTAMP,
                                       matchState
                                       ENUM
(
                                       'PENDING',
                                       'ACCEPTED',
                                       'REJECTED',
                                       'CANCELLED'
) DEFAULT 'PENDING',
    FOREIGN KEY (user1_id) REFERENCES user_(id) ON DELETE CASCADE,
    FOREIGN KEY (user2_id) REFERENCES user_(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS messages (
                                        id
                                        INT
                                        AUTO_INCREMENT
                                        PRIMARY
                                        KEY,
                                        match_id
                                        INT
                                        NOT
                                        NULL,
                                        senderUser_id
                                        INT
                                        NOT
                                        NULL,
                                        receiverUser_id
                                        INT
                                        NOT
                                        NULL,
                                        content
                                        TEXT
                                        NOT
                                        NULL,
                                        timestamp
                                        DATETIME
                                        DEFAULT
                                        CURRENT_TIMESTAMP,
                                        isRead
                                        TINYINT
(
                                        1
) DEFAULT 0,
    FOREIGN KEY
(
    match_id
) REFERENCES matches
(
    id
) ON DELETE CASCADE,
    FOREIGN KEY (senderUser_id) REFERENCES user_(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS likes (
                                     id
                                     INT
                                     AUTO_INCREMENT
                                     PRIMARY
                                     KEY,
                                     liker_id
                                     INT
                                     NOT
                                     NULL,
                                     liked_id
                                     INT
                                     NOT
                                     NULL,
                                     timestamp
                                     DATETIME
                                     DEFAULT
                                     CURRENT_TIMESTAMP,
                                     state
                                     ENUM
(
                                     'PENDING',
                                     'ACCEPTED',
                                     'REJECTED'
) DEFAULT 'PENDING',
    FOREIGN KEY (liker_id) REFERENCES user_(id) ON DELETE CASCADE,
    FOREIGN KEY (liked_id) REFERENCES user_(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS blocks (
                                      id
                                      INT
                                      AUTO_INCREMENT
                                      PRIMARY
                                      KEY,
                                      reporter_id
                                      INT
                                      NOT
                                      NULL,
                                      reported_id
                                      INT
                                      NOT
                                      NULL,
                                      blockDate
                                      DATETIME
                                      DEFAULT
                                      CURRENT_TIMESTAMP,
                                      blockReason
                                      TEXT,
                                      FOREIGN
                                      KEY
(
                                      reporter_id
) REFERENCES user_
(
    id
) ON DELETE CASCADE,
    FOREIGN KEY (reported_id) REFERENCES user_(id) ON DELETE CASCADE
    );

-- Insertar datos en la tabla user_
INSERT INTO user_ (firstname, lastname, username, email, password, role)
VALUES
    ('Juan', 'Perez', 'juan.perez', 'juan.perez@ejemplo.com', 'contraseña_cifrada_1', 'USER'),
    ('Maria', 'Garcia', 'maria.garcia', 'maria.garcia@ejemplo.com', 'contraseña_cifrada_2', 'USER'),
    ('Admin', 'Admin', 'admin', 'admin@ejemplo.com', 'contraseña_cifrada_3', 'ADMIN') ON DUPLICATE KEY
UPDATE email=email;

-- Insertar datos en la tabla profile (sin el campo "age" ya que se calcula dinámicamente)
INSERT INTO profile (user_id, name, lastName, gender, birthDate, location,
                     bio, interests, profilePhoto, isOnline, lastActive, preferredRelationship)
VALUES (1, 'Juan', 'Pérez', 'MALE', '1990-01-15', 'Madrid, España', 'Amante de los libros y el café.',
        'Leer, Senderismo', 'https://ejemplo.com/imagenes/juan.jpg', 1, NOW(), 'FRIENDSHIP'),
       (2, 'María', 'García', 'FEMALE', '1985-05-22', 'Barcelona, España', 'Entusiasta de la tecnología y jugadora.',
        'Videojuegos, Viajar', 'https://ejemplo.com/imagenes/maria.jpg', 0, NOW(), 'SERIOUS'),
       (3, 'Admin', 'Usuario', 'MALE', '1980-11-30', 'Global', 'Cuenta de administrador del sistema.', '',
        'https://ejemplo.com/imagenes/admin.jpg', 0, NOW(), 'CASUAL') ON DUPLICATE KEY
UPDATE user_id=user_id;

-- Insertar datos en la tabla preferences
INSERT INTO preferences (user_id, minAgeRange, maxAgeRange, maxDistance, favoriteGender)
VALUES
    (1, 25, 35, 50, 'FEMALE'),
    (2, 30, 40, 100, 'MALE'),
    (3, 18, 99, 1000, 'OTHER') ON DUPLICATE KEY
UPDATE user_id=user_id;

-- Insertar datos en la tabla matches
INSERT INTO matches (user1_id, user2_id, matchState)
VALUES
    (1, 2, 'PENDING'),
    (2, 3, 'ACCEPTED'),
    (1, 3, 'REJECTED') ON DUPLICATE KEY
UPDATE user1_id=user1_id;

-- Insertar datos en la tabla messages
INSERT INTO messages (match_id, senderUser_id, receiverUser_id, content, isRead)
VALUES
    (1, 1, 2, '¡Hola! ¿Cómo estás?', 0),
    (1, 2, 1, 'Estoy bien, ¡gracias! ¿Y tú?', 0),
    (2, 2, 2, '¡Hola! ¿Nos vemos pronto?', 1) ON DUPLICATE KEY
UPDATE match_id=match_id;

-- Insertar datos en la tabla likes
INSERT INTO likes (liker_id, liked_id, state)
VALUES
    (1, 2, 'PENDING'),
    (2, 1, 'ACCEPTED'),
    (3, 1, 'REJECTED') ON DUPLICATE KEY
UPDATE liker_id=liker_id;

-- Insertar datos en la tabla blocks
INSERT INTO blocks (reporter_id, reported_id, blockDate, blockReason)
VALUES
    (1, 3, NOW(), 'Envío de mensajes no deseados'),
    (2, 3, NOW(), 'Contenido inapropiado'),
    (3, 1, NOW(), 'Bloqueado por decisión del administrador') ON DUPLICATE KEY
UPDATE reporter_id=reporter_id;
