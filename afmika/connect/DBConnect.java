package connect;
import java.sql.*;

public class DBConnect {
    String username;
    String password;
    
    public DBConnect() {
        this.username = "";
        this.password = "";
    }
    
    public DBConnect(String user, String password) {
        this.username = user;
        this.password = password;
    }
    
    public Connection getConnection() {
        Connection connection = null;
        try {
            String nomDriver = "oracle.jdbc.driver.OracleDriver";
            Class.forName(nomDriver);
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, password);
        } catch (Exception e) {
            System.out.println("DATABASE CONNECTION ERROR");
            e.printStackTrace();
            return connection;
        }
    }
}