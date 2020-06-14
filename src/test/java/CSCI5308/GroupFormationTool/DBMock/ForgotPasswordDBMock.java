package CSCI5308.GroupFormationTool.DBMock;

import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUser;

public class ForgotPasswordDBMock implements IForgotPasswordRepository {
	
	@Override
	public boolean addToken(IUser user, String token) {
		if (user == null || token.equals("") || token.equals(null)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getToken(IUser user) {
		if (user == null) {
			return null;
		} else {
			return "token";
		}
	}

	@Override
	public boolean updateToken(IUser user, String token) {
		if (user == null || token.equals("") || token.equals(null)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean updatePassword(IUser user, String password) {
		if (user == null || password.equals("") || password.equals(null)) {
			user.setPassword(password);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean deleteToken(IUser user, String token) {

		if (user == null || token.equals("") || token.equals(null)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public IUser getUserId(IUser user) {
		if (user != null && user.getId() == 123) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public IUser getEmailByToken(IUser user, String token) {

		if (user != null && user.getEmailId().equalsIgnoreCase("haard.shah@dal.ca")) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public String getSettingValue(String settingName) {
		// TODO Auto-generated method stub
		return null;
	}

}
