package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.DBMock.PasswordHistoryDBMock;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordHistoryServiceTest {
    PasswordHistoryDBMock passwordHistoryDBMock = new PasswordHistoryDBMock();
    User user = new User();

    @Test
    void isHistoryViolatedTest() {
        user.setId(123);
        user.setBannerId("B00827531");
        user.setEmailId("haard.shah@dal.ca");
        user.setFirstName("haard");
        user.setLastName("shah");
        user.setPassword("pswd12345");

        assertEquals(null, passwordHistoryDBMock.getSettingValue(null));
        assertEquals("value", passwordHistoryDBMock.getSettingValue("Password History"));

        assertEquals(null, passwordHistoryDBMock.getNPasswords(null, "3"));
        assertEquals(null, passwordHistoryDBMock.getNPasswords(user, ""));
        assertEquals(null, passwordHistoryDBMock.getNPasswords(user, null));
        assertEquals(3, passwordHistoryDBMock.getNPasswords(user, "3").size());

    }
}
