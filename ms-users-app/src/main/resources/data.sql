CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE
);

INSERT INTO users (name, lastname, username, password, email) VALUES
('Juan', 'Perez', 'juanp', '1234', 'juan@example.com'),
('María', 'Gomez', 'mariag', 'abcd', 'maria@example.com'),
('Luis', 'Rodriguez', 'luisr', 'pass1', 'luis@example.com'),
('Ana', 'Martinez', 'anam', 'clave', 'ana@example.com'),
('Carlos', 'Fernandez', 'carlosf', 'qwerty', 'carlos@example.com'),
('Lucía', 'Diaz', 'luciad', 'asdfg', 'lucia@example.com'),
('Javier', 'Sanchez', 'javis', 'javi123', 'javier@example.com'),
('Sofía', 'Romero', 'sofiar', 'sofia456', 'sofia@example.com'),
('Diego', 'Torres', 'diegot', 'd1234', 'diego@example.com'),
('Valentina', 'Ruiz', 'valenr', 'valenpass', 'valentina@example.com');