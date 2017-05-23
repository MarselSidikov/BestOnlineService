DROP TABLE IF EXISTS books;
CREATE TABLE  books (
  id INTEGER AUTO_INCREMENT,
  name VARCHAR(50),
  author VARCHAR(50),
  type VARCHAR(50),
  genre VARCHAR(50),
  publishingHouse VARCHAR (50),
  yearOfIssue INTEGER,
  numberOfPages INTEGER,
  language VARCHAR(50),
  description VARCHAR(350)
);