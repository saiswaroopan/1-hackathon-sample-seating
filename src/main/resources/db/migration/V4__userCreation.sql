CREATE TABLE users
(
  id SERIAL PRIMARY KEY,
  created_tmstp timestamp default null,
  enabled bit(1) default null,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  updated_tmtp timestamp default null, 
  userName VARCHAR(255) NOT NULL
);

CREATE TABLE roles
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) DEFAULT NULL
);

CREATE TABLE users_roles
(
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users(id),
  role_id INTEGER NOT NULL REFERENCES roles(id)
);

