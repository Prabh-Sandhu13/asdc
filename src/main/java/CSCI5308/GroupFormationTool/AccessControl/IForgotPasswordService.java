package CSCI5308.GroupFormationTool.AccessControl;

public interface IForgotPasswordService {

	public boolean notifyUser(IUser user);
	public boolean updatePassword(IUser user, String token);
	//public boolean isHistoryViolated(IUser user, String enteredPassword);
}
