
CREATE DATABASE place;
USE place;

CREATE TABLE city (
    id INTEGER(6) PRIMARY KEY AUTO_INCREMENT,
    plz INTEGER(6) NOT NULL,
    city VARCHAR(255) NOT NULL
) ENGINE=InnoDB;
CREATE INDEX city ON city(city);
CREATE INDEX plz ON city(plz);

CREATE TABLE student (
    id INTEGER(6) PRIMARY KEY AUTO_INCREMENT,
    id_city INTEGER(6) NOT NULL,
    name VARCHAR(255) NOT NULL,
    marker INTEGER(4) NOT NULL DEFAULT 0,
    FOREIGN KEY (id_city) REFERENCES city(id)
) ENGINE=InnoDB;
CREATE INDEX id_city ON student(id_city);
CREATE INDEX name ON student(name);

CREATE TABLE user (
    id INTEGER(6) PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role INTEGER(3) NOT NULL DEFAULT 0
) ENGINE=InnoDB;
CREATE INDEX login ON user(login);

LOAD DATA LOCAL INFILE './plz.csv' INTO TABLE city CHARACTER SET 'utf8' FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (plz, city);


INSERT INTO student (id_city, name, marker) VALUES ('1293', 'Wilbert', 0);
INSERT INTO user (login, password, role) VALUES ('admin', '$2y$10$Veuppl0zrVrTKl7e9TP2EuMrEXLJOEsEODthSjP2Y0SuXSCdt8A5i', 1);
INSERT INTO user (login, password) VALUES ('hans', '$2y$10$tW/SlCkl83EgWLj/tLKmzO3qyX.8W.2a6SWtVkLLuKfDHzumxlJPi');
