CREATE TABLE products (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    insert_in TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    category VARCHAR(50) NOT NULL,
    user_id INTEGER NOT NULL DEFAULT 1
);

INSERT INTO products (name, description, price, category) VALUES
('Notebook Acer Nitro V15', 'Notebook 15pol 165hz, ssd de 512gb, 32gb de ram, RTX 4060 8gb', 5700.00, 'ELETRONICO'),
('Camiseta Regata', 'Camiseta regata Growth', 45.50, 'ROUPA'),
('Monitor 27pol 160hz', 'Monitor 165hz, 27pol, preto', 1855.99, 'ELETRONICO'),
('Smartphone Samsung S24 Ultra', 'Smartphone 512gb, preto', 3499.90, 'ELETRONICO');
