-- Crear tablas si no existen
CREATE TABLE IF NOT EXISTS Users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     userEmail VARCHAR(255) NOT NULL,
    userPassword VARCHAR(255) NOT NULL,
    userRole VARCHAR(50) NOT NULL,
    userState VARCHAR(50) NOT NULL,
    age INT NOT NULL
    );

CREATE TABLE IF NOT EXISTS Profile (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       user_id INT,
                                       name VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    gender VARCHAR(50),
    location VARCHAR(255),
    bio TEXT,
    interests TEXT,
    profilePhoto VARCHAR(255),
    isOnline BOOLEAN,
    lastActive TIMESTAMP,
    preferredRelationship VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES Users(id)
    );

CREATE TABLE IF NOT EXISTS Preferences (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           user_id INT,
                                           minAgeRange INT,
                                           maxAgeRange INT,
                                           maxDistance INT,
                                           favoriteGender VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES Users(id)
    );

CREATE TABLE IF NOT EXISTS Matches (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       user1_id INT,
                                       user2_id INT,
                                       matchState VARCHAR(50),
    FOREIGN KEY (user1_id) REFERENCES Users(id),
    FOREIGN KEY (user2_id) REFERENCES Users(id)
    );

CREATE TABLE IF NOT EXISTS Messages (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        match_id INT,
                                        senderUser_id INT,
                                        receiverUser_id INT,
                                        content TEXT,
                                        isRead BOOLEAN,
                                        FOREIGN KEY (match_id) REFERENCES Matches(id),
    FOREIGN KEY (senderUser_id) REFERENCES Users(id),
    FOREIGN KEY (receiverUser_id) REFERENCES Users(id)
    );

CREATE TABLE IF NOT EXISTS Likes (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     liker_id INT,
                                     liked_id INT,
                                     state VARCHAR(50),
    FOREIGN KEY (liker_id) REFERENCES Users(id),
    FOREIGN KEY (liked_id) REFERENCES Users(id)
    );

CREATE TABLE IF NOT EXISTS Blocks (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      reporter_id INT,
                                      reported_id INT,
                                      blockDate TIMESTAMP,
                                      blockReason TEXT,
                                      FOREIGN KEY (reporter_id) REFERENCES Users(id),
    FOREIGN KEY (reported_id) REFERENCES Users(id)
    );

-- Insertar Datos en la Tabla Users
INSERT INTO Users (userEmail, userPassword, userRole, userState, age)
VALUES
    ('juan.perez@ejemplo.com', 'contraseña_cifrada_1', 'user', 'active', 28),
    ('maria.garcia@ejemplo.com', 'contraseña_cifrada_2', 'user', 'active', 32),
    ('admin@ejemplo.com', 'contraseña_cifrada_3', 'admin', 'active', 35)
    ON DUPLICATE KEY UPDATE userEmail=userEmail; -- Evita duplicados si ya existen.

-- Insertar Datos en la Tabla Profile
INSERT INTO Profile (user_id, name, lastName, gender, location, bio, interests, profilePhoto, isOnline, lastActive, preferredRelationship)
VALUES
    (1, 'Juan', 'Pérez', 'male', 'Madrid, España', 'Amante de los libros y el café.', 'Leer, Senderismo', 'https://ejemplo.com/imagenes/juan.jpg', 1, NOW(), 'friendship'),
    (2, 'María', 'García', 'female', 'Barcelona, España', 'Entusiasta de la tecnología y jugadora.', 'Videojuegos, Viajar', 'https://ejemplo.com/imagenes/maria.jpg', 0, NOW(), 'serious'),
    (3, 'Admin', 'Usuario', 'male', 'Global', 'Cuenta de administrador del sistema.', '', 'https://ejemplo.com/imagenes/admin.jpg', 0, NOW(), 'casual')
    ON DUPLICATE KEY UPDATE user_id=user_id; -- Evita duplicados si ya existen.

-- Insertar Datos en la Tabla Preferences
INSERT INTO Preferences (user_id, minAgeRange, maxAgeRange, maxDistance, favoriteGender)
VALUES
    (1, 25, 35, 50, 'female'),
    (2, 30, 40, 100, 'male'),
    (3, 18, 99, 1000, 'other')
    ON DUPLICATE KEY UPDATE user_id=user_id; -- Evita duplicados si ya existen.

-- Insertar Datos en la Tabla Matches
INSERT INTO Matches (user1_id, user2_id, matchState)
VALUES
    (1, 2, 'pending'),
    (2, 3, 'accepted'),
    (1, 3, 'rejected')
    ON DUPLICATE KEY UPDATE user1_id=user1_id; -- Evita duplicados si ya existen.

-- Insertar Datos en la Tabla Messages
INSERT INTO Messages (match_id, senderUser_id, receiverUser_id, content, isRead)
VALUES
    (1, 1, 2, '¡Hola! ¿Cómo estás?', 0),
    (1, 2, 1,'Estoy bien, ¡gracias! ¿Y tú?', 0),
    (2, 2, 2,'¡Hola! ¿Nos vemos pronto?', 1)
    ON DUPLICATE KEY UPDATE match_id=match_id; -- Evita duplicados si ya existen.

-- Insertar Datos en la Tabla Likes
INSERT INTO Likes (liker_id, liked_id, state)
VALUES
    (1, 2, 'pending'),
    (2, 1, 'accepted'),
    (3, 1, 'rejected')
    ON DUPLICATE KEY UPDATE liker_id=liker_id; -- Evita duplicados si ya existen.

-- Insertar Datos en la Tabla Blocks
INSERT INTO Blocks (reporter_id, reported_id, blockDate, blockReason)
VALUES
    (1, 3, NOW(), 'Envío de mensajes no deseados'),
    (2, 3, NOW(), 'Contenido inapropiado'),
    (3, 1, NOW(), 'Bloqueado por decisión del administrador')
    ON DUPLICATE KEY UPDATE reporter_id=reporter_id; -- Evita duplicados si ya existen.
