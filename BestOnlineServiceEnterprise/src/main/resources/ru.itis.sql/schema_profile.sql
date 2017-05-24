CREATE TABLE schema_profile (
  id SERIAL PRIMARY KEY,
  firstNameUser VARCHAR(40),
  lastNameUser VARCHAR(40),
  ageUser INTEGER,
  city VARCHAR(30),
  image VARCHAR(50)
);