ALTER TABLE seat ADD user_name VARCHAR(255);

drop table users_roles;
drop table roles;
drop table users;

CREATE TABLE users
(
  id SERIAL PRIMARY KEY,
  created_tmstp timestamp default null,
  enabled boolean,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  updated_tmtp timestamp default null, 
  userName VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL
);

CREATE TABLE roles
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) DEFAULT NULL,
  user_id INTEGER REFERENCES users(id)
);





