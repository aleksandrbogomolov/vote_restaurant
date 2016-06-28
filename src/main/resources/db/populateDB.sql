DELETE FROM users;
DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, role)
VALUES ('User', 'user@yandex.ru', 'password', 'USER');

INSERT INTO users (name, email, password, role)
VALUES ('Admin', 'admin@yandex.ru', 'admin', 'ADMIN');


INSERT INTO restaurants (name, address) VALUES ('Кавказ', 'Днепровка');

INSERT INTO restaurants (name, address) VALUES ('Скиф', 'Энергодар');


INSERT INTO menus (registered, restaurant_id) VALUES ('2016-06-26 09:00:00', 100002);

INSERT INTO menus (registered, restaurant_id) VALUES ('2016-06-27 09:00:00', 100002);

INSERT INTO menus (registered, restaurant_id) VALUES ('2016-06-27 08:30:00', 100003);


INSERT INTO dishes (name, price, menu_id) VALUES ('Борщ', 50, 100004);

INSERT INTO dishes (name, price, menu_id) VALUES ('Пюре', 30, 100004);

INSERT INTO dishes (name, price, menu_id) VALUES ('Котлета', 50, 100004);

INSERT INTO dishes (name, price, menu_id) VALUES ('Салат из помидор', 40, 100004);

INSERT INTO dishes (name, price, menu_id) VALUES ('Солянка', 70, 100005);

INSERT INTO dishes (name, price, menu_id) VALUES ('Макароны', 20, 100005);

INSERT INTO dishes (name, price, menu_id) VALUES ('Бифштекс', 60, 100005);

INSERT INTO dishes (name, price, menu_id) VALUES ('Компот', 10, 100005);
