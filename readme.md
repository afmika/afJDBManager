# afJDBManager
A basic library  for SQL queries 100% written in java
## Basic uses
```sql
CREATE TABLE Person ( 
	idperson INT PRIMARY KEY, 
	name VARCHAR(20),
	score float,
	birth_date TIMESTAMP
);
```
### Select
```java
afQuery query = afQuery.use(my_connection);
// without filters
ArrayList<Person> list =
    query.of(new Person()).<Person>get();

// with filters
ArrayList<Person> list =
    query.of(new Person())
        .where("age > 5 AND score < ?", new Object[]{10.5})
        .<Person>get();
```
### Update
```java
afQuery query = afQuery.use(my_connection);
Map<String, Object> new_values = new HashMap<>();
new_values.put("score", 258.6);
new_values.put("birth_date", Timestamp.valueOf("2000-01-01 01:48:00"));

// without filters
int row_affected = query.of(new Person())
    .update(new_values)
    .end();

// with filters
int row_affected = query.of(new Person())
    .update(new_values)
    .where("age > 18") // .where("age > ?", new Integer[]{18})
    .end();
```

### Insert
```java
afQuery query = afQuery.use(my_connection);
int nextval = query.sequence("PersonSequence").nextValue();
Person new_person = new Person(nextval, "Rabe", 17.8, Timestamp.valueOf("2010-04-01 00:02:11"));
int row_affected = query.of(new_person)
                    .insert()
                    .end();
```
### Delete
```java
afQuery query = afQuery.use(my_connection);
query.of(new Person())
    .delete()
    .where("score < 10")
    .end();
```

## Tips

* Keep in mind that the core engine works with prepared statements.
```java
...where("name = ?", new String[]{"John"})
```
* You can still run direct queries like this
```java
afQuery query = afQuery.use(my_connection);
// insert, update, delete queries
int affected_rows = 
    query.run("UPDATE Person SET score = ? WHERE age <= 10", new Float[]{8})
        .end();
int affected_rows =
    query.run("DELETE FROM Person WHERE score = 1")
        .end();

// select query
ArrayList<Person> list = 
        query.run("SELECT * FROM Person WHERE score > ?", new Object[]{0})
            .<Person>get(new Person());
```

* Handling sequences
```java
afQuery query = afQuery.use(my_connection);
int nextval = query.sequence("my_sequence").nextValue();

```