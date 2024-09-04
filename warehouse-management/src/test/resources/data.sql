DELETE FROM Warehouse;
DELETE FROM Product;

-- insert tables
INSERT INTO Warehouse(name, address, capacity)
	VALUES
	('Electronics Galore', 'Orlando, FL', 300),
	('Mystery Box1', 'Tampa, FL', 1000),
	('Mystery Box2', 'Tampa, FL', 500),
	('Knick Knacks', 'Phoenix, AZ', 1700),
	('Books & More', 'Fresno, CA', 700),
	('Books & More 2', 'Oakland, CA', 900),
	('Books & More 3', 'Oakland, CA', 730),
	('Greenish', 'Oakland, CA', 370),
	('MorningWave', 'Algona, WA', 2300),
	('MooreWarehouse', 'Seattle, WA', 430);

INSERT INTO Product(category_id, name, price)
	VALUES
	((SELECT id from Category WHERE id =1), 'knicknacks', 13),
	((SELECT id from Category WHERE id =2), 'new books', 20),
	((SELECT id from Category WHERE id =3), 'beddings', 21),
	((SELECT id from Category WHERE id =1), 'old books', 20),
	((SELECT id from Category WHERE id =1), 'electronics', 46),
	((SELECT id from Category WHERE id =3), 'miscellaneous', 99),
	((SELECT id from Category WHERE id =4), 'supplies', 12),
	((SELECT id from Category WHERE id =3), 'clothing', 11),
	((SELECT id from Category WHERE id =2), 'vintage', 11),
	((SELECT id from Category WHERE id =2), 'dishware', 30);