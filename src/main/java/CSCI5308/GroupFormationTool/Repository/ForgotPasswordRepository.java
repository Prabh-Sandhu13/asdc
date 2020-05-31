package CSCI5308.GroupFormationTool.Repository;

import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.Model.User;

public class ForgotPasswordRepository implements IForgotPasswordRepository{

	@Override
	public boolean addToken(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getToken(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteToken(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
