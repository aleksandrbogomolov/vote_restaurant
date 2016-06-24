DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users (
  id         INTEGER     NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR(50) NOT NULL,
  email      VARCHAR(50) NOT NULL,
  password   VARCHAR(16) NOT NULL,
  registered TIMESTAMP                        DEFAULT now(),
  enabled    BOOL                             DEFAULT TRUE,
  role       VARCHAR(5)  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE restaurants (
  id            INTEGER     NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
  name          VARCHAR(50) NOT NULL,
  address       VARCHAR(50) NOT NULL,
  time_to_up    TIME                             DEFAULT ('09:00:00'),
  time_to_close TIME                             DEFAULT ('23:00:00')
);

CREATE TABLE menus (
  id            INTEGER NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
  registered    TIMESTAMP                    DEFAULT now(),
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE dishes (
  id      INTEGER     NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR(50) NOT NULL,
  price   INTEGER     NOT NULL,
  menu_id INTEGER     NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
);
