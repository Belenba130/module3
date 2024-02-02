package util;

import java.sql.Connection;

public class MySQL {
    public static Connection open() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/tickelfilm";
            String user = "root";
            String password = "NVM131097";
            Connection con = java.sql.DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
