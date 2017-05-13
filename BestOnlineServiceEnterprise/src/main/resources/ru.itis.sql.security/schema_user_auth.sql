CREATE TABLE user_auth (
  user_id INTEGER REFERENCES login(id),
  user_login VARCHAR(50) REFERENCES logins(login),
  user_password VARCHAR(50) REFERENCES logins(password),
  auth_token VARCHAR(255) REFERENCES access_token(token)
);