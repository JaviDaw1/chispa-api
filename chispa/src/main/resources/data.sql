DROP DATABASE chispa;

-- Crear la base de datos "chispa" si no existe --
CREATE DATABASE IF NOT EXISTS chispa
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos "chispa" --
USE chispa;


-- Tabla de usuarios --
CREATE TABLE IF NOT EXISTS user_ (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') DEFAULT 'USER',
    reset_token VARCHAR(255),
    reset_token_expiry DATETIME
    );


-- Tabla perfil --
CREATE TABLE IF NOT EXISTS profile (
     id INT AUTO_INCREMENT PRIMARY KEY,
     user_id INT NOT NULL,
     name VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    birthDate DATE NOT NULL,
    location VARCHAR(255),
    bio TEXT,
    interests TEXT,
    profilePhoto VARCHAR(5000),
    isOnline TINYINT(1) DEFAULT 0,
    lastActive DATETIME,
    preferredRelationship ENUM('FRIENDSHIP', 'CASUAL', 'SERIOUS', 'LONG_TERM', 'OPEN', 'HOOKUP', 'MARRIAGE', 'NOT_SURE') DEFAULT 'FRIENDSHIP',
    FOREIGN KEY (user_id) REFERENCES user_(id) ON DELETE CASCADE
    );


-- Tabla de preferencias de usuario --
CREATE TABLE IF NOT EXISTS preferences (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    minAgeRange INT NOT NULL,
    maxAgeRange INT NOT NULL,
    maxDistance INT NOT NULL,
    favoriteGender ENUM('MALE', 'FEMALE', 'OTHER') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user_(id) ON DELETE CASCADE
    );


-- Tabla de coincidencias (matches) --
CREATE TABLE IF NOT EXISTS matches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user1_id INT NOT NULL,
    user2_id INT NOT NULL,
    matchDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    matchState ENUM('MATCHED', 'BLOCKED') DEFAULT 'MATCHED',
    FOREIGN KEY (user1_id) REFERENCES user_(id) ON DELETE CASCADE,
    FOREIGN KEY (user2_id) REFERENCES user_(id) ON DELETE CASCADE
    );


-- Tabla de mensajes entre usuarios --
CREATE TABLE IF NOT EXISTS messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    match_id INT NOT NULL,
    senderUser_id INT NOT NULL,
    receiverUser_id INT NOT NULL,
    content TEXT NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    sRead TINYINT(1) DEFAULT 0,
    messageState ENUM('SEND', 'BLOCKED') DEFAULT 'SEND',
    FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE,
    FOREIGN KEY (senderUser_id) REFERENCES user_(id) ON DELETE CASCADE
    );


-- Tabla de "me gusta" entre usuarios --
CREATE TABLE IF NOT EXISTS likes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    liker_id INT NOT NULL,
    liked_id INT NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    state ENUM('LIKED', 'BLOCKED') DEFAULT 'LIKED',
    FOREIGN KEY (liker_id) REFERENCES user_(id) ON DELETE CASCADE,
    FOREIGN KEY (liked_id) REFERENCES user_(id) ON DELETE CASCADE
    );


-- Tabla de bloqueos entre usuarios --
CREATE TABLE IF NOT EXISTS blocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reporter_id INT NOT NULL,
    reported_id INT NOT NULL,
    blockDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    blockReason TEXT,
    FOREIGN KEY (reporter_id) REFERENCES user_(id) ON DELETE CASCADE,
    FOREIGN KEY (reported_id) REFERENCES user_(id) ON DELETE CASCADE
    );


-- Usuarios --
INSERT INTO user_ (firstname, lastname, username, email, password, role)
VALUES
    ('Ana', 'García', 'ana.g', 'ana@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Admin', 'admin', 'admin', 'admin@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'ADMIN'),
    ('Carla', 'López', 'carla.l', 'carla@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Mario', 'Ruiz', 'mario.r', 'mario@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Laura', 'Martínez', 'laura.m', 'laura@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Javier', 'Torres', 'javi.t', 'javit@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Paula', 'Navarro', 'paula.n', 'paula@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Diego', 'Sánchez', 'diego.s', 'diego@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Marta', 'Romero', 'marta.r', 'marta@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Hugo', 'Castro', 'hugo.c', 'hugo@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Nuria', 'Alonso', 'nuria.a', 'nuria@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Carlos', 'Iglesias', 'carlos.i', 'carlos@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Sara', 'Domínguez', 'sara.d', 'sara@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Pablo', 'Rey', 'pablo.r', 'pablo@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Elena', 'Flores', 'elena.f', 'elena@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Andrés', 'Luna', 'andres.l', 'andres@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Lucía', 'Campos', 'lucia.c', 'lucia@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Fernando', 'Morales', 'fernando.m', 'fernando@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Silvia', 'Vega', 'silvia.v', 'silvia@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER'),
    ('Óscar', 'Nieto', 'oscar.n', 'oscar@gmail.com', '$2a$10$yc1oAMv7Wwsb.pFem7bKU.df9Sc9ADx9Zn0oZgG/g7HtpaSTIHv66', 'USER') ON DUPLICATE KEY
UPDATE email=email;


-- Perfiles
INSERT INTO profile (user_id, name, lastName, gender, birthDate, location, bio, interests, profilePhoto, isOnline,
                     lastActive, preferredRelationship)
VALUES(1, 'Ana', 'García', 'FEMALE', '1994-06-12', 'Madrid', 'Hola, soy Ana y me encanta conocer gente nueva.', 'música, viajes, cine', 'https://randomuser.me/api/portraits/women/1.jpg', 1, '2025-05-13 09:32:00', 'FRIENDSHIP'),
      (2, 'Luis', 'Pérez', 'MALE', '1989-03-04', 'Barcelona', 'Hola, soy Luis y me encanta conocer gente nueva.', 'deporte, lectura', 'https://randomuser.me/api/portraits/men/2.jpg', 0, '2025-05-12 20:10:00', 'SERIOUS'),
      (3, 'Carla', 'López', 'FEMALE', '1992-09-27', 'Valencia', 'Hola, soy Carla y me encanta conocer gente nueva.', 'arte, naturaleza', 'https://randomuser.me/api/portraits/women/3.jpg', 1, '2025-05-13 11:00:00', 'CASUAL'),
      (4, 'Mario', 'Ruiz', 'MALE', '1990-12-15', 'Sevilla', 'Hola, soy Mario y me encanta conocer gente nueva.', 'tecnología, videojuegos', 'https://randomuser.me/api/portraits/men/4.jpg', 0, '2025-05-12 15:45:00', 'FRIENDSHIP'),
      (5, 'Laura', 'Martínez', 'FEMALE', '1996-02-20', 'Granada', 'Hola, soy Laura y me encanta conocer gente nueva.', 'música, viajes, cine', 'https://randomuser.me/api/portraits/women/5.jpg', 0, '2025-05-11 18:20:00', 'CASUAL'),
      (6, 'Javier', 'Torres', 'MALE', '1987-11-09', 'Málaga', 'Hola, soy Javier y me encanta conocer gente nueva.', 'deporte, lectura', 'https://randomuser.me/api/portraits/men/6.jpg', 1, '2025-05-13 07:15:00', 'SERIOUS'),
      (7, 'Paula', 'Navarro', 'FEMALE', '1993-08-01', 'Zaragoza', 'Hola, soy Paula y me encanta conocer gente nueva.', 'arte, naturaleza', 'https://randomuser.me/api/portraits/women/7.jpg', 0, '2025-05-12 22:10:00', 'FRIENDSHIP'),
      (8, 'Diego', 'Sánchez', 'MALE', '1991-01-17', 'Madrid', 'Hola, soy Diego y me encanta conocer gente nueva.', 'tecnología, videojuegos', 'https://randomuser.me/api/portraits/men/8.jpg', 0, '2025-05-11 14:30:00', 'CASUAL'),
      (9, 'Marta', 'Romero', 'FEMALE', '1995-07-21', 'Barcelona', 'Hola, soy Marta y me encanta conocer gente nueva.', 'música, viajes, cine', 'https://randomuser.me/api/portraits/women/9.jpg', 1, '2025-05-13 10:00:00', 'SERIOUS'),
      (10, 'Hugo', 'Castro', 'MALE', '1990-04-03', 'Valencia', 'Hola, soy Hugo y me encanta conocer gente nueva.', 'arte, naturaleza', 'https://randomuser.me/api/portraits/men/10.jpg', 0, '2025-05-12 17:00:00', 'FRIENDSHIP'),
      (11, 'Nuria', 'Alonso', 'FEMALE', '1994-05-16', 'Alicante', 'Hola, soy Nuria, y me encanta pasar tiempo con amigos y disfrutar del arte.', 'arte, teatro, lectura', 'https://randomuser.me/api/portraits/women/11.jpg', 0, '2025-05-11 21:40:00', 'CASUAL'),
      (12, 'Carlos', 'Iglesias', 'MALE', '1988-10-23', 'Madrid', 'Hola, soy Carlos, un amante del cine y la música.', 'cine, música', 'https://randomuser.me/api/portraits/men/12.jpg', 1, '2025-05-13 09:10:00', 'FRIENDSHIP'),
      (13, 'Sara', 'Domínguez', 'FEMALE', '1990-04-14', 'Murcia', 'Soy Sara y me encanta el deporte y la vida al aire libre.', 'deporte, senderismo', 'https://randomuser.me/api/portraits/women/13.jpg', 0, '2025-05-10 22:30:00', 'SERIOUS'),
      (14, 'Pablo', 'Rey', 'MALE', '1993-01-11', 'Sevilla', 'Soy Pablo, me gusta disfrutar de una buena conversación y un café.', 'lectura, cine, viajar', 'https://randomuser.me/api/portraits/men/14.jpg', 1, '2025-05-13 08:45:00', 'CASUAL'),
      (15, 'Elena', 'Flores', 'FEMALE', '1992-12-09', 'Barcelona', 'Soy Elena y me encanta conocer nuevas personas y aprender de ellas.', 'arte, fotografía', 'https://randomuser.me/api/portraits/women/15.jpg', 0, '2025-05-11 19:10:00', 'FRIENDSHIP'),
      (16, 'Andrés', 'Luna', 'MALE', '1991-03-20', 'Valencia', 'Soy Andrés, un amante de la música y la cultura en general.', 'música, cine, teatro', 'https://randomuser.me/api/portraits/men/16.jpg', 1, '2025-05-13 10:30:00', 'SERIOUS'),
      (17, 'Lucía', 'Campos', 'FEMALE', '1994-08-12', 'Zaragoza', 'Hola, soy Lucía, me encanta la naturaleza y las caminatas largas.', 'senderismo, naturaleza', 'https://randomuser.me/api/portraits/women/17.jpg', 0, '2025-05-11 20:50:00', 'CASUAL'),
      (18, 'Fernando', 'Morales', 'MALE', '1987-06-28', 'Alicante', 'Soy Fernando y disfruto del cine clásico y la fotografía.', 'cine, fotografía', 'https://randomuser.me/api/portraits/men/18.jpg', 1, '2025-05-13 07:00:00', 'FRIENDSHIP'),
      (19, 'Silvia', 'Vega', 'FEMALE', '1995-11-03', 'Sevilla', 'Soy Silvia y amo el arte en todas sus formas.', 'arte, música', 'https://randomuser.me/api/portraits/women/19.jpg', 0, '2025-05-12 21:00:00', 'SERIOUS'),
      (20, 'Óscar', 'Nieto', 'MALE', '1990-07-15', 'Madrid', 'Soy Óscar y disfruto de la lectura y la buena comida.', 'lectura, gastronomía', 'https://randomuser.me/api/portraits/men/20.jpg', 1, '2025-05-13 06:30:00', 'CASUAL') ON DUPLICATE KEY
UPDATE user_id=user_id;


-- Preferencias
INSERT INTO preferences (user_id, minAgeRange, maxAgeRange, maxDistance, favoriteGender)
VALUES
    (1, 18, 35, 100, 'FEMALE'),
    (2, 18, 35, 100, 'FEMALE'),
    (3, 18, 30, 50, 'MALE'),
    (4, 18, 40, 100, 'FEMALE'),
    (5, 20, 30, 75, 'MALE'),
    (6, 18, 30, 60, 'FEMALE'),
    (7, 18, 30, 50, 'MALE'),
    (8, 18, 35, 100, 'FEMALE'),
    (9, 18, 30, 60, 'MALE'),
    (10, 20, 35, 50, 'FEMALE'),
    (11, 18, 30, 50, 'MALE'),
    (12, 18, 30, 75, 'FEMALE'),
    (13, 20, 35, 60, 'MALE'),
    (14, 18, 30, 100, 'FEMALE'),
    (15, 20, 40, 50, 'MALE'),
    (16, 18, 35, 100, 'FEMALE'),
    (17, 18, 30, 50, 'MALE'),
    (18, 18, 40, 75, 'FEMALE'),
    (19, 20, 35, 60, 'MALE'),
    (20, 18, 35, 100, 'FEMALE') ON DUPLICATE KEY
UPDATE user_id=user_id;
