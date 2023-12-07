package Entity;

public class Commodity {
    private int id;
    private String name;
    private int price;
    private String shop;

    private String platform;

    private String origin;

    public Commodity(int id, String name, int price, String shop, String platform, String origin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shop = shop;
        this.platform = platform;
        this.origin = origin;
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
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}