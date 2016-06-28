DELETE FROM users;
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
