package CSCI5308.GroupFormationTool.AccessControl;

public interface IPasswordHistoryService {
	public boolean isHistoryViolated(IUser user, String enteredPassword);
	public void addPasswordHistory(IUser user, String encrypted_password);
	public String getSettingValue(String settingName);
}
