package connect;

import core.afQuery;
import java.sql.Connection;
import operation.afReadOperation;
/**
 * @author Michael
 */
public class Utils {
    
    public static String majFirst(String tmp) {
        String a = tmp.substring(0,1).toUpperCase();
        return a + tmp.substring(1, tmp.length());
    }

    public static Object[] select(Connection c, String table, Object objinstance, String where, Object[] values) throws Exception{
        afQuery query = afQuery.use(c);
        afReadOperation op = query.of(objinstance, table).select();
        if (where != null && values == null)
            op = op.where(where);
        if (where != null && values != null)
            op = op.where(where, values);
        
        return op.get().toArray();
    }

    public static Object[] select(String table, Object objinstance, String where, Object[] values) throws Exception{
        //retourne un TABLEAU D obj qui est soit une tab de Dept ou de Emp
        DBConnect db = new DBConnect();
        Connection c = db.getConnection();
        Object[] valiny = select(c, table, objinstance, where, values);
        c.close();
        return valiny;		
    }

    //insere n importe quel objet et annule les quotes sur un attrib spef utile pour
    //les sequences
    public static int insert(Connection c, Object o, String attribtoignore) throws Exception {
        afQuery query = afQuery.use(c);
        return query.of(o)
                    .insert()
                    .end();
    }
}