package CSCI5308.GroupFormationTool.AccessControl;
import CSCI5308.GroupFormationTool.Model.User;

public interface IForgotPasswordService {

	public boolean sendMail(IUser user);
	public boolean updatePassword(IUser user, String token);
	
}
