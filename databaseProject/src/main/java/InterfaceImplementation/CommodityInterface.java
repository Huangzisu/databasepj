package InterfaceImplementation;

import Entity.Commodity;
import Entity.DetailedCommodity;
import Entity.Price;
import Entity.User;
import SqlOperation.SqlConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
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

    public static Integer releaseNewCommodity(String name, String category, String description,
                                              String produceDate, Integer shopId, Integer price, Integer platformId) {
        Connection conn = null;
        try {
            conn = SqlConnection.getConnection();
            // 关闭自动提交
            conn.setAutoCommit(false);
            String insertCommoditySql = "INSERT INTO commodity (name, category, description, produceDate, " +
                    "s_id, p_id) VALUES (?, ?, ?, ?, ?, ?)";
            // 插入新商品
            PreparedStatement insertCommodityStmt = conn.prepareStatement(insertCommoditySql, Statement.RETURN_GENERATED_KEYS);
            insertCommodityStmt.setString(1, name);
            insertCommodityStmt.setString(2, category);
            insertCommodityStmt.setString(3, description);
            insertCommodityStmt.setString(4, produceDate);
            insertCommodityStmt.setInt(5, shopId);
            insertCommodityStmt.setInt(6, platformId);

            insertCommodityStmt.executeUpdate();
            ResultSet generatedKeys = insertCommodityStmt.getGeneratedKeys();
            Integer generatedId = null;
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
            // 插入新商品价格
            PriceInterface.insertPrice(conn, generatedId, price);
            //事务提交
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try{
                conn.rollback();
            }catch (Exception e1){
                e1.printStackTrace();
                return -1;
            }
            return -1;
        } finally {
            try{
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
                return -1;
            }
        }
        return 1;
    }

    public static Integer updateCommodityInfo(Integer id, String name, String category, String description,
                                              String produceDate) {
        Integer result = 0;
        try {
            Connection conn = SqlConnection.getConnection();

            String updateCommoditySql = "UPDATE commodity SET name = ?, category = ?, description = ?, " +
                    "produceDate = ? WHERE id = ?";
            // 插入新商品
            PreparedStatement updateCommodityStmt = conn.prepareStatement(updateCommoditySql);
            updateCommodityStmt.setString(1, name);
            updateCommodityStmt.setString(2, category);
            updateCommodityStmt.setString(3, description);
            updateCommodityStmt.setString(4, produceDate);
            updateCommodityStmt.setInt(5, id);

            result = updateCommodityStmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        if(result != 1){
            return -1;
        }
        return 1;
    }

    public static Integer updateCommodityPrice(Integer id, Integer price) {
        Integer result = 0;
        try {
            Connection conn = SqlConnection.getConnection();

            // 查询目标商品价格历史，判断是否满足更新条件

            ArrayList<Price> priceArrayList = getCommodityPriceHistory(conn, id,0);

            if(priceArrayList == null || priceArrayList.size() < 1) { // 未查询到商品价格历史
                return -1;
            }
            if(priceArrayList.size() > 1 && isInOneDayFromNow(convertToTimestamp(priceArrayList.get(0).getTime()))) { // 已经修改过商品价格，检查是否在24小时内
                return -2;
            }

            // 更新商品价格（插入新价格）
            String insertPriceSql = "INSERT INTO price (c_id, price, time) VALUES (?, ?, current_timestamp)";

            PreparedStatement updateCommodityStmt = conn.prepareStatement(insertPriceSql);
            updateCommodityStmt.setInt(1, id);
            updateCommodityStmt.setInt(2, price);

            result = updateCommodityStmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        if(result != 1){
            return -1;
        }
        return 1;
    }

    public static ArrayList<Price> getCommodityPriceHistory(Connection conn, Integer id,int option) {
        //option用来设置查询范围，0代表所有日期，1代表近一星期，2代表近一月，3代表近一年
        String[] timeRange = {"","and time > DATE_SUB(CURDATE(), INTERVAL 7 DAY)",
                "and time > DATE_SUB(CURDATE(), INTERVAL 1 MONTH)","and time > DATE_SUB(CURDATE(), INTERVAL 1 YEAR)"};
        ArrayList<Price> priceArrayList = new ArrayList<>();
        try {
            // 查询目标商品价格历史，判断是否满足更新条件
            String selectPriceSql = "SELECT * FROM price WHERE c_id = ?"+timeRange[option]+" ORDER BY time DESC";

            PreparedStatement selectPriceStmt = conn.prepareStatement(selectPriceSql);
            selectPriceStmt.setInt(1, id);

            ResultSet resultSet = selectPriceStmt.executeQuery();
            while (resultSet.next()) {
                priceArrayList.add(new Price(resultSet.getInt("c_id"),
                        resultSet.getInt("price"), resultSet.getString("time")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return priceArrayList;
    }


    public static Integer administratorUpdateCommodityInfo(Integer id, String name, String category, String description,
                                              String produceDate, Integer s_id, Integer p_id) {
        Integer result = 0;
        try {
            Connection conn = SqlConnection.getConnection();

            String updateCommoditySql = "UPDATE commodity SET name = ?, category = ?, description = ?, " +
                    "produceDate = ?, s_id = ?, p_id = ? WHERE id = ?";
            // 插入新商品
            PreparedStatement updateCommodityStmt = conn.prepareStatement(updateCommoditySql);
            updateCommodityStmt.setString(1, name);
            updateCommodityStmt.setString(2, category);
            updateCommodityStmt.setString(3, description);
            updateCommodityStmt.setString(4, produceDate);
            updateCommodityStmt.setInt(5, s_id);
            updateCommodityStmt.setInt(6, p_id);
            updateCommodityStmt.setInt(7, id);

            result = updateCommodityStmt.executeUpdate();

            conn.close();
            if(result != 1){
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public static Timestamp convertToTimestamp(String timestampString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parsedDate = dateFormat.parse(timestampString);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean isInOneDayFromNow(Timestamp timestamp) {
        // 获取当前时间的时间戳
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        // 计算时间差
        long timeDifference = currentTime.getTime() - timestamp.getTime();
        // 判断时间差是否在24小时内
        if (timeDifference <= 24 * 60 * 60 * 1000) {
            return true;
        }
        return false;
    }

    public static Boolean batchImportCommodity(String path) throws SQLException {
        Connection con = null;
        try {
            con = SqlConnection.getConnection();
            con.setAutoCommit(false);
            String sql = "LOAD DATA LOCAL INFILE ? INTO TABLE commodity FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' (name, category,description,produceDate, shopId, price, platformId)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, path);
            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
            con.setAutoCommit(true);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            con.setAutoCommit(true);
            return false;
        }
        return true;
    }
}

