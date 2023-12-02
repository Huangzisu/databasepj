package InterfaceImplementation;

import Entity.Message;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MessageInterfaceTest {

    @Test
    public void getMessageByUserId() {
        Integer userId = 10000001;
        ArrayList<Message> result = MessageInterface.getMessageByUserId(userId);
        for(Message message : result) {
            System.out.println(message.getmId() + " " + message.getuId() + " " + message.getContent() + " " + message.getTime());
        }
    }
}