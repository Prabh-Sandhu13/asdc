package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.DBMock.ForgotPasswordDBMock;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ForgotPasswordServiceTest {


    ForgotPasswordDBMock fpDBMock = new ForgotPasswordDBMock();
    User user = new User();

    @Test
    void sendMailTest() {

        user.setId(123);
        user.setBannerId("B00827531");
        user.setEmailId("haard.shah@dal.ca");
        user.setFirstName("haard");
        user.setLastName("shah");
        user.setPassword("pswd12345");

        assertEquals(user, fpDBMock.getUserId(user));
        assertEquals(user, fpDBMock.getEmailByToken(user, "token"));
        assertEquals(true, fpDBMock.addToken(user, "token"));

    }

    @Test
    void updatePasswordtest() {
        user.setId(123);
        user.setBannerId("B00827531");
        user.setEmailId("haard.shah@dal.ca");
        user.setFirstName("haard");
        user.setLastName("shah");
        user.setPassword("pswd12345");

        assertEquals(user, fpDBMock.getEmailByToken(user, "token"));
        assertEquals(true, fpDBMock.updatePassword(user, "password"));
        assertEquals(true, fpDBMock.deleteToken(user, "token"));

    }

}
