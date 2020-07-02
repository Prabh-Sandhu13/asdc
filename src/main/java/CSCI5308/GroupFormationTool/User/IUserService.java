package CSCI5308.GroupFormationTool.User;

public interface IUserService {

    boolean createUser(IUser user);

    boolean checkCurrentUserIsAdmin(String emailId);

}
