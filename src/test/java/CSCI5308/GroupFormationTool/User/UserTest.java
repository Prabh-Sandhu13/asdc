package CSCI5308.GroupFormationTool.User;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Password.PasswordHistoryManager;
import CSCI5308.GroupFormationTool.Password.Policy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
public class UserTest {
	
    User userInstance;
    UserRepository userRepository;
    Policy policyInstance;
    PasswordHistoryManager passwordHistoryManager;

    @BeforeEach
    public void init() {
    	userInstance = new User();
        userRepository = mock(UserRepository.class);
        Injector.instance().setUserRepository(userRepository);
        policyInstance = mock(Policy.class);
        Injector.instance().setPolicy(policyInstance);
        passwordHistoryManager = mock(PasswordHistoryManager.class);
        Injector.instance().setPasswordHistoryManager(passwordHistoryManager);
    }

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
    
    @Test
    void createUserTest() {
        IUser user = new User();
        user.setBannerId("B00854462");
        user.setEmailId("padmeshdonthu@gmail.com");
        user.setFirstName("Padmesh");
        user.setLastName("Donthu");
        user.setPassword("Padmesh1$");
        user.setConfirmPassword("Padmesh1$");
        String encryptedPassword = "encryptedPadmesh1$";
        when(policyInstance.passwordSPolicyCheck(user.getPassword())).thenReturn(null);
        when(userRepository.getUserByEmailId(user)).thenReturn(null);
        when(userRepository.createUser(user)).thenReturn(true);
        when(userRepository.getUserIdByEmailId(user)).thenReturn(user);
        doNothing().when(passwordHistoryManager).addPasswordHistory(user, encryptedPassword);
        assertTrue(userInstance.createUser(user));
        user.setPassword("pa");
        user.setConfirmPassword("pa");
        String passwordErrorMessage = "Minimum number of characters is 3";
        when(policyInstance.passwordSPolicyCheck(user.getPassword())).thenReturn(passwordErrorMessage);
        PasswordException passwordException = assertThrows(PasswordException.class, () -> {
        	userInstance.createUser(user);
        });
        assertTrue(passwordException.getMessage().equals(passwordErrorMessage));
        user.setPassword("Padmesh1$");
        user.setConfirmPassword("Padmesh1");
        passwordErrorMessage = "The passwords do not match. Please try again!";
        passwordException = assertThrows(PasswordException.class, () -> {
        	userInstance.createUser(user);
        });
        assertTrue(passwordException.getMessage().equals(passwordErrorMessage));
        user.setConfirmPassword("Padmesh1$");
        when(policyInstance.passwordSPolicyCheck(user.getPassword())).thenReturn(null);
        when(userRepository.getUserByEmailId(user)).thenReturn(user);
        UserAlreadyExistsException userAlreadyExistsException = assertThrows(UserAlreadyExistsException.class, () -> {
        	userInstance.createUser(user);
        });
        String userAlreadyExistsErrorMessage = "An account with " + user.getEmailId() + " already exists.";
        assertTrue(userAlreadyExistsException.getMessage().equals(userAlreadyExistsErrorMessage));
        user.setEmailId("");
        assertFalse(userInstance.createUser(user));
    }

    @Test
    void checkCurrentUserIsAdminTest() {
        String emailId = "padmeshd@gmail.com";
        IUser admin = new User();
        admin.setBannerId("B00854462");
        admin.setEmailId("padmeshdonthu@gmail.com");
        admin.setFirstName("Padmesh");
        admin.setLastName("Donthu");
        when(userRepository.getAdminDetails()).thenReturn(admin);
        assertFalse(userInstance.checkCurrentUserIsAdmin(emailId));
        emailId = "padmeshdonthu@gmail.com";
        when(userRepository.getAdminDetails()).thenReturn(admin);
        assertTrue(userInstance.checkCurrentUserIsAdmin(emailId));
    }

}
