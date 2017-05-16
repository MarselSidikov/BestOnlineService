CREATE TABLE user_auth (
  user_id INTEGER REFERENCES service_login(id),
  user_login VARCHAR(50) REFERENCES service_login(login),
  user_password VARCHAR(50) REFERENCES service_login(password),
  auth_token VARCHAR(255) REFERENCES access_token(token)
);