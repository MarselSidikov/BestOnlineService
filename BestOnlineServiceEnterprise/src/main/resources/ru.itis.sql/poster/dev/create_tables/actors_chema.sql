CREATE TABLE actors(
  id SERIAL PRIMARY KEY,
  id_film INTEGER REFERENCES films(id),
  actor_name VARCHAR(50)
);