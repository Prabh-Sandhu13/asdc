package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordHistoryException;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenExpiredException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Mail.IMailManagerAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Mail.MailManager;
import CSCI5308.GroupFormationTool.TestsInjector;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ForgotPasswordManagerTest {

    public ForgotPasswordRepository forgotPasswordRepository;
    public IForgotPasswordManager forgotPasswordManager;
    public MailManager mailManager;
    public IPolicy policyInstance;
    public PasswordHistoryManager passwordHistoryManager;
    public PolicyRepository policyRepository;

    private IPasswordAbstractFactoryTest passwordAbstractFactoryTest = TestsInjector.instance().
            getPasswordAbstractFactoryTest();

    private IMailManagerAbstractFactoryTest mailManagerAbstractFactoryTest = TestsInjector.instance().
            getMailManagerAbstractFactoryTest();

    private IUserAbstractFactoryTest userAbstractFactoryTest = TestsInjector.instance().getUserAbstractFactoryTest();

    @BeforeEach
    public void init() {
        forgotPasswordRepository = passwordAbstractFactoryTest.createForgotPasswordRepositoryMock();
        mailManager = mailManagerAbstractFactoryTest.createMailManagerMock();
        policyInstance = passwordAbstractFactoryTest.createPolicyInstance();
        passwordHistoryManager = passwordAbstractFactoryTest.createPasswordHistoryManagerMock();
        forgotPasswordManager = passwordAbstractFactoryTest.createForgotPasswordManagerInstance();
        policyRepository = passwordAbstractFactoryTest.createPolicyRepositoryMock();
        Injector.instance().setForgotPasswordRepository(forgotPasswordRepository);
        Injector.instance().setMailManager(mailManager);
        Injector.instance().setPasswordHistoryManager(passwordHistoryManager);
        Injector.instance().setPolicyRepository(policyRepository);
    }

    @Test
    void notifyUserTest() {
        IUser user = userAbstractFactoryTest.createUserInstance();
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
        when(mailManager.sendForgotPasswordMail(user, token)).thenReturn(true);
        assertTrue(forgotPasswordManager.notifyUser(user));
        token = "";
        when(forgotPasswordRepository.getUserId(user)).thenReturn(user);
        when(forgotPasswordRepository.getToken(user)).thenReturn(token);
        when(forgotPasswordRepository.addToken(user, token)).thenReturn(true);
        when(mailManager.sendForgotPasswordMail(user, token)).thenReturn(true);
        assertTrue(forgotPasswordManager.notifyUser(user));
        when(forgotPasswordRepository.getUserId(user)).thenReturn(null);
        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            forgotPasswordManager.notifyUser(user);
        });
        String expectedMsg = "An account with " + user.getEmailId() + " not found";
        String actualMsg = exception.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
    }

    @Test
    void updatePasswordTest() {
        IUser user = userAbstractFactoryTest.createUserInstance();
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
        ArrayList<IPolicy> policyList = passwordAbstractFactoryTest.createPolicyListInstance();
        IPolicy policy = passwordAbstractFactoryTest.createPolicyInstance();
        policy.setEnabled(1);
        policy.setId(0);
        policy.setValue("10");
        policyList.add(policy);
        when(policyRepository.passwordSPolicyCheck(user.getPassword())).
                thenReturn(passwordAbstractFactoryTest.createPolicyListInstance());
        when(forgotPasswordRepository.getEmailByToken(user, token)).thenReturn(user);
        when(passwordHistoryManager.isHistoryViolated(user, user.getPassword())).thenReturn(false);
        when(forgotPasswordRepository.updatePassword(user, encryptedPassword)).thenReturn(true);
        doNothing().when(passwordHistoryManager).addPasswordHistory(user, encryptedPassword);
        when(forgotPasswordRepository.deleteToken(user, token)).thenReturn(true);
        assertTrue(forgotPasswordManager.updatePassword(user, token));
        user.setPassword("pa");
        user.setConfirmPassword(user.getPassword());
        when(policyRepository.passwordSPolicyCheck(user.getPassword())).thenReturn(policyList);
        PasswordException passwordException = assertThrows(PasswordException.class, () -> {
            forgotPasswordManager.updatePassword(user, token);
        });
        String expectedMsg = DomainConstants.passwordMinimumLength + policy.getValue();
        String actualMsg = passwordException.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
        when(policyRepository.passwordSPolicyCheck(user.getPassword())).
                thenReturn(passwordAbstractFactoryTest.createPolicyListInstance());
        when(forgotPasswordRepository.getEmailByToken(user, token)).thenReturn(null);
        TokenExpiredException tokenExpiredException = assertThrows(TokenExpiredException.class, () -> {
            forgotPasswordManager.updatePassword(user, token);
        });
        expectedMsg = "The renew password link has expired, please renew it again";
        actualMsg = tokenExpiredException.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
        when(policyRepository.passwordSPolicyCheck(user.getPassword())).
                thenReturn(passwordAbstractFactoryTest.createPolicyListInstance());
        when(forgotPasswordRepository.getEmailByToken(user, token)).thenReturn(user);
        when(passwordHistoryManager.getSettingValue("Password History")).thenReturn("5");
        when(passwordHistoryManager.isHistoryViolated(user, user.getPassword())).thenReturn(true);
        PasswordHistoryException passwordHistoryException = assertThrows(PasswordHistoryException.class, () -> {
            forgotPasswordManager.updatePassword(user, token);
        });
        expectedMsg = "Your new password cannot be same as previous 5 passwords!";
        actualMsg = passwordHistoryException.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
        user.setConfirmPassword("somethingelse");
        when(policyRepository.passwordSPolicyCheck(user.getPassword())).
                thenReturn(passwordAbstractFactoryTest.createPolicyListInstance());
        passwordException = assertThrows(PasswordException.class, () -> {
            forgotPasswordManager.updatePassword(user, token);
        });
        expectedMsg = "The passwords do not match. Please try again!";
        actualMsg = passwordException.getMessage();
        assertTrue(expectedMsg.equals(actualMsg));
    }
}
