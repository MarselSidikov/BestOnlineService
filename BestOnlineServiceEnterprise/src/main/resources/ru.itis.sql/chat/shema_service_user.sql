CREATE TABLE service_user (
  id SERIAL PRIMARY KEY,
  login VARCHAR (50),
  password VARCHAR(50),
  name VARCHAR(50),
  age INTEGER
);