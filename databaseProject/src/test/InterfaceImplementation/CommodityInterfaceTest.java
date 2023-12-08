package InterfaceImplementation;

import Entity.DetailedCommodity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommodityInterfaceTest {
    @Test
    public void testGetAllCommoditiesByShopId(){
        ArrayList<DetailedCommodity> result = CommodityInterface.getAllCommoditiesByShopId(10000001);
        for(DetailedCommodity commodity : result){
            System.out.println(commodity.getName());
        }
    }
}