package InterfaceImplementation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {
    @Test
    public void updateUserInfo(){
        Integer result = UserInterface.updateUserInfo(10000003, "update_name", 30, "17321126787",
                "female");
        assertEquals(1, result);
    }

}