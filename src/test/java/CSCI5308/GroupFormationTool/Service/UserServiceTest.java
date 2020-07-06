package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.Policy;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    User userInstance;
    UserRepository userRepository;
    Policy policyService;
    PasswordHistoryService passwordHistoryService;

    @BeforeEach
    public void init() {
    	userInstance = new User();
        userRepository = mock(UserRepository.class);
        Injector.instance().setUserRepository(userRepository);
        policyService = mock(Policy.class);
        Injector.instance().setPolicyService(policyService);
        passwordHistoryService = mock(PasswordHistoryService.class);
        Injector.instance().setPasswordHistoryService(passwordHistoryService);
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

        when(policyService.passwordSPolicyCheck(user.getPassword())).thenReturn(null);
        when(userRepository.getUserByEmailId(user)).thenReturn(null);
        when(userRepository.createUser(user)).thenReturn(true);
        when(userRepository.getUserIdByEmailId(user)).thenReturn(user);
        doNothing().when(passwordHistoryService).addPasswordHistory(user, encryptedPassword);
        assertTrue(userInstance.createUser(user));

        user.setPassword("pa");
        user.setConfirmPassword("pa");
        String passwordErrorMessage = "Minimum number of characters is 3";
        when(policyService.passwordSPolicyCheck(user.getPassword())).thenReturn(passwordErrorMessage);
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
        when(policyService.passwordSPolicyCheck(user.getPassword())).thenReturn(null);
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