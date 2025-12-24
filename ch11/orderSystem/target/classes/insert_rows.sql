INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김일', '서울시', 'kim1@gmail.com');
INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김이', '부산시', 'kim2@gmail.com');
INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김삼', '대전시', 'kim3@gmail.com');
INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김사', '인천시', 'kim4@gmail.com');
INSERT INTO customer(customer_name, customer_address, customer_email) VALUES ('김오', '대구시', 'kim5@gmail.com');

INSERT INTO product(product_name, product_description, product_price) VALUES ('제품1', '제품1설명', 10000);
INSERT INTO product(product_name, product_description, product_price) VALUES ('제품2', '제품2설명', 20000);
INSERT INTO product(product_name, product_description, product_price) VALUES ('제품3', '제품3설명', 30000);
INSERT INTO product(product_name, product_description, product_price) VALUES ('제품4', '제품4설명', 40000);
INSERT INTO product(product_name, product_description, product_price) VALUES ('제품5', '제품5설명', 50000);

INSERT INTO inventory VALUES (1, 1000);
INSERT INTO inventory VALUES (2, 2000);
INSERT INTO inventory VALUES (3, 3000);
INSERT INTO inventory VALUES (4, 4000);
INSERT INTO inventory VALUES (5, 5000);

INSERT INTO orders(customer_id) VALUES (1);
INSERT INTO orders(customer_id) VALUES (2);
INSERT INTO orders(customer_id) VALUES (3);
INSERT INTO orders(customer_id) VALUES (4);
INSERT INTO orders(customer_id) VALUES (5);

INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (1, 1, 1);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (2, 2, 1);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (3, 3, 1);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (4, 4, 2);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (5, 5, 2);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (1, 10, 3);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (2, 20, 3);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (3, 30, 4);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (4, 40, 4);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (5, 50, 4);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (1, 100, 5);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (2, 200, 5);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (3, 300, 5);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (4, 400, 5);
INSERT INTO order_item(product_id, order_item_quantity, order_id) VALUES (5, 500, 5);

SELECT * FROM customer;
SELECT * FROM product;
SELECT * FROM inventory;
SElECT * FROM order_item;
SELECT * FROM orders;
