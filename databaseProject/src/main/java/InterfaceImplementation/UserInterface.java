package InterfaceImplementation;

import Entity.User;
import SqlOperation.SqlConnection;

import java.sql.*;

public class UserInterface {
    public static int login(String id, String password, User user) throws SQLException, ClassNotFoundException {
        Connection con = SqlConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement("select * from user where id = ? and password = ?");
        stmt.setString(1, id);
        stmt.setString(2, password);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
            user.setPhoneNumber(resultSet.getString("phoneNumber"));
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


    public static Integer updateUserInfo(Integer id, String name, Integer age, String phoneNumber, String gender) {
        Integer result = 0;
        try{
            Connection con = SqlConnection.getConnection();
            String sql = "UPDATE user SET name = ?, age = ?, phoneNumber = ?, gender = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setInt(2,age);
            pstmt.setString(3,phoneNumber);
            pstmt.setString(4, gender);
            pstmt.setInt(5,id);
            result = pstmt.executeUpdate();
            pstmt.close();
            con.close();
            if(result != 1)  return -1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
