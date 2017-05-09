CREATE TABLE group_user (
  id SERIAL PRIMARY KEY,
  users VARCHAR(50),
  interests VARCHAR(50),
  posts VARCHAR(50),
  friends INTEGER
);