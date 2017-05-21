CREATE TABLE access_token (
  id SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES user_data(id),
  token VARCHAR(255)
)