package operation;

import core.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author afmika
 */
public class afInsertOperation extends afOperation {

    public afInsertOperation(afQuery query) throws Exception {
        super(query);
        query.copyLogConfigTo(this);
    }

    public afInsertOperation ignore (String[] column_names) {
        for (String column : column_names)
            ignored_cols.add(column);
        return this;
    }

    public int end () throws Exception {
        Object instance = this.getAfquery().getInstance();
        Class<?> inst_class = instance.getClass();
        String table_name = this.getTable_name();
        
        Map<String, Field> fieldMap = afReflectTools.extractFieldWithProperAliases(instance);
        Set<String> fieldAliases = fieldMap.keySet();
        int i = 0;
        basic_query = "INSERT INTO " + table_name + "";
        String values_enum = "(";
        String insert_enum = "(";
        for (String alias : fieldAliases) {
            Field field = fieldMap.get(alias);
            values_enum += alias;

            insert_enum += "?";
            values_enum += i + 1 == fieldAliases.size() ? "" : ", ";
            insert_enum += i + 1 == fieldAliases.size() ? "" : ", ";

            // variables
            String meth_name = afReflectTools.capitalizeFirstLetter(field.getName());
            Method method = inst_class.getMethod("get" + meth_name);

            req_variables.add(method.invoke(instance));
            
            i++;
        }
        values_enum += ")";
        insert_enum += ")";

        String sql = basic_query + " " + values_enum + " VALUES " + insert_enum;

        Object[] variables = this.getAllVariables().toArray();
        return afquery.run(sql, variables).end();
    }
}
