\c postgres
DROP DATABASE afdbtest;
CREATE DATABASE afdbtest;
\c afdbtest;

CREATE SEQUENCE PersonSequence;

CREATE TABLE Person ( 
	idperson INT PRIMARY KEY DEFAULT NEXTVAL ('PersonSequence'), 
	name VARCHAR(20),
	score float,
	birth_date TIMESTAMP
);

INSERT INTO Person VALUES (DEFAULT, 'Paul', 17.5, TIMESTAMP '1999-01-01 00:00:00');
INSERT INTO Person VALUES (DEFAULT, 'Jack', 8.5, TIMESTAMP '2000-01-01 00:00:00');
INSERT INTO Person VALUES (DEFAULT, 'John', 10, TIMESTAMP '2000-08-01 00:00:00');
INSERT INTO Person VALUES (DEFAULT, 'Smith', 15.73, TIMESTAMP '1980-01-01 00:00:00');

CREATE VIEW AverageScorers AS SELECT * FROM Person WHERE score >= 8 AND score <= 12;