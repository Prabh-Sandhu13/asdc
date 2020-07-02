package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.User;
import CSCI5308.GroupFormationTool.Password.PasswordHistoryRepository;
import CSCI5308.GroupFormationTool.Password.PasswordHistoryService;
import CSCI5308.GroupFormationTool.Security.BCryptEncryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PasswordHistoryServiceTest {

    public PasswordHistoryService passwordHistoryService;
    public PasswordHistoryRepository passwordHistoryRepository;
    public BCryptEncryption bCryptEncryption;

    @BeforeEach
    public void init() {
        passwordHistoryService = new PasswordHistoryService();
        passwordHistoryRepository = mock(PasswordHistoryRepository.class);
        bCryptEncryption = mock(BCryptEncryption.class);
        Injector.instance().setPasswordHistoryRepository(passwordHistoryRepository);
        Injector.instance().setPasswordEncryptor(bCryptEncryption);
    }

    @Test
    void isHistoryViolatedTest() {
        User user = new User();
        user.setId(123);
        user.setBannerId("B00827531");
        user.setEmailId("haard.shah@dal.ca");
        user.setFirstName("haard");
        user.setLastName("shah");
        user.setPassword("pswd12345");
        String num = "5";
        String encryptedPassword = "encrypted12345";
        ArrayList<String> passwords = new ArrayList<>();
        passwords.add("Password");
        passwords.add("qwerty");
        when(bCryptEncryption.encoder(user.getPassword())).thenReturn(encryptedPassword);
        when(passwordHistoryRepository.getSettingValue("Password History")).thenReturn(null);
        assertFalse(passwordHistoryService.isHistoryViolated(user, user.getPassword()));
        when(passwordHistoryRepository.getSettingValue("Password History")).thenReturn(num);
        when(passwordHistoryRepository.getNPasswords(user, num)).thenReturn(passwords);
        assertFalse(passwordHistoryService.isHistoryViolated(user, user.getPassword()));
        passwords.add("encrypted12345");
        user.setPassword("encrypted12345");
        when(passwordHistoryRepository.getSettingValue("Password History")).thenReturn(num);
        when(passwordHistoryRepository.getNPasswords(user, num)).thenReturn(passwords);
        when(bCryptEncryption.passwordMatch(encryptedPassword, passwords.get(2))).thenReturn(true);
        assertTrue(passwordHistoryService.isHistoryViolated(user, user.getPassword()));
    }

    @Test
    void addPasswordHistoryTest() {
        User user = new User();
        user.setId(123);
        user.setBannerId("B00827531");
        user.setEmailId("haard.shah@dal.ca");
        user.setFirstName("haard");
        user.setLastName("shah");
        user.setPassword("pswd12345");
        String encryptedPassword = "encrypted12345";
        when(passwordHistoryRepository.addPasswordHistory(user, encryptedPassword)).thenReturn(true);
        passwordHistoryService.addPasswordHistory(user, encryptedPassword);
    }

    @Test
    void getSettingValueTest() {
        String settingName = "Password History";
        when(passwordHistoryRepository.getSettingValue(settingName)).thenReturn("6");
        assertFalse(passwordHistoryService.getSettingValue(settingName).isEmpty());
        assertTrue(passwordHistoryService.getSettingValue(settingName).equals("6"));
    }
}
