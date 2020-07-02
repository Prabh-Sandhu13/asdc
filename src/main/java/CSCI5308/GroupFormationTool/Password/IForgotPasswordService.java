package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.User.IUser;

public interface IForgotPasswordService {

    boolean notifyUser(IUser user);

    boolean updatePassword(IUser user, String token);
}
