package InterfaceImplementation;

import Entity.Message;
import Entity.Price;
import Entity.Shop;
import SqlOperation.SqlConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * @Description：
 * @Author Huangzisu
 * @date 2023-12-02
 **/
public class ShopInterface {
    public static Shop getShopInfoById(Integer id){
        Shop shop = null;
        try{
            Connection conn = SqlConnection.getConnection();
            String sql = "select * from shop where id = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                shop = new Shop(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getInt("owner_id"));
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return shop;
    }

}
