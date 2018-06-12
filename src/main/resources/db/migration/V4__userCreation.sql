CREATE TABLE users
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  created_tmstp timestamp default null,
  enabled bit(1) default null,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  updated_tmtp timestamp default null, 
  userName VARCHAR(255) NOT NULL
);

CREATE TABLE users_roles
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT(20) NOT NULL REFERENCES users(id),
  role_id BIGINT(20) NOT NULL REFERENCES roles(id)
);

CREATE TABLE roles
(
  id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) DEFAULT NULL
);