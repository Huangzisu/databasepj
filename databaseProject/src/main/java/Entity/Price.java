package Entity;

import java.sql.Timestamp;

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
