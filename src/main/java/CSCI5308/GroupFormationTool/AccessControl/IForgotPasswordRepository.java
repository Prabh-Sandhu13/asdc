package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Model.User;

public interface IForgotPasswordRepository {
    public boolean addToken(User user, String token);
    public String getToken(User user);
    public boolean updateToken(User user, String token);
    public boolean updatePassword(User user);    
    public boolean deleteToken(User user, String token);
    public User getUserId(User user);
}
