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

    public static Integer deleteShopByOwnerId(Connection con, Integer id){
        // 根据商家id删除商店
        Integer resultShop = -1;
        Integer resultCommodity = -1;
        try {
            ArrayList<Shop> shops = ShopInterface.getShopsByOwnerId(id);
            for(Shop shop : shops){
                resultCommodity = CommodityInterface.deleteCommodityByShopId(con, shop.getId());
            }
            String sql = "DELETE FROM shop WHERE owner_id = ?";
            PreparedStatement ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            resultShop = ptmt.executeUpdate();
            ptmt.close();
            if(resultShop < 0 || resultCommodity == -1){
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
    public static Integer deleteShopById(Integer id){
        // 根据商家id删除商店
        Integer resultCommodity = -1;
        Integer resultShop = -1;
        Connection con = null;
        try {
            con = SqlConnection.getConnection();
            con.setAutoCommit(false);
            resultCommodity = CommodityInterface.deleteCommodityByShopId(con, id);
            if(resultCommodity == -1){
                con.rollback();
                con.close();
                return -1;
            }
            String sql = "DELETE FROM shop WHERE id = ?";
            PreparedStatement ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            resultShop = ptmt.executeUpdate();
            ptmt.close();
            if(resultShop < 1){
                con.rollback();
                con.close();
                return -1;
            }
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try{
                con.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
                return -1;
            }
            return -1;
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
                return -1;
            }
        }
        return 1;
    }
}

