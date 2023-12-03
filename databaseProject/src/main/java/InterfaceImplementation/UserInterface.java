package InterfaceImplementation;

import Entity.User;
import SqlOperation.SqlConnection;

import java.sql.*;

public class UserInterface {
    public static int login(String id, String password, User user) throws SQLException, ClassNotFoundException {
        Connection con = SqlConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from user where id = " + id + " and password = " + password);
        if (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
            user.setPhoneNumber(resultSet.getInt("phoneNumber"));
            user.setRole(resultSet.getInt("role"));
            stmt.close();
            con.close();
            return 1;
        }
        else {
            stmt.close();
            con.close();
            return -1;
        }
    }



    public static void getUserInfo(User user){
        System.out.println("id:"+user.getId());
        System.out.println("name:"+user.getName());
        System.out.println("age:"+user.getAge());
        System.out.println("phoneNumber:"+user.getPhoneNumber());
        System.out.println("role:"+user.getRole());
    }
}
