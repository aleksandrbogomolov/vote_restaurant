DELETE FROM votes;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, role)
VALUES ('User', 'user@yandex.ru', 'password', 'USER');

INSERT INTO users (name, email, password, role)
VALUES ('Admin', 'admin@yandex.ru', 'admin', 'ADMIN');


INSERT INTO restaurants (name, address) VALUES ('Кавказ', 'Днепровка');

INSERT INTO restaurants (name, address) VALUES ('Скиф', 'Энергодар');


INSERT INTO dishes (name, type_dish, price, restaurant_id) VALUES ('Борщ', 1, 50, 100002);

INSERT INTO dishes (name, type_dish, price, restaurant_id) VALUES ('Пюре', 2, 30, 100002);

INSERT INTO dishes (name, type_dish, price, restaurant_id) VALUES ('Котлета', 3, 50, 100002);

INSERT INTO dishes (name, type_dish, price, restaurant_id) VALUES ('Салат из помидор', 4, 40, 100002);

INSERT INTO dishes (name, type_dish, price, restaurant_id) VALUES ('Солянка', 1, 70, 100003);

INSERT INTO dishes (name, type_dish, price, restaurant_id) VALUES ('Макароны', 2, 20, 100003);

INSERT INTO dishes (name, type_dish, price, restaurant_id) VALUES ('Бифштекс', 3, 60, 100003);

INSERT INTO dishes (name, type_dish, price, restaurant_id) VALUES ('Компот', 4, 10, 100003);


INSERT INTO votes (user_id, restaurant_id) VALUES (100000, 100003);
