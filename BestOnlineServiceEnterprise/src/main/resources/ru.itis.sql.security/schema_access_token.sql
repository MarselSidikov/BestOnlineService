CREATE TABLE access_token (
  token_id INTEGER REFERENCES login(id),
  token VARCHAR(10)
)