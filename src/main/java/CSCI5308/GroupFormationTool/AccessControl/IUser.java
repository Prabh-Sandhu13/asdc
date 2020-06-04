package CSCI5308.GroupFormationTool.AccessControl;

public interface IUser {

	public long getId();

	public void setId(long id);

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getBannerId();

	public void setBannerId(String bannerId);

	public String getEmailId();

	public void setEmailId(String emailId);

	public String getPassword();

	public void setPassword(String password);

	public String getConfirmPassword();

	public void setConfirmPassword(String confirmPassword);
}
