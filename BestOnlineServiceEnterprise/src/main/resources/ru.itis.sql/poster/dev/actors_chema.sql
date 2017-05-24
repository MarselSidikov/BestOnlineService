CREATE TABLE actors(
  id SERIAL PRIMARY KEY,
  id_film INTEGER REFERENCES films(id),
  name VARCHAR(50)
);