CREATE TABLE auth (
  id SERIAL PRIMARY KEY,
  user_name VARCHAR(50),
  password VARCHAR(50),
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  date_created TIMESTAMP,
  date_modified TIMESTAMP
);