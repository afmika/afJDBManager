package core;

import operation.*;
import java.sql.Connection;
import java.util.Map;

/**
 * @author afmika
 */
public class afQuery extends afLoggable {
    private String table_name = null;
    private Object instance = null;
    private Connection connection = null;

    public void setTable_name (String table) {
        this.table_name = table;
    }
    public void setInstance(Object instance) {
        this.instance = instance;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public String getTable_name () {
        return this.table_name;
    }
    public Object getInstance() {
        return this.instance;
    }
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Constructor
     * @param instance
     */
    private afQuery(Connection connection) {
        this.connection = connection;
    }

    
    public static afQuery use (Connection connection) {
        return new afQuery(connection);  
    }

    // factory
    public afQuery of (Object instance) {
        this.setInstance(instance);
        this.setTable_name(null);
        return this;
    }

    public afQuery of (Object instance, String table_name) {
        this.setInstance(instance);
        this.setTable_name(table_name);
        return this; 
    }

    /**
     * Select query
     *  afQuery.of(new Person())
     *         .select().where("age >= 18")
     *         .<Person>get("name = 'John'")
     *         .end();
     */
    public afReadOperation select() throws Exception {
        if (this.getInstance() == null)
            throw new Exception("You must define an instance with 'of()' before 'select'");
        return new afReadOperation(this);
    }

    /**
     * Sequence query
     */
    public afSequenceOperation sequence(String sequence_name) throws Exception {
        return new afSequenceOperation(this, sequence_name);
    }

    /**
     * Basic query runner afQuery.run("SELECT * FROM Person") .get<Person>();
     */
    public afBasicQueryOperation run(String sql, Object[] variables) throws Exception {
        return new afBasicQueryOperation(this, sql, variables);
    }
    public afBasicQueryOperation run (String sql) throws Exception {
        return new afBasicQueryOperation(this, sql, null);
    }

    /**
     * Update query
     * @param new_values defines the updated values
     * @apiNote
     *  Map<String, Object> values = new HashMap<>();
     *  values.put("name", "John");
     *  afQuery.of(new Person())
     *         .update(values)
     *         .where("name = 'John'")
     *         .end();
     */
    public afUpdateOperation update(Map<String, Object> new_values) throws Exception {
        if (this.getInstance() == null)
            throw new Exception("You must define an instance with 'of()' before 'update'");
        return new afUpdateOperation(this, new_values);
    }

    /**
     * Update query
     * @apiNote
     *  afQuery.of(new Person())
     *         .delete()
     *         .where("name = 'John'")
     *         .end();
     */
    public afDeleteOperation delete() throws Exception {
        if (this.getInstance() == null)
            throw new Exception("You must define an instance with 'of()' before 'delete'");
        return new afDeleteOperation(this);
    }

    /**
     * Update query
     * @apiNote
     *  afQuery.of(new Person("John", 56))
     *         .insert()
     *         .end();
     */    
    public afInsertOperation insert() throws Exception {
        if (this.getInstance() == null)
            throw new Exception("You must define an instance with 'of()' before 'insert'");
        return new afInsertOperation(this);
    }
}