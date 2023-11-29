package Entity;

public class DetailedCommodity extends Commodity {
//    private int id;
//    private String name;
//    private int price;
//    private String shop;
//
//    private String platform;
    private String category;
    private String description;
    private String productionDate;
    private String shopName;

    public DetailedCommodity(int id, String name, int price, String shop, String platform, String category, String description, String productionDate, String shopName, String shopAddress) {
        super(id, name, price, shop, platform);
        this.category = category;
        this.description = description;
        this.productionDate = productionDate;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
    }
    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    private String shopAddress;

}
