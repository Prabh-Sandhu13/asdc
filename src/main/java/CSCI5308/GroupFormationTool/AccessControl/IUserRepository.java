package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserRepository {

    public boolean createUser(IUser user);
    public IUser getUserByEmailId(IUser user);
    public IUser getUserByBannerId(IUser user);  
    public IUser getAdminDetails();
}
