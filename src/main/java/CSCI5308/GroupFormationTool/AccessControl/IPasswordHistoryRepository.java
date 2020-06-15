package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IPasswordHistoryRepository {
    public String getSettingValue(String settingName);
    public boolean addPasswordHistory(IUser user, String password);
    public ArrayList<String> getNPasswords(IUser user, String num);
}
