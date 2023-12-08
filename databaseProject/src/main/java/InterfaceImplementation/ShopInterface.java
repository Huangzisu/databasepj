package InterfaceImplementation;


import Entity.Shop;
import SqlOperation.SqlConnection;


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

    public static Shop getShopInfoByName(String name){
        Shop shop = null;
        try{
            Connection conn = SqlConnection.getConnection();
            String sql = "select * from shop where name = ? LIMIT 1";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
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

    public static ArrayList<Shop> getAllShops(){
        ArrayList<Shop> shops = new ArrayList<>();
        try {
            Connection conn = SqlConnection.getConnection();
            String sql = "SELECT * FROM shop";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Shop shop = new Shop(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getInt("owner_id"));
                shops.add(shop);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shops;
    }

    public static ArrayList<Shop> getShopsByOwnerId(Integer ownerId) {
        ArrayList<Shop> shops = new ArrayList<>();
        try {
            Connection conn = SqlConnection.getConnection();
            String sql = "SELECT * FROM shop WHERE owner_id = ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, ownerId);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Shop shop = new Shop(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getInt("owner_id"));
                shops.add(shop);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shops;
    }

    public static Integer updateShopInfo(Integer id, String name, String address){
        Integer result = 0;
        try{
            Connection con = SqlConnection.getConnection();
            String sql = "UPDATE shop SET name = ?, address = ? WHERE id = " + id;
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,address);
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

