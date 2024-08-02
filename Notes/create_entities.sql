DROP TABLE IF EXISTS Warehouse CASCADE;
DROP TABLE IF EXISTS Product_Inventory CASCADE;
DROP TABLE IF EXISTS Product CASCADE;
DROP TABLE IF EXISTS Category CASCADE;

-- tables

-- Table: Warehouse
CREATE TABLE Warehouse (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
	capacity INTEGER,
	UNIQUE (name)
);

-- Table: Category
CREATE TABLE Category(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

-- Insert: Category
INSERT INTO Category(name)
	VALUES
	('S'),
	('M'),
	('L'),
	('XL');

-- Table: Product
CREATE TABLE Product(
    id SERIAL PRIMARY KEY,
    category_id INTEGER NOT NULL, 
	name VARCHAR(255),
    price INTEGER,
	CONSTRAINT fk_category
		FOREIGN KEY(category_id)
			REFERENCES Category(id)
			ON DELETE SET NULL 
);

-- Table: Product_Inventory
CREATE TABLE Product_Inventory(
	id SERIAL PRIMARY KEY,
    warehouse_id INTEGER,
    product_id INTEGER, 
    stock INTEGER,
	CONSTRAINT fk_warehouse
		FOREIGN KEY(warehouse_id)
			REFERENCES Warehouse(id)
			ON DELETE CASCADE,
	CONSTRAINT fk_product
		FOREIGN KEY(product_id)
			REFERENCES Product(id)
			ON DELETE CASCADE,
	UNIQUE (warehouse_id, product_id)
);