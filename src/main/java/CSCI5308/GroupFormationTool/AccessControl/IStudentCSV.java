package CSCI5308.GroupFormationTool.AccessControl;

public interface IStudentCSV {

	public String getFirstName();
	
	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getEmail();

	public void setEmail(String email);

	public String getBannerId();

	public void setBannerId(String bannerId);
	
	public String getPassword();

	public void setPassword(String password);
}
