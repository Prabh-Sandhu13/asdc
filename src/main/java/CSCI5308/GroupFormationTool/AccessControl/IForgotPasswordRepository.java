package CSCI5308.GroupFormationTool.AccessControl;


public interface IForgotPasswordRepository {

    boolean addToken(IUser user, String token);

    String getToken(IUser user);

    boolean updateToken(IUser user, String token);

    boolean updatePassword(IUser user, String password);

    boolean deleteToken(IUser user, String token);

    IUser getUserId(IUser user);

    IUser getEmailByToken(IUser user, String token);

}
