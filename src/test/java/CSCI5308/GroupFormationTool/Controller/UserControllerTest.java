package CSCI5308.GroupFormationTool.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    private UserController userController;

    @Test
    void createUser() {
    	userController = new UserController();
        assertEquals(false, userController.createUser());
    }
}