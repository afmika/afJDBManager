CREATE USER afdbtest IDENTIFIED BY afdbtest; 
GRANT DBA TO afdbtest; 
CONNECT afdbtest 
afdbtest

CREATE TABLE Person ( 
	idperson INT PRIMARY KEY, 
	name VARCHAR(20),
	score float,
	birth_date TIMESTAMP
);

CREATE SEQUENCE PersonSequence;
INSERT INTO Person VALUES (PersonSequence.nextval, 'Paul', 17.5, TIMESTAMP '1999-01-01 00:00:00');
INSERT INTO Person VALUES (PersonSequence.nextval, 'Jack', 8.5, TIMESTAMP '2000-01-01 00:00:00');
INSERT INTO Person VALUES (PersonSequence.nextval, 'John', 10, TIMESTAMP '2000-08-01 00:00:00');
INSERT INTO Person VALUES (PersonSequence.nextval, 'Smith', 15.73, TIMESTAMP '1980-01-01 00:00:00');
COMMIT;