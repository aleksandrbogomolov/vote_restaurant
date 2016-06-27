DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, role)
VALUES ('User', 'user@yandex.ru', 'password', 'USER');

INSERT INTO users (name, email, password, role)
VALUES ('Admin', 'admin@yandex.ru', 'admin', 'ADMIN');

