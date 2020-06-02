package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserService {

    public boolean createUser(IUser user);
    
    public boolean checkCurrentUserIsAdmin(String emailId);
}
