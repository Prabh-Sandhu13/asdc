package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Model.User;

public interface IForgotPasswordRepository {
    public boolean addToken(User user);
    public String getToken(User user);
    public boolean updatePassword(User user);    
    public boolean deleteToken(User user);
}
