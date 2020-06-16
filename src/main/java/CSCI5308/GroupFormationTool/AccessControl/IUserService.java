package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserService {

    boolean createUser(IUser user);

    boolean checkCurrentUserIsAdmin(String emailId);

}
