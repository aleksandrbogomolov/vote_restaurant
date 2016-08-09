DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dishes;
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
  role       VARCHAR(16) NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE restaurants (
  id      INTEGER     NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR(50) NOT NULL,
  address VARCHAR(50) NOT NULL,
  vote    INTEGER
);

CREATE TABLE dishes (
  id            INTEGER     NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
  name          VARCHAR(50) NOT NULL,
  type_dish     INTEGER     NOT NULL,
  price         INTEGER     NOT NULL,
  restaurant_id INTEGER     NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes (
  id            INTEGER NOT NULL PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
