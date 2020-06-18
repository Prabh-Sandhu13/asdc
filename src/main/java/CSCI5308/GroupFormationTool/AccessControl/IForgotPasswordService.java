package CSCI5308.GroupFormationTool.AccessControl;

public interface IForgotPasswordService {

    boolean notifyUser(IUser user);

    boolean updatePassword(IUser user, String token);
}
