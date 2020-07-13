package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordHistoryManager implements IPasswordHistoryManager {

    private IPasswordHistoryRepository passwordHistoryRepository;
    private IPasswordEncryptor encryptor;
    private static final Logger Log = LoggerFactory.getLogger(PasswordHistoryManager.class.getName());

    @Override
    public boolean isHistoryViolated(IUser user, String enteredPassword) {

        passwordHistoryRepository = Injector.instance().getPasswordHistoryRepository();
        encryptor = Injector.instance().getPasswordEncryptor();
        boolean violation = false;
        encryptor = Injector.instance().getPasswordEncryptor();
        String settingValue = passwordHistoryRepository.getSettingValue(DomainConstants.passwordHistory);
        if (settingValue == null) {
        	Log.warn("Could not fetch history setting value");
            return false;
        } else {
            ArrayList<String> nPasswords = passwordHistoryRepository.getNPasswords(user, settingValue);
            for (int listIndex = 0; listIndex < nPasswords.size(); listIndex++) {
                if (encryptor.passwordMatch(enteredPassword, nPasswords.get(listIndex))) {
                    violation = true;
                    break;
                }
            }
        }
        return violation;
    }

    @Override
    public void addPasswordHistory(IUser user, String encrypted_password) {
        passwordHistoryRepository = Injector.instance().getPasswordHistoryRepository();
        passwordHistoryRepository.addPasswordHistory(user, encrypted_password);
    }

    @Override
    public String getSettingValue(String settingName) {
        passwordHistoryRepository = Injector.instance().getPasswordHistoryRepository();
        return passwordHistoryRepository.getSettingValue(settingName);
    }
}
