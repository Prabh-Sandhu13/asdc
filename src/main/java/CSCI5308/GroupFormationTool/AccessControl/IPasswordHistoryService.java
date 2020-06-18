package CSCI5308.GroupFormationTool.AccessControl;

public interface IPasswordHistoryService {

	boolean isHistoryViolated(IUser user, String enteredPassword);

    void addPasswordHistory(IUser user, String encrypted_password);

    String getSettingValue(String settingName);
}
