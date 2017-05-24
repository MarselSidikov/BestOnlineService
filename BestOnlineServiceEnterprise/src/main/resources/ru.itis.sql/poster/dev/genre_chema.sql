CREATE TABLE genres(
  id SERIAL PRIMARY KEY,
  id_film INTEGER REFERENCES films(id),
  genre VARCHAR(50)
);