package InterfaceImplementation;

import SqlOperation.SqlConnection;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class PriceInterfaceTest {

    @Test
    public void insertPrice() {
        try {
            Connection connection = SqlConnection.getConnection();
            PriceInterface.insertPrice(connection, 10000001, 500);
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}