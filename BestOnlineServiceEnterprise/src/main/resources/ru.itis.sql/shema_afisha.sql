CREATE TABLE afisha (
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

/**
Добавление фильмов
 */
INSERT INTO afisha (name, releaseDate, genre, country, producer, lasting, description, actors, picture)
VALUES ('Меч короля Артура',
        '11 мая 2017',
        'фентези',
        'США',
        'Гай Ричи',
        126,
        'Молодой Артур, своим происхождением совершенно не дорожит и не интересуется.Так он живёт до момента, когда судьба сводит его с волшебным мечом Экскалибуром',
        'Чарли Ханном',
        '1.jpg' );

INSERT INTO afisha (name, releaseDate, genre, country, producer, lasting, description, actors, picture)
VALUES ('Прочь',
        '11 мая 2017',
        'ужасы',
        'США',
        'Джейсон Блум',
        104,
        'Знакомство с родителями подружки не предвещает молодому фотографу из Нью-Йорка ничего хорошего, ведь семья девушки принадлежит к элитному обществу и живет в уединенном загородном доме. ',
        'Даниэл Калуя',
        'default.jpg' );