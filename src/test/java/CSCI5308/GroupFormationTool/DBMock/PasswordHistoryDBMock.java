package CSCI5308.GroupFormationTool.DBMock;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.IPasswordHistoryRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUser;

public class PasswordHistoryDBMock implements IPasswordHistoryRepository {
	@Override
	public String getSettingValue(String settingName) {
		if (null == settingName) {
		return null;
		}
		else{
			return "value";
		}
		
	}

	@Override
	public ArrayList<String> getNPasswords(IUser user, String num) {
		if(null == user || null == num || num.equals("")) {
			return null;
		}
		else {
			ArrayList<String> nPasswords = new ArrayList<String>();
			nPasswords.add("hostory1");
			nPasswords.add("hostory2");
			nPasswords.add("hostory3");
			return nPasswords;
		}
	}

	@Override
	public boolean addPasswordHistory(IUser user, String password) {
		if(null == user || null == password || password.equals("")) {
			return false;
		}
		else {
			return true;
		}
	}
}
