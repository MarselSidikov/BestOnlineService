CREATE TABLE poster (
  id SERIAL PRIMARY KEY,
  name VARCHAR(50),
  date DATE,
  genre VARCHAR(50),
  country VARCHAR(50),
  producer VARCHAR(50),
  lasting TIME,
  description VARCHAR(255),
  actors VARCHAR(50),
  picture VARCHAR(100)
);