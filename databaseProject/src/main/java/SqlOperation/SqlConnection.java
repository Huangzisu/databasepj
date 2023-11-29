package SqlOperation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    private static String url = "jdbc:mysql://localhost:3306/databasepj";
    private static String userName = "root";

    private static String passWord = "Qizhi2002";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,userName,passWord);
        return con;
    }
}