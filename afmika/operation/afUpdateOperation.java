package operation;

import core.*;
import java.util.Map;

/**
 * @author afmika
 */
public class afUpdateOperation extends afOperation {
    private Map<String, Object> new_values = null;

    public afUpdateOperation(afQuery query, Map<String, Object> new_values) throws Exception {
        super(query);
        query.copyLogConfigTo(this);
        this.new_values = new_values;
    }

    public afUpdateOperation ignore (String[] column_names) {
        for (String column : column_names)
            ignored_cols.add(column);
        return this;
    }

    public int end () throws Exception {
        String table_name = this.getTable_name();
        
        basic_query = "UPDATE " + table_name + " SET ";
        Object[] keys = new_values.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            String key = (String) keys[i];

            basic_query += key + " = ?";
            basic_query += i + 1 == keys.length ? "" : ", ";

            // variables
            req_variables.add(new_values.get(key));
        }

        String sql = basic_query + (where_clause == null ? "" : " WHERE " + where_clause);

        Object[] variables = this.getAllVariables().toArray();
        return afquery.run(sql, variables).end();
    }

    public afUpdateOperation where (String where) {
        where_clause = where;
        return this;
    }

    public afUpdateOperation where (String where, Object[] variables) {
        where_clause = where;
        for (Object var : variables)
            where_variables.add(var);
        return this;
    }  
}