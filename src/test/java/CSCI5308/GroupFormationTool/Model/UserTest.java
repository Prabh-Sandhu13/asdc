package CSCI5308.GroupFormationTool.Model;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.DBMock.UserDBMock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTest {

    private IUser createDefaultUser() {
        UserDBMock userDbMock = new UserDBMock();
        IUser user = loadUser(userDbMock);
        return user;
    }

    private IUser loadUser(UserDBMock userDBMock) {
        IUser user = new User();
        user = userDBMock.loadUserWithID(user);
        return user;
    }

    @Test
    public void getIdTest() {
        IUser user = createDefaultUser();
        assertEquals(1, user.getId());
    }

    @Test
    public void setIdTest() {
        IUser user = new User();
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    public void getFirstNameTest() {
        IUser user = createDefaultUser();
        assertEquals("Test", user.getFirstName());
    }

    @Test
    public void setFirstNameTest() {
        IUser user = new User();
        user.setFirstName("Padmesh");
        assertEquals("Padmesh", user.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        IUser user = createDefaultUser();
        assertEquals("User", user.getLastName());
    }

    @Test
    public void setLastNameTest() {
        IUser user = new User();
        user.setLastName("Donthu");
        assertEquals("Donthu", user.getLastName());
    }

    @Test
    public void getBannerIdTest() {
        IUser user = createDefaultUser();
        assertEquals("B00854462", user.getBannerId());
    }

    @Test
    public void setBannerIdTest() {
        IUser user = new User();
        user.setBannerId("B0000000");
        assertEquals("B0000000", user.getBannerId());
    }

    @Test
    public void getEmailIdTest() {
        IUser user = createDefaultUser();
        assertEquals("padmeshdonthu@gmail.com", user.getEmailId());
    }

    @Test
    public void setEmailIdTest() {
        IUser user = new User();
        user.setBannerId("padmeshdonthu@gmail.com");
        assertEquals("padmeshdonthu@gmail.com", user.getBannerId());
    }

    @Test
    public void getPasswordTest() {
        IUser user = createDefaultUser();
        assertEquals("password", user.getPassword());
    }

    @Test
    public void setPasswordTest() {
        IUser user = new User();
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    @Test
    public void getConfirmPasswordTest() {
        IUser user = createDefaultUser();
        assertEquals("password", user.getConfirmPassword());
    }

    @Test
    public void setConfirmPasswordTest() {
        IUser user = new User();
        user.setConfirmPassword("password");
        assertEquals("password", user.getConfirmPassword());
    }

}
