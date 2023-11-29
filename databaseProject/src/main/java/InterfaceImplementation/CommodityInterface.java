package InterfaceImplementation;

import Entity.Commodity;
import Entity.DetailedCommodity;
import Entity.User;
import SqlOperation.SqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class CommodityInterface {
    public static ArrayList<Commodity> searchCommodity(String name, User user) throws SQLException, ClassNotFoundException {
        if(user.getRole()!=0){
            return null;
        }
        ArrayList<Commodity> commodityArrayList = new ArrayList<>();
        Connection con = SqlConnection.getConnection();
        String sql = "SELECT t1.id, t1.name as commodityName, t2.name as platformName, t3.name as shopName, t4.price " +
                "FROM commodity t1 " +
                "INNER JOIN platform t2 ON t1.p_id = t2.id " +
                "INNER JOIN shop t3 ON t1.s_id = t3.id " +
                "INNER JOIN price t4 ON t4.c_id = t1.id " +
                "WHERE t1.name LIKE ? AND t4.time = ( " +
                "SELECT MAX(time) " +
                "FROM price " +
                "WHERE c_id = t1.id)";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "%" + name + "%");
        ResultSet resultSet = pstmt.executeQuery();
        if(resultSet.next()){
            do{
                commodityArrayList.add(new Commodity(resultSet.getInt("id"), resultSet.getString("commodityName"),
                        resultSet.getInt("price"), resultSet.getString("shopName"), resultSet.getString("platformName")));
            }while(resultSet.next());
        }
        pstmt.close();
        con.close();
        return commodityArrayList;
    }

    public static DetailedCommodity getDetailedCommodityInfo(int id, User user) throws SQLException, ClassNotFoundException {
        if(user.getRole()!=0){
            return null;
        }
        Connection con = SqlConnection.getConnection();
        String sql = "SELECT t1.id, t1.name as commodityName,t1.category,t1.produceDate,t1.description ,t2.name as platformName, t3.name as shopName, t3.address,t4.price"
        +"FROM commodity t1"
        +"INNER JOIN platform t2 ON t1.p_id = t2.id"
        +"INNER JOIN shop t3 ON t1.s_id = t3.id"
        +"INNER JOIN price t4 ON t4.c_id = t1.id"
        +"WHERE t1.id=? AND t4.time = ("
        +"SELECT MAX(time)"
        +"FROM price"
        +"WHERE c_id = t1.id)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet resultSet = pstmt.executeQuery();
        if(resultSet.next()){
            DetailedCommodity detailedCommodity = new DetailedCommodity(resultSet.getInt("id"), resultSet.getString("commodityName"),
                    resultSet.getInt("price"), resultSet.getString("shopName"), resultSet.getString("platformName"),
                    resultSet.getString("category"), resultSet.getString("description"), resultSet.getString("produceDate"),
                    resultSet.getString("shopName"), resultSet.getString("address"));
            pstmt.close();
            con.close();
            return detailedCommodity;
        }
        else{
            pstmt.close();
            con.close();
            return null;
        }
    }
}
