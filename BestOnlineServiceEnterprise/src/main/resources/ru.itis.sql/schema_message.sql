CREATE TABLE message (
  id SERIAL PRIMARY KEY,
  messageId INTEGER,
  text VARCHAR(50),
  chatId INTEGER,
  userId INTEGER
);