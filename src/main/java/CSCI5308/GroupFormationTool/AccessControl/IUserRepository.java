package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Model.User;

public interface IUserRepository {

    public boolean createUser(User user);
    public User getUserByEmailId(User user);
    public User getUserByBannerId(User user);    
}
