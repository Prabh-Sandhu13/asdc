package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IUserRepository {

    public boolean createUser(IUser user);
    public IUser getUserByEmailId(IUser user);
    public IUser getUserByBannerId(IUser user);  
    public IUser getAdminDetails();
	public ArrayList<IPolicy> passwordSPolicyCheck(String password);
}
