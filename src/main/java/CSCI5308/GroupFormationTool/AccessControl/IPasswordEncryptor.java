package CSCI5308.GroupFormationTool.AccessControl;

public interface IPasswordEncryptor {

	public String encoder(String password);

	public boolean passwordMatch(String password, String encryptedPassword);

}
