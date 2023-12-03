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
    public static Integer insertPrice(Connection conn, Integer cId, Integer price) {
        Integer result = -1;
        try{
            String sql = "insert into price(c_id, price, time) values(?, ?, current_timestamp)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cId);
            ps.setInt(2, price);
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

}
