package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordHistoryRepository;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordHistoryService;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;

import java.util.ArrayList;

public class PasswordHistoryService implements IPasswordHistoryService {

    private IPasswordHistoryRepository passwordHistoryRepository;
    private IPasswordEncryptor encryptor;

    @Override
    public boolean isHistoryViolated(IUser user, String enteredPassword) {

        passwordHistoryRepository = Injector.instance().getPasswordHistoryRepository();
        encryptor = Injector.instance().getPasswordEncryptor();
        boolean violation = false;

        encryptor = Injector.instance().getPasswordEncryptor();
        String settingValue = passwordHistoryRepository.getSettingValue(DomainConstants.passwordHistory);

        if (settingValue == null) {
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
