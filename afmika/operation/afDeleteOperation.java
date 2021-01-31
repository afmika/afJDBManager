package operation;

import core.*;
/**
 * @author afmika
 */
public class afDeleteOperation extends afOperation {
    public afDeleteOperation(afQuery query) throws Exception {
        super(query);
        query.copyLogConfigTo(this);
    }

    public int end () throws Exception {
        String table_name = this.getTable_name();
        
        basic_query = "DELETE FROM " + table_name;
        String sql = basic_query + (where_clause == null ? "" : " WHERE " + where_clause);

        Object[] variables = this.getAllVariables().toArray();
        return afquery.run(sql, variables).end();
    }

    public afDeleteOperation where (String where) {
        where_clause = where;
        return this;
    }

    public afDeleteOperation where (String where, Object[] variables) {
        where_clause = where;
        for (Object var : variables)
            where_variables.add(var);
        return this;
    }
}
