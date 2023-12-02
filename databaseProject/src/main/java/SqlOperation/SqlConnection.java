package SqlOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    private static String url = "jdbc:mysql://localhost:3306/database_design_pj";
    private static String userName = "root";

    private static String passWord = "root";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,userName,passWord);
        return con;
    }
}