CREATE TABLE user_auth (
  id SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES user_data(id),
  user_login VARCHAR(50) REFERENCES user_data(login),
  user_password VARCHAR(50) REFERENCES user_data(password),
  auth_token VARCHAR(255) REFERENCES access_token(token)
);