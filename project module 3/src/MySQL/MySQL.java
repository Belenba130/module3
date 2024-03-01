package MySQL;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQL {
    public static Connection open() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/MySQL";
            String user = "root";
            String password = "NVM131097";
            Connection con = java.sql.DriverManager.getConnection(url, user, password);
            return con;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void close(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
