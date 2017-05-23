DROP TABLE IF EXISTS films;
CREATE TABLE films(
  id INTEGER AUTO_INCREMENT,
  name VARCHAR(50),
  releaseDate VARCHAR(20),
  genre VARCHAR(50),
  country VARCHAR(50),
  producer VARCHAR(50),
  lasting INTEGER,
  description VARCHAR(255),
  actors VARCHAR(50),
  picture VARCHAR(100)
);