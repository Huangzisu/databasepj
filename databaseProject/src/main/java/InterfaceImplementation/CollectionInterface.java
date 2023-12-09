package InterfaceImplementation;

import Entity.User;
import SqlOperation.SqlConnection;

import java.sql.*;

public class CollectionInterface {

    public static int addCollectionCommodity(int cId, User user,double floorPrice) throws SQLException, ClassNotFoundException {
        int returnValue = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        if (user.getRole() == 0) {
            try {
                con = SqlConnection.getConnection();
                con.setAutoCommit(false); // 关闭自动提交
                String sqlInsert = "INSERT INTO collection (u_id, c_id,floorprice) VALUES (?, ?,?)";
                pstmt = con.prepareStatement(sqlInsert);
                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, cId);
                pstmt.setDouble(3,floorPrice);
                pstmt.executeUpdate();
                con.commit(); // 提交事务
                returnValue = 1;
            }
            catch (SQLException e) {
                if (con != null) {
                    con.rollback(); // 发生异常，回滚事务
                    return -1;
                }
                throw e;
            } finally {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                if (con != null) {
                    con.setAutoCommit(true); // 恢复自动提交
                    con.close();
                }
            }
        } else {
            returnValue = -1;
        }

        return returnValue;
    }

    public static Integer getMostPopularCommodityId(){
        Integer resultId = -1;
        try{
            Connection conn = SqlConnection.getConnection();

            String sql = "SELECT c_id, COUNT(c_id) AS count\n" +
                    "FROM collection\n" +
                    "GROUP BY c_id\n" +
                    "ORDER BY count DESC\n" +
                    "LIMIT 1;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                resultId = resultSet.getInt("c_id");
            }
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultId;
    }

    public static Integer deleteCollectionByUserId(Connection con, Integer userId){
        Integer result = -1;
        try{
            String sql = "DELETE FROM collection WHERE u_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,userId);
            result = pstmt.executeUpdate();
            pstmt.close();
            if(result < 0)  return -1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
    public static Integer deleteCollectionByCommodityId(Connection con, Integer commodityId){
        Integer result = -1;
        try{
            String sql = "DELETE FROM collection WHERE c_id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,commodityId);
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
