
create database place;
use place;

CREATE TABLE city (
    id INTEGER(6) PRIMARY KEY AUTO_INCREMENT,
    plz INTEGER(6) NOT NULL,
    city VARCHAR(255),
    lat INTEGER(6),
    lon INTEGER(6)
) ENGINE=InnoDB;
 
CREATE TABLE student (
    id INTEGER(6) PRIMARY KEY AUTO_INCREMENT,
    id_city INTEGER(6),
    name VARCHAR(255),
    marker INTEGER(4),
    FOREIGN KEY (id_city) REFERENCES city(id)
) ENGINE=InnoDB;
CREATE INDEX id_city ON student(id_city);

LOAD DATA LOCAL INFILE './plz.csv' INTO TABLE city CHARACTER SET 'utf8' FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (plz, city);

INSERT INTO student (name, id_city) VALUES ('Hanser Hans', 4116);
