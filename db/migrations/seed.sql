CREATE TYPE role_type AS ENUM ('WAITER', 'ADMIN','BOSS');

CREATE TABLE restaurant(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    url_img TEXT UNIQUE,
    enabled boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE restaurant_parameters (
    id SERIAL PRIMARY KEY,
    restaurant_id INT NOT NULL REFERENCES restaurant(id),
    menu_color VARCHAR(100), 
    parameter_value TEXT NOT NULL      
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) UNIQUE,
    password VARCHAR(255) NOT NULL,
    role role_type NOT NULL DEFAULT 'WAITER',
    enabled boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    index INT NOT NULL DEFAULT 0,
    restaurant_id INT NOT NULL REFERENCES restaurant(id),
    enabled boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    url_img TEXT UNIQUE,
    category_id INT NOT NULL REFERENCES category(id),
    enabled boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE history_prices(
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL REFERENCES product(id),
    price NUMERIC(10,2) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    restaurant_id INT NOT NULL REFERENCES restaurant(id)
);

CREATE TABLE email_verificator(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    token VARCHAR(255) NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    verified BOOLEAN NOT NULL DEFAULT FALSE
);

INSERT INTO restaurant (name,phone,email,url_img) VALUES ('Bar1','1999595959','restaurant1@gmail.com','www.bar1.com.br');
INSERT INTO restaurant (name,phone,email,url_img) VALUES ('Bar2','2222222222','restaurant2@gmail.com','www.bar2.com.br');
INSERT INTO restaurant (name,phone,email,url_img) VALUES ('Bar3','3333333333','restaurant3@gmail.com','www.bar3.com.br');
INSERT INTO restaurant (name,phone,email,url_img) VALUES ('Bar4','4444444444','restaurant4@gmail.com','www.bar4.com.br');

INSERT INTO category (name,restaurant_id) VALUES 
('Porções',1),
('Bebidas',1),
('Lanches',1),
('Pizza',1),
('Porções Frias',1);

INSERT INTO category (name,restaurant_id) VALUES 
('Porções',2),
('Bebidas',2),
('Lanches',2),
('Pizza',2),
('Porções Frias',2);

INSERT INTO category (name,restaurant_id) VALUES 
('Porções',3),
('Bebidas',3),
('Lanches',3),
('Pizza',3),
('Porções Frias',3);

INSERT INTO category (name,restaurant_id) VALUES 
('Porções',4),
('Bebidas',4),
('Lanches',4),
('Pizza',4),
('Porções Frias',4);

INSERT INTO product (name, description, price, category_id) VALUES
-- Porções
('Frango à Passarinho', 'Porção de frango frito temperado.', 19.99, 1),
('Coxinha', 'Coxinha de frango com massa crocante.', 12.50, 1),
('Bolinho de Bacalhau', 'Bolinho frito de bacalhau.', 18.00, 1),

-- Bebidas
('Cerveja Lagers', 'Cerveja leve e refrescante.', 7.50, 2),
('Caipirinha', 'Drink tradicional brasileiro feito com cachaça.', 15.00, 2),
('Refrigerante', 'Refrigerante sabor cola.', 5.00, 2),

-- Lanches
('Hambúrguer Clássico', 'Hambúrguer com queijo e bacon.', 20.00, 3),
('Sanduíche Natural', 'Sanduíche com peito de peru e vegetais.', 12.00, 3),

-- Pizza
('Pizza Margherita', 'Pizza com molho de tomate e mozzarella.', 30.00, 4),
('Pizza Calabresa', 'Pizza com calabresa e cebola.', 32.00, 4),

-- Porções Frias
('Tábua de Frios', 'Seleção de queijos e embutidos.', 45.00, 5),
('Salada de Frutas', 'Mistura de frutas frescas da estação.', 25.00, 5);


INSERT INTO product (name, description, price, category_id) VALUES
-- Porções
('Frango à Passarinho2', 'Porção de frango frito temperado.', 19.99, 6),
('Coxinha2', 'Coxinha de frango com massa crocante.', 12.50, 6),
('Bolinho de Bacalhau2', 'Bolinho frito de bacalhau.', 18.00, 6),

-- Bebidas
('Cerveja Lagers2', 'Cerveja leve e refrescante.', 7.50, 7),
('Caipirinha2', 'Drink tradicional brasileiro feito com cachaça.', 15.00, 7),
('Refrigerante2', 'Refrigerante sabor cola.', 5.00, 7),

-- Lanches
('Hambúrguer Clássico2', 'Hambúrguer com queijo e bacon.', 20.00, 8),
('Sanduíche Natural2', 'Sanduíche com peito de peru e vegetais.', 12.00, 8),

-- Pizza
('Pizza Margherita2', 'Pizza com molho de tomate e mozzarella.', 30.00, 9),
('Pizza Calabresa2', 'Pizza com calabresa e cebola.', 32.00, 9),

-- Porções Frias
('Tábua de Frios2', 'Seleção de queijos e embutidos.', 45.00, 10),
('Salada de Frutas2', 'Mistura de frutas frescas da estação.', 25.00, 10);

INSERT INTO  users
("name", email, "password", "role")
VALUES('Administrador', 'admin@gmail.com', '$2a$10$1HVGUdEQ1y4DX10DRUKDK.z/i59K9iXQeQty8Pwc8znnuSoua2Ta6', 'ADMIN'::public."role_type");

INSERT INTO users
("name", email, "password", "role", enabled)
VALUES('Waiter', 'waiter@gmail.com', '$2a$10$tUo05DzvN7VIYTq1uiRIjOM22QoCFwU/OdYeB9lzS/H38NV/fT4Pi', 'WAITER'::public."role_type", true);

