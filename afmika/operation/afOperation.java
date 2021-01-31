package operation;

import core.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * @author afmika
 */
public class afOperation extends afLoggable {
    afQuery afquery = null;  // the query responsible for generating this object

    String basic_query = null;
    String where_clause = null;

    ArrayList<Object> req_variables = new ArrayList<>();
    ArrayList<Object> where_variables = new ArrayList<>();

    Set<String> ignored_cols = new HashSet<>();

    public afOperation(final afQuery afquery) throws Exception {
        this.init(afquery);
        afquery.copyLogConfigTo(this);
    }

    private void init(final afQuery afquery) throws Exception {
        if (afquery == null)
            throw new Exception("afquery received is NULL !");
        this.afquery = afquery;
    }

    public ArrayList<Object> getAllVariables () {
        ArrayList<Object> out = new ArrayList<>();

        for (Object value : req_variables) out.add(value);
        for (Object value : where_variables) out.add(value);

        return out;
    }

    public String getTable_name () {
        Class<?> inst_class = this.getAfquery().getInstance().getClass();
        String table_name = this.getAfquery().getTable_name();
        
        if (table_name == null) {
            String[] names = inst_class.getName().split("\\.");
            return names[Math.max(names.length - 1, 0)];
        }

        return table_name;
    }
    
    public afQuery getAfquery () {
        return this.afquery;
    }

    public Field[] getAllNonIgnoredFields () {
        Object instance = this.getAfquery().getInstance();
        Field[] fields = afReflectTools.extractPublicAndFriendlyFields(instance);

        return Arrays.asList(fields)
                    .stream()
                    .filter((Field field) -> {
                        boolean found = false;
                        for (String str : ignored_cols)
                            found = str.equals(field.getName());
                        return !found;
                    })
                    .toArray(Field[]::new);
    }
}