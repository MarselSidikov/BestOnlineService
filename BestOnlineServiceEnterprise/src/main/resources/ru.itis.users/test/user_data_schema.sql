DROP TABLE IF EXISTS group_user;
CREATE TABLE user_data (
  id       INTEGER AUTO_INCREMENT,
  login    VARCHAR(20),
  password VARCHAR(20),
  name     VARCHAR(20),
  age      INTEGER
);