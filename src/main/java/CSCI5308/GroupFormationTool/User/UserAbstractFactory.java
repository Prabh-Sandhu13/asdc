package CSCI5308.GroupFormationTool.User;

import java.util.ArrayList;

public class UserAbstractFactory implements IUserAbstractFactory {

    @Override
    public IUser createUserInstance() {
        return new User();
    }
    
    @Override
    public ArrayList<IUser> createUserListInstance() {
        return new ArrayList<IUser>();
    }
}
