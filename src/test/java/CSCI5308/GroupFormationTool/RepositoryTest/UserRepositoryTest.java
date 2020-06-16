package CSCI5308.GroupFormationTool.RepositoryTest;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.DBMock.UserDBMock;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class UserRepositoryTest {

    private UserDBMock userDBMock;

    @Test
    public void createUserTest() {

        userDBMock = new UserDBMock();

        IUser user = new User();

        user = userDBMock.loadUserWithID(user);

        assertEquals(true, userDBMock.createUser(user));

    }

    @Test
    public void getUserByEmailIdTest() {

        userDBMock = new UserDBMock();

        IUser user = new User();

        user = userDBMock.loadUserWithID(user);

        assertEquals("B00854462", userDBMock.getUserByEmailId(user).getBannerId());

        assertNotEquals("Sample", userDBMock.getUserByEmailId(user).getFirstName());

    }

    @Test
    public void getUserByBannerIdTest() {

        userDBMock = new UserDBMock();

        IUser user = new User();

        user = userDBMock.loadUserWithID(user);

        assertEquals("padmeshdonthu@gmail.com", userDBMock.getUserByBannerId(user).getEmailId());

        assertNotEquals("Sample", userDBMock.getUserByEmailId(user).getLastName());

    }

    @Test
    public void getAdminDetailsTest() {
        userDBMock = new UserDBMock();

        IUser user = new User();

        user = userDBMock.loadUserWithID(user);

        assertEquals("admin@gmail.com", userDBMock.getAdminDetails().getEmailId());

        assertNotEquals("Padmesh", userDBMock.getAdminDetails().getFirstName());

    }

}
