package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordHistoryException;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenExpiredException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Repository.ForgotPasswordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ForgotPasswordServiceTest {

    public ForgotPasswordRepository forgotPasswordRepository;
    public ForgotPasswordService forgotPasswordService;
    public MailService mailService;
    public PolicyService policyService;
    public PasswordHistoryService passwordHistoryService;

    @BeforeEach
    public void init() {
        forgotPasswordRepository = mock(ForgotPasswordRepository.class);
        mailService = mock(MailService.class);
        policyService = mock(PolicyService.class);
        passwordHistoryService = mock(PasswordHistoryService.class);
        forgotPasswordService = new ForgotPasswordService();
        Injector.instance().setForgotPasswordRepository(forgotPasswordRepository);
        Injector.instance().setMailService(mailService);
        Injector.instance().setPolicyService(policyService);
        Injector.instance().setPasswordHistoryService(passwordHistoryService);
    }

    @Test
    void notifyUserTest() {
        User user = new User();
        user.setId(123);
        user.setBannerId("B00827531");
        user.setEmailId("haard.shah@dal.ca");
        user.setFirstName("haard");
        user.setLastName("shah");
        user.setPassword("pswd12345");
        String token = "sample token";
        when(forgotPasswordRepository.getUserId(user)).thenReturn(user);
        when(forgotPasswordRepository.getToken(user)).thenReturn(token);
        when(forgotPasswordRepository.updateToken(user, token)).thenReturn(true);
        when(mailService.sendForgotPasswordMail(user, token)).thenReturn(true);
        assertTrue(forgotPasswordService.notifyUser(user));
        token = "";
        when(forgotPasswordRepository.getUserId(user)).thenReturn(user);
        when(forgotPasswordRepository.getToken(user)).thenReturn(token);
        when(forgotPasswordRepository.addToken(user, token)).thenReturn(true);
        when(mailService.sendForgotPasswordMail(user, token)).thenReturn(true);
        assertTrue(forgotPasswordService.notifyUser(user));
        when(forgotPasswordRepository.getUserId(user)).thenReturn(null);
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            forgotPasswordService.notifyUser(user);
        });
        String expectedMsg = "An account with " + user.getEmailId() + " not found";
        String actualMsg = exception.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
    }

    @Test
    void updatePasswordTest() {
        User user = new User();
        user.setId(123);
        user.setBannerId("B00827531");
        user.setEmailId("haard.shah@dal.ca");
        user.setFirstName("haard");
        user.setLastName("shah");
        user.setPassword("pswd12345");
        user.setConfirmPassword("pswd12345");
        String token = "sample token";
        String passwordErrorMessage = "Error";
        String encryptedPassword = "encryptedpswd12345";
        when(policyService.passwordSPolicyCheck(user.getPassword())).thenReturn(null);
        when(forgotPasswordRepository.getEmailByToken(user, token)).thenReturn(user);
        when(passwordHistoryService.isHistoryViolated(user, user.getPassword())).thenReturn(false);
        when(forgotPasswordRepository.updatePassword(user, encryptedPassword)).thenReturn(true);
        doNothing().when(passwordHistoryService).addPasswordHistory(user, encryptedPassword);
        when(forgotPasswordRepository.deleteToken(user, token)).thenReturn(true);
        assertTrue(forgotPasswordService.updatePassword(user, token));
        when(policyService.passwordSPolicyCheck(user.getPassword())).thenReturn(passwordErrorMessage);
        PasswordException passwordException = assertThrows(PasswordException.class, () -> {
            forgotPasswordService.updatePassword(user, token);
        });
        String expectedMsg = "Error";
        String actualMsg = passwordException.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
        when(policyService.passwordSPolicyCheck(user.getPassword())).thenReturn(null);
        when(forgotPasswordRepository.getEmailByToken(user, token)).thenReturn(null);
        TokenExpiredException tokenExpiredException = assertThrows(TokenExpiredException.class, () -> {
            forgotPasswordService.updatePassword(user, token);
        });
        expectedMsg = "The renew password link has expired, please renew it again";
        actualMsg = tokenExpiredException.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
        when(policyService.passwordSPolicyCheck(user.getPassword())).thenReturn(null);
        when(forgotPasswordRepository.getEmailByToken(user, token)).thenReturn(user);
        when(passwordHistoryService.getSettingValue("Password History")).thenReturn("5");
        when(passwordHistoryService.isHistoryViolated(user, user.getPassword())).thenReturn(true);
        PasswordHistoryException passwordHistoryException = assertThrows(PasswordHistoryException.class, () -> {
            forgotPasswordService.updatePassword(user, token);
        });
        expectedMsg = "Your new password cannot be same as previous 5 passwords!";
        actualMsg = passwordHistoryException.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
        user.setConfirmPassword("somethingelse");
        when(policyService.passwordSPolicyCheck(user.getPassword())).thenReturn(null);
        passwordException = assertThrows(PasswordException.class, () -> {
            forgotPasswordService.updatePassword(user, token);
        });
        expectedMsg = "The passwords do not match. Please try again!";
        actualMsg = passwordException.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
    }
}
