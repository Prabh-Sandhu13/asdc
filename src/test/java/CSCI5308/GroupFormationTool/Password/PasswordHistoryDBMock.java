package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.TestsInjector;
import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;

public class PasswordHistoryDBMock implements IPasswordHistoryRepository {

    ITestPasswordAbstractFactory passwordAbstractFactoryTest = TestsInjector.instance().
            getPasswordAbstractFactoryTest();

    @Override
    public String getSettingValue(String settingName) {
        if (null == settingName) {
            return null;
        } else {
            return "value";
        }

    }

    @Override
    public ArrayList<String> getNPasswords(IUser user, String num) {
        if (null == user || null == num || num.equals("")) {
            return null;
        } else {
            ArrayList<String> nPasswords = passwordAbstractFactoryTest.createListInstance();
            nPasswords.add("hostory1");
            nPasswords.add("hostory2");
            nPasswords.add("hostory3");
            return nPasswords;
        }
    }

    @Override
    public boolean addPasswordHistory(IUser user, String password) {
        return null != user && null != password && !password.equals("");
    }
}
