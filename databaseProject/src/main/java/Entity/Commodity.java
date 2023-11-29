package Entity;

public class Commodity {
    private int id;
    private String name;
    private int price;
    private String shop;

    private String platform;

    public Commodity(int id, String name, int price, String shop, String platform) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shop = shop;
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getShop() {
        return shop;
    }

    public String getPlatform() {
        return platform;
    }
}