package test;

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