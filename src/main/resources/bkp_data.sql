CREATE TABLE Client (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    cpf VARCHAR(11)
);

CREATE TABLE Product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    price NUMERIC(20,2)
);

CREATE TABLE `Order` (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER REFERENCES Client (id),
    date_order TIMESTAMP,
    status VARCHAR(20),
    total NUMERIC(20,2)
);

CREATE TABLE Item_order (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    order_id INTEGER REFERENCES `Order` (id),
    product_id INTEGER REFERENCES Product (id),
    quantity INTEGER
);
