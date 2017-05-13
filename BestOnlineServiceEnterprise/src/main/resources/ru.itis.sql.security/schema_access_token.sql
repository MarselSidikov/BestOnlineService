CREATE TABLE access_token (
  user_id INTEGER REFERENCES login(id),
  token VARCHAR(255)
)