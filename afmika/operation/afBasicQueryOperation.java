package operation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import core.*;

public class afBasicQueryOperation extends afOperation {
    String sql_query = null;

    public afBasicQueryOperation(afQuery afquery, String sql, Object[] variables) throws Exception {
        super(afquery);
        this.sql_query = sql;
        if (variables != null) {
            for (Object var : variables)
                req_variables.add(var);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> get(T instance) throws Exception {
        ArrayList<T> result = new ArrayList<>();
        String sql = sql_query;

        this.log(sql_query);
        this.err(":: direct query vars " + req_variables);

        Connection connection = this.afquery.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        ArrayList<Object> variables = getAllVariables();
        int i = 1;
        for (Object value : variables)
            statement.setObject(i++, value);

        ResultSet res_set = statement.executeQuery();

        try {
            // conversion
            Object object = instance;
            Class<?> cls = object.getClass();

            Field[] fields = afReflectTools.extractPublicAndFriendlyFields(object);
            while (res_set.next()) {
                T temp = (T) cls.newInstance();

                for (Field field : fields) {
                    String type = field.getType().getName();
                    String name = field.getName();
                    String meth_name = "set" + afReflectTools.capitalizeFirstLetter(name);
                    Method method = temp.getClass().getMethod(meth_name, field.getType());

                    if (type.equals("int")) {
                        method.invoke(temp, res_set.getInt(name));
                    } else if (type.equals("double")) {
                        method.invoke(temp, res_set.getDouble(name));
                    } else if (type.equals("float")) {
                        method.invoke(temp, res_set.getFloat(name));
                    } else if (type.equals("java.math.BigDecimal")) {
                        method.invoke(temp, res_set.getBigDecimal(name));
                    } else if (type.equals("java.sql.Date")) {
                        method.invoke(temp, res_set.getDate(name));
                    } else if (type.equals("java.sql.Timestamp")) {
                        method.invoke(temp, res_set.getTimestamp(name));
                    } else {
                        method.invoke(temp, res_set.getString(name));
                    }

                }
                result.add(temp);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            res_set.close();
            statement.close();
        }

        return result;
    }

    public int end() throws SQLException {
        String sql = sql_query;

        this.log(sql);
        this.err(":: direct query vars " + req_variables);

        PreparedStatement statement = null;
        try {
            Connection connection = this.afquery.getConnection();
            statement = connection.prepareStatement(sql);
            ArrayList<Object> variables = this.getAllVariables();
            int out = 0;
            int i = 1;
            for (Object value : variables)
                statement.setObject(i++, value);
            out = statement.executeUpdate();

            return out;
        } catch(Exception ex) {
            throw ex;
        } finally {
            if (statement != null) statement.close();
        }
    }
}
