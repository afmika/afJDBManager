package operation;

import core.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        
        Field[] fields = this.getAllNonIgnoredFields();

        basic_query = "INSERT INTO " + table_name + "";
        String values_enum = "(";
        String insert_enum = "(";
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            values_enum += field.getName();

            insert_enum += "?";
            values_enum += i + 1 == fields.length ? "" : ", ";
            insert_enum += i + 1 == fields.length ? "" : ", ";


            // variables
            String meth_name = afReflectTools.capitalizeFirstLetter(field.getName());
            Method method = inst_class.getMethod("get" + meth_name);

            req_variables.add(method.invoke(instance));
        }
        values_enum += ")";
        insert_enum += ")";

        String sql = basic_query + " " + values_enum + " VALUES " + insert_enum;

        Object[] variables = this.getAllVariables().toArray();
        return afquery.run(sql, variables).end();
    }
}
