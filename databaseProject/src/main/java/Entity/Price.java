package Entity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Description：
 * @Author Huangzisu
 * @date 2023-12-02
 **/
public class Price {
    private Integer cId;
    private Integer price;
    private String time;
    public Price(Integer cId, Integer price, String time) {
        this.cId = cId;
        this.price = price;
        this.time = time;
    }

    public static float getMaxPrice(ArrayList<Price> priceList){
        float maxPrice = 0;
        for (Price price : priceList) {
            if (price.getPrice() > maxPrice) {
                maxPrice = price.getPrice();
            }
        }
        return maxPrice;
    }

    public static float getMinPrice(ArrayList<Price> priceList){
        float minPrice = 1000000000;
        for (Price price : priceList) {
            if (price.getPrice() < minPrice) {
                minPrice = price.getPrice();
            }
        }
        return minPrice;
    }

    public static float getDiffPrice(ArrayList<Price> priceList){
        float maxPrice = Price.getMaxPrice(priceList);
        float minPrice = Price.getMinPrice(priceList);
        return maxPrice - minPrice;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
