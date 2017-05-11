CREATE TABLE user_auth (
  id SERIAL PRIMARY KEY,
  login VARCHAR(50),
  password VARCHAR(50),
  user_id INTEGER REFERENCES login(id),
  auth_token VARCHAR(10) REFERENCES access_token(token)
);