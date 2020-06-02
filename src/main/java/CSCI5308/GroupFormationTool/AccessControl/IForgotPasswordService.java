package CSCI5308.GroupFormationTool.AccessControl;
import CSCI5308.GroupFormationTool.Model.User;

public interface IForgotPasswordService {

	public boolean sendMail(User user);
	public boolean updatePassword(User user, String token);
	
}
