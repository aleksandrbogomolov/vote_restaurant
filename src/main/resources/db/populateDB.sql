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


INSERT INTO dishes (name, price, restaurant_id) VALUES ('Борщ', 50, 100002);

INSERT INTO dishes (name, price, restaurant_id) VALUES ('Пюре', 30, 100002);

INSERT INTO dishes (name, price, restaurant_id) VALUES ('Котлета', 50, 100002);

INSERT INTO dishes (name, price, restaurant_id) VALUES ('Салат из помидор', 40, 100002);

INSERT INTO dishes (name, price, restaurant_id) VALUES ('Солянка', 70, 100003);

INSERT INTO dishes (name, price, restaurant_id) VALUES ('Макароны', 20, 100003);

INSERT INTO dishes (name, price, restaurant_id) VALUES ('Бифштекс', 60, 100003);

INSERT INTO dishes (name, price, restaurant_id) VALUES ('Компот', 10, 100003);
