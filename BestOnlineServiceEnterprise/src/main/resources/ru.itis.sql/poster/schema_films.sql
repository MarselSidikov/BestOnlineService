CREATE TABLE films (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50),
  releaseDate VARCHAR(20),
  genre VARCHAR(50),
  country VARCHAR(50),
  producer VARCHAR(50),
  lasting DOUBLE PRECISION,
  description VARCHAR(255),
  actors VARCHAR(50),
  picture VARCHAR(100)
);
