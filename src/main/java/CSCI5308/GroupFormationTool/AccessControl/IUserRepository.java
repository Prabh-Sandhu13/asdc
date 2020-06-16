package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserRepository {

    boolean createUser(IUser user);

    IUser getUserByEmailId(IUser user);

    IUser getUserByBannerId(IUser user);

    IUser getAdminDetails();

    IUser getUserIdByEmailId(IUser user);
}
