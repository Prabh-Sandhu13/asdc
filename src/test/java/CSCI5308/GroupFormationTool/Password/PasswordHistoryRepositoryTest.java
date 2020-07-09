package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.FactoryProducerTest;
import CSCI5308.GroupFormationTool.Security.ISecurityAbstractFactoryTest;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PasswordHistoryRepositoryTest {

    private IPasswordAbstractFactoryTest passwordAbstractFactoryTest = FactoryProducerTest.getFactory().
            createPasswordAbstractFactoryTest();

    private IUserAbstractFactoryTest userAbstractFactoryTest = FactoryProducerTest.getFactory().
            createUserAbstractFactoryTest();

    private PasswordHistoryRepository passwordHistoryRepository;

    private IUser user = userAbstractFactoryTest.createUserInstance();

    @BeforeEach
    void init() {
        passwordHistoryRepository = passwordAbstractFactoryTest.createPasswordHistoryRepositoryMock();
    }

    @Test
    public void getSettingvalueTest() {
        when(passwordHistoryRepository.getSettingValue("Password History")).thenReturn("value");
        when(passwordHistoryRepository.getSettingValue(null)).thenReturn(null);

        assertFalse(passwordHistoryRepository.getSettingValue(null) != null);
        assertTrue(passwordHistoryRepository.getSettingValue("Password History").equals("value"));
    }

    @Test
    public void getNPasswordsTest() {
        ArrayList<String> nPasswords = new ArrayList<String>();
        nPasswords.add("hostory1");
        nPasswords.add("hostory2");
        nPasswords.add("hostory3");

        when(passwordHistoryRepository.getNPasswords(user, "3")).thenReturn(nPasswords);
        when(passwordHistoryRepository.getNPasswords(user, null)).thenReturn(null);
        when(passwordHistoryRepository.getNPasswords(null, "3")).thenReturn(null);

        assertFalse(passwordHistoryRepository.getNPasswords(user, null) != null);
        assertFalse(passwordHistoryRepository.getNPasswords(null, "3") != null);
        assertTrue(passwordHistoryRepository.getNPasswords(user, "3").size() == 3);
    }

    @Test
    public void addPasswordHistoryTest() {
        when(passwordHistoryRepository.addPasswordHistory(user, "")).thenReturn(false);
        when(passwordHistoryRepository.addPasswordHistory(user, null)).thenReturn(false);
        when(passwordHistoryRepository.addPasswordHistory(null, "password")).thenReturn(false);
        when(passwordHistoryRepository.addPasswordHistory(user, "password")).thenReturn(true);

        assertFalse(passwordHistoryRepository.addPasswordHistory(user, ""));
        assertFalse(passwordHistoryRepository.addPasswordHistory(user, null));
        assertFalse(passwordHistoryRepository.addPasswordHistory(null, "password"));
        assertTrue(passwordHistoryRepository.addPasswordHistory(user, "password"));
    }
}
