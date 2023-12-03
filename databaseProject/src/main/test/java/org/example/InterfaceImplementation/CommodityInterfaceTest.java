package InterfaceImplementation;

import Entity.Price;
import SqlOperation.SqlConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CommodityInterfaceTest {
    @Test
    public void releaseNewCommodity() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(currentDate);
        Integer result = CommodityInterface.releaseNewCommodity("test_commodity", "test_category",
                "test_description", dateString, 10000001, 1000, 10000001);
        assertEquals(1, result);
    }
    @Test
    public void updateCommodityInfo() {
        Integer result = CommodityInterface.updateCommodityInfo(10000004, "update_test_commodity", "update_test_category",
                "update_test_description", "2020-12-02");
        assertEquals(1, result);
    }
    @Test
    public void getCommodityPriceHistory(){
        try{
            Connection conn = SqlConnection.getConnection();
            ArrayList<Price> priceHistory = CommodityInterface.getCommodityPriceHistory(conn, 10000001, 0);
            for(Price price : priceHistory){
                System.out.println(price.getcId() + " " + price.getPrice() + " " + price.getTime());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void isInOneDayFromNow(){
        String time = "2023-12-03 01:28:04";
        Boolean result = CommodityInterface.isInOneDayFromNow(CommodityInterface.convertToTimestamp(time));
        assertTrue(result);
    }
    @Test
    public void updateCommodityPrice() {
        Integer result = CommodityInterface.updateCommodityPrice(10000004, 70);
        assertEquals(-2, result);
        Integer result2 = CommodityInterface.updateCommodityPrice(10000001, 1500);
        assertEquals(1, result2);
    }

    @Test
    public void administratorUpdateCommodityInfo() {
        Integer result = CommodityInterface.administratorUpdateCommodityInfo(10000004, "update_administrator", "update_administrator",
                "update_administrator", "2020-12-03", 10000002, 10000001);
        assertEquals(-1, result);
        result = CommodityInterface.administratorUpdateCommodityInfo(10000004, "update_administrator", "update_administrator",
                "update_administrator", "2020-12-03", 10000001, 10000002);
        assertEquals(-1, result);
        result = CommodityInterface.administratorUpdateCommodityInfo(10000004, "update_administrator", "update_administrator",
                "update_administrator", "2020-12-03", 10000001, 10000001);
        assertEquals(1, result);
    }
    @Test
    public void getMostPopularCommodityId(){
        try{
            Integer result = CommodityInterface.getMostPopularCommodityId();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}