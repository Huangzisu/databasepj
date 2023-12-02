package InterfaceImplementation;

import Entity.Message;
import SqlOperation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @Description：
 * @Author Huangzisu
 * @date 2023-12-02
 **/
public class MessageInterface {
    public static ArrayList<Message> getMessageByUserId(int userId) {
        ArrayList<Message> messages = new ArrayList<>();
        try {
            Connection conn = SqlConnection.getConnection();
            String sql = "select * from message where u_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Message message = new Message(rs.getInt("m_id"), rs.getInt("u_id"), rs.getString("content"), rs.getTimestamp("time"));
                messages.add(message);
            }
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
