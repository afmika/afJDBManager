package core;

import java.sql.Connection;

/**
 * @author Michael
 */
public interface afQueryable {
    // main overridable requests
    public int save (Connection connection) throws Exception;
    public int remove (Connection connection) throws Exception;
}
