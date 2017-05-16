CREATE TABLE access_token (
  id SERIAL PRIMARY KEY,
  login_id INTEGER REFERENCES service_login(id),
  token VARCHAR(255)
)