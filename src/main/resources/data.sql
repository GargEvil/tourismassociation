DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user(
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   email VARCHAR(128) NOT NULL,
                                   password_hash VARCHAR(256) NOT NULL,
                                   user_id VARCHAR(256) NOT NULL
);

DROP TABLE IF EXISTS municipality;

CREATE TABLE IF NOT EXISTS municipality
(
    id   INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    country_id INT NULL

);

DROP TABLE IF EXISTS landmark;

CREATE TABLE IF NOT EXISTS landmark(
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       name VARCHAR(50) NOT NULL,
                                       description VARCHAR(MAX) NULL,
                                       pictures VARBINARY NULL,
                                       geo_latitude DECIMAL NOT NULL,
                                       geo_longitude DECIMAL NOT NULL,
                                       active BIT  NULL,
                                       avgRating FLOAT NULL,
                                       municipality_id INT NULL,
                                       significance_id INT NULL
);

DROP TABLE IF EXISTS country;

CREATE TABLE IF NOT EXISTS country
(
    id   INT  PRIMARY KEY,
    name VARCHAR(50) NOT NULL

);

DROP TABLE IF EXISTS significance;

CREATE TABLE IF NOT EXISTS significance(
                                           id INT PRIMARY KEY,
                                           grade VARCHAR(40)
);

DROP TABLE IF EXISTS rating;

CREATE TABLE IF NOT EXISTS rating(
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     grade INT NOT NULL,
                                     landmark_id INT NOT NULL,
                                     user_id INT NULL,
                                     comment VARCHAR(MAX) NULL
);


/**
  ADDING FOREIGN KEYS TO TABLES
 */
ALTER TABLE municipality
    ADD FOREIGN KEY (country_id)
        REFERENCES country(id);

ALTER TABLE landmark
    ADD FOREIGN KEY (municipality_id)
        REFERENCES municipality(id);

ALTER TABLE landmark
    ADD FOREIGN KEY (significance_id)
        REFERENCES significance(id);

ALTER TABLE rating
    ADD FOREIGN KEY (landmark_id)
        REFERENCES landmark(id);

ALTER TABLE rating
    ADD FOREIGN KEY (user_id)
        REFERENCES user(id);

/**
  CREATING SOME TESTABLE DATA
 */


INSERT INTO user(email,password_hash, user_id) VALUES
( 'mahir@gmail.com', 'uhuh4848482','327382' ),
( 'adem@gmail.com', 'jgjgjs382839','392320');

INSERT INTO significance(id, grade) VALUES
( 1, 'Znamenito' ),
(2, 'Veoma znamenito'),
(3, 'Nezaobilazno');

INSERT INTO country VALUES
( 1, 'BiH' ),
(2, 'Hrvatska'),
(3, 'Slovenija'),
(4, 'Srbija'),
(5,'Crna Gora');

INSERT INTO municipality VALUES ( 1, 'Sarajevo', 1 ),
                                (2, 'Beograd',4),
                                (3, 'Zagreb' , 2),
                                (4, ' Podgorica', 5),
                                (5, 'Ljubljana', 3);

INSERT INTO landmark(name, description,
                     geo_latitude, geo_longitude,
                     active, municipality_id, significance_id)
VALUES
('Bascarsija', 'Prelijep dio starog grada',
 43.8598, 18.4313, 1, 1, 3),
('Skadarlija', 'Prelijep dio starog grada',
 43.8598, 18.4313, 1, 2, 2);

INSERT INTO  rating(grade,landmark_id, user_id, comment) VALUES (5, 1, 1, 'sadsa'  ),
                                                          (4, 2,NULL, 'sjasna');
