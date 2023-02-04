
package connection;

import java.sql.*;

public class Connector {

    public static Connection con;
    public static Statement st;
    private static ResultSet rs;

    static {
        try {
            connect();
        } catch (Exception e) {
        }
    }

    static void connect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/javaproject";
        con = DriverManager.getConnection(url, "root", "");
        st = con.createStatement();
        System.out.println("Connected");
    }

    public static ResultSet getData(String query) {
        try {
            rs = st.executeQuery(query);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        return rs;
    }

    public static void updateData(String query) {
        try {
            System.out.println(st.executeUpdate(query) + "Rows affected");
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
