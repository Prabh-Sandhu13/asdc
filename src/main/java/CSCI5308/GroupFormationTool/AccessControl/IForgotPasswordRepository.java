package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Model.User;

public interface IForgotPasswordRepository {
    public boolean addToken(IUser user, String token);
    public String getToken(IUser user);
    public boolean updateToken(IUser user, String token);
    public boolean updatePassword(IUser user, String password);    
    public boolean deleteToken(IUser user, String token);
    public IUser getUserId(IUser user);
    public IUser getEmailByToken(IUser user,String token);
}
