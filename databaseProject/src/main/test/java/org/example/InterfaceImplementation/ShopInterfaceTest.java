package InterfaceImplementation;

import Entity.Shop;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ShopInterfaceTest {

    @Test
    public void getShopInfoById() {
        Shop shop = ShopInterface.getShopInfoById(10000001);
        System.out.println(shop.getId() + " " + shop.getName() + " " + shop.getAddress() + " " + shop.getOwnerId());
    }
}