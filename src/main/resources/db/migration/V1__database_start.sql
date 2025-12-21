DROP TABLE IF EXISTS users;
-- Ensure drops happen before creates to avoid leftover objects
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    insert_in TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    category VARCHAR(50) NOT NULL,
    user_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO users (id, name, email, password) VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 'Diego Sousa', 'diego@teste.com', '$2a$12$naluoPrCCUnWIl0HJhaV5.XDjeKLqaRjwcj2bKfBdAzwyNXWtPozu'),
    ('b2b1b5a2-5d4b-4f7b-9e5d-3f9d0a3c8e21', 'Filipe Sousa', 'filipe@teste.com', '$2a$12$ZAl3B2ak1a8XDnF4RdWR9Ogi4WYW6xUBhW283artJZm9q.NFCe93a');

INSERT INTO products (name, description, price, category, user_id) VALUES
    ('Notebook Acer Nitro V15', 'Notebook 15pol 165hz, ssd de 512gb, 32gb de ram, RTX 4060 8gb', 5700.00, 'ELETRONICO', '550e8400-e29b-41d4-a716-446655440000'),
    ('Camiseta Regata', 'Camiseta regata Growth', 45.50, 'ROUPA', '550e8400-e29b-41d4-a716-446655440000'),
    ('Monitor 27pol 160hz', 'Monitor 165hz, 27pol, preto', 1855.99, 'ELETRONICO', 'b2b1b5a2-5d4b-4f7b-9e5d-3f9d0a3c8e21'),
    ('Smartphone Samsung S24 Ultra', 'Smartphone 512gb, preto', 3499.90, 'ELETRONICO', 'b2b1b5a2-5d4b-4f7b-9e5d-3f9d0a3c8e21');