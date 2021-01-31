package operation;

import core.*;

import java.util.ArrayList;

/**
 * @author afmika
 */
public class afReadOperation extends afOperation {

    public afReadOperation(afQuery query) throws Exception {
        super(query);
        query.copyLogConfigTo(this);
    }

    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> get () throws Exception {
        String table_name = this.getTable_name();
        
        basic_query = "SELECT * FROM " + table_name;
        String sql = basic_query + (where_clause == null ? "" : " WHERE " + where_clause);

        Object[] variables = this.getAllVariables().toArray();
        return afquery.run(sql, variables).<T>get((T) this.afquery.getInstance());
    }

    public afReadOperation where (String where) {
        where_clause = where;
        return this;
    }

    public afReadOperation where (String where, Object[] variables) {
        where_clause = where;
        for (Object var : variables)
            where_variables.add(var);
        return this;
    }  
}