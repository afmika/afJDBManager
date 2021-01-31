package test;
/*
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
*/
import java.sql.Timestamp;

public class Person {
	int idperson = 0;
	String name = null;
	float score = 0;
	Timestamp birth_date = null;

	public Person () {}
	public Person (int idperson, String name, float score, Timestamp birth_date) {
		setIdperson (idperson);
		setName (name);
		setScore (score);
		setBirth_date (birth_date);
	}

	public void setIdperson (int idperson) {
		this.idperson = idperson;
	}
	public void setName (String name) {
		this.name = name;
	}
	public void setScore (float score) {
		this.score = score;
	}
	public void setBirth_date (Timestamp birth_date) {
		this.birth_date = birth_date;
	}

	public int getIdperson () {
		return this.idperson;
	}
	public String getName () {
		return this.name;
	}
	public float getScore () {
		return this.score;
	}
	public Timestamp getBirth_date () {
		return this.birth_date;
	}

	
	@Override
	public String toString () {
		return "[ idperson = " + idperson + " name = " + name + " score = " + score + " birth_date = " + birth_date + " ]";
	}
}