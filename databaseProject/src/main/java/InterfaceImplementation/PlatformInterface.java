package InterfaceImplementation;

import Entity.Platform;
import Entity.Shop;
import SqlOperation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Description：
 * @Author Huangzisu
 * @date 2023-12-06
 **/
public class PlatformInterface {
    public static Platform getPlatformByName(String name){
        Platform platform = null;
        try{
            Connection conn = SqlConnection.getConnection();
            String sql = "select * from platform where name = ? LIMIT 1";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                 platform = new Platform(rs.getInt("id"), rs.getString("name"));
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return platform;
    }
}
