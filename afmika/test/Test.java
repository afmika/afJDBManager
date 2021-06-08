package test;

import java.sql.Connection;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import core.afQuery;

public class Test {

    public static Connection getConnection () throws Exception {
        String url = "jdbc:postgresql://localhost/afdbtest";
        String user = "postgres";
        String password = "1234";
        return DriverManager.getConnection(url, user, password);
    }
    
    public static void runSimpleCase () throws Exception {
        Connection con = Test.getConnection();
        afQuery query = afQuery.use(con);

        Map<String, Object> new_values = new HashMap<>();
        new_values.put("score", 100);
        new_values.put("birth_date", Timestamp.valueOf("2000-01-01 01:48:00"));

        query.of(new Person())
            .update(new_values)
            .where("score > 200")
            .end(); // ok

        int nextval = query.sequence("PersonSequence").nextValue();
        query.log("SEQUENCE = " + nextval);
        query.of(new Person(nextval, "ZZZ", 44.4f, Timestamp.valueOf("2010-04-01 00:02:11")))
            .insert()
            .end(); // ok

        // query.of(new Person())
        //     .delete()
        //     .where("score = ?", new Integer[]{1})
        //     .end(); // ok

        // query.of(new Person())
        //     .select().where("score > 1.6")
        //     .<Person>get()
        //     .forEach(x -> {
        //         System.out.println(x);
        //     }); // ok
        
        // query.run("UPDATE Person SET fvalue = ? WHERE score <= 1", new Object[]{Math.random()})
        //     .end();

        // query.run("DELETE Person WHERE score = 1")
        //     .end();
        
        query.of(new Person(), "AverageScorers")
            .select().<Person>get()
            .forEach(x -> {
                System.out.println(x);
            }); // ok
        
        // query.run("SELECT * FROM Person WHERE score > ?", new Object[]{0})
        //      .<Person>get(new Person())
        //      .forEach(x -> {
        //         System.out.println("=>" +x);
        //      });
        
        con.close();
   }

   public static void runAnnotatedCase () throws Exception {
        Connection con = getConnection();
        afQuery query = afQuery.use(con); 

        // int count = query.of(new PersonAnnotated())
        //     .delete()
        //     .where("idperson = 1")
        //     .end();
        // System.out.println("REMOVED " + count);

        PersonAnnotated pers = new PersonAnnotated();
        int nextval = query.sequence("PersonSequence").nextValue();
        query.log("SEQUENCE = " + nextval);
        pers.setIdPerSoNwawa(nextval);
        pers.setName("UUU");
        pers.setScore(77.7f);
        pers.setBirthDateWoo(Timestamp.valueOf("2010-04-01 00:02:11"));
        
        query.of(pers)
            .insert()
            .end(); // ok

        query.of (new PersonAnnotated())
            .select()
            .get().forEach(p -> {
                System.out.println("==> " + p);
            });
        con.close();
   }

   public static void main (String[] args) throws Exception {
       // runSimpleCase();
       runAnnotatedCase();
   }
}