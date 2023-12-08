package InterfaceImplementation;


import SqlOperation.SqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Entity.Price;
import Entity.User;
import java.util.ArrayList;

import static InterfaceImplementation.CommodityInterface.getCommodityPriceHistory;


/**
 * @Description：
 * @Author Huangzisu
 * @date 2023-12-02
 **/
public class PriceInterface {
    public static Integer insertPrice(Connection conn, Integer cId, Double price) {
        Integer result = -1;
        try{
            String sql = "insert into price(c_id, price, time) values(?, ?, current_timestamp)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cId);
            ps.setDouble(2, price);
            result =  ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Price> getHistoryPrice(Integer cId, User user,int option) throws SQLException, ClassNotFoundException {
        ArrayList<Price> priceArrayList = new ArrayList<>();
        if(user.getRole() !=0){
            return priceArrayList;
        }
        Connection con = SqlConnection.getConnection();
        priceArrayList = getCommodityPriceHistory(con,cId,option);
        return priceArrayList;
    }

    public static Integer deletePriceByCommodityId(Connection con, Integer id){
        Integer result = -1;
        try{
            String sql = "DELETE FROM price WHERE c_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            result = pstmt.executeUpdate();
            pstmt.close();
            if(result < 0)  return -1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

}
