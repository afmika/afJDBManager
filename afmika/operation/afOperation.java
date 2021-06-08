package operation;

import core.*;

import java.util.ArrayList;
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

    public String getTable_name () throws Exception {
        String table_name = getAfquery().getTable_name();
        if (table_name != null)
            return table_name; // for example a view
        return afReflectTools.extractTableTarget(this.getAfquery().getInstance());
    }
    
    public afQuery getAfquery () {
        return this.afquery;
    }
}