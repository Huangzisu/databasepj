package InterfaceImplementation;

import Entity.User;
import SqlOperation.SqlConnection;

import java.sql.*;

public class CollectionInterface {

    public static int addCollectionCommodity(int cId, User user) throws SQLException, ClassNotFoundException {
        int returnValue = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        if (user.getRole() == 0) {
            try {
                con = SqlConnection.getConnection();
                con.setAutoCommit(false); // 关闭自动提交
                String sqlInsert = "INSERT INTO collection_commodity (u_id, c_id) VALUES (?, ?)";
                pstmt = con.prepareStatement(sqlInsert);
                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, cId);
                pstmt.executeUpdate();
                con.commit(); // 提交事务
                returnValue = 1;
                }
            catch (SQLException e) {
                if (con != null) {
                    con.rollback(); // 发生异常，回滚事务
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
    public static int setFloorPrice(float floorPrice, User user) throws SQLException, ClassNotFoundException {
        int returnValue = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet resultSet = null;

        if (floorPrice < 0) return -1;
        if (user.getRole() == 0) {
            try {
                con = SqlConnection.getConnection();
                con.setAutoCommit(false); // 关闭自动提交

                stmt = con.createStatement();
                resultSet = stmt.executeQuery("SELECT * FROM collection_floorprice WHERE u_id=" + user.getId());

                if (!resultSet.next()) {
                    String sqlInsert = "INSERT INTO collection_floorprice (u_id, floorPrice) VALUES (?, ?)";
                    pstmt = con.prepareStatement(sqlInsert);
                    pstmt.setInt(1, user.getId());
                    pstmt.setFloat(2, floorPrice);
                } else {
                    String sqlUpdate = "UPDATE collection_floorprice SET floorprice = ? WHERE u_id = ?";
                    pstmt = con.prepareStatement(sqlUpdate);
                    pstmt.setFloat(1, floorPrice);
                    pstmt.setInt(2, user.getId());
                }

                pstmt.executeUpdate();
                con.commit(); // 提交事务
                returnValue = 1;
            } catch (SQLException e) {
                if (con != null) {
                    con.rollback(); // 发生异常，回滚事务
                }
                throw e;
            } finally {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                if (stmt != null) stmt.close();
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
}
