package test;

import java.sql.Timestamp;

import annotations.afColumn;
import annotations.afTable;

@afTable (alias = "Person")
public class PersonAnnotated {
    @afColumn (alias = "idperson")
    int idPerSoNwawa = 0;

    // @afColumn
    String name = null;

    // @afColumn
    float score = 0;
    
    @afColumn (alias = "birth_date")
    Timestamp birthDateWoo = null;
    
    public int getIdPerSoNwawa() {
        return idPerSoNwawa;
    }

    public void setIdPerSoNwawa(int idPerSoNwawa) {
        this.idPerSoNwawa = idPerSoNwawa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Timestamp getBirthDateWoo() {
        return birthDateWoo;
    }

    public void setBirthDateWoo(Timestamp birthDateWoo) {
        this.birthDateWoo = birthDateWoo;
    }

    @Override
    public String toString() {
        return "PersonAnnotated [birthDateWoo=" + birthDateWoo + ", idPerSoNwawa=" + idPerSoNwawa + ", name=" + name
                + ", score=" + score + "]";
    }

}