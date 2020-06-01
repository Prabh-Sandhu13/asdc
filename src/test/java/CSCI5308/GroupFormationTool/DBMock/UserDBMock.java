package CSCI5308.GroupFormationTool.DBMock;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.Model.User;

public class UserDBMock implements IUserRepository {

	private long id;

	private String firstName;

	private String lastName;

	private String bannerId;

	private String emailId;

	private String password;

	private String confirmPassword;

	public UserDBMock() {
		setToDefaults();
	}

	public void setToDefaults() {
		id = 1;

		firstName = "Test";

		lastName = "User";

		bannerId = "B00854462";

		emailId = "padmeshdonthu@gmail.com";

		password = "password";

		confirmPassword = "password";
	}

	@Override
	public boolean createUser(IUser user) {

		user.setBannerId(bannerId);
		user.setConfirmPassword(confirmPassword);
		user.setEmailId(emailId);
		user.setFirstName(firstName);
		user.setId(id);
		user.setLastName(lastName);
		user.setPassword(password);
		return true;
	}

	@Override
	public IUser getUserByEmailId(IUser user) {

		user.setBannerId(bannerId);
		user.setConfirmPassword(confirmPassword);
		user.setEmailId(user.getEmailId());
		user.setFirstName(firstName);
		user.setId(id);
		user.setLastName(lastName);
		user.setPassword(password);
		return user;
	}

	@Override
	public IUser getUserByBannerId(IUser user) {
		user.setBannerId(user.getBannerId());
		user.setConfirmPassword(confirmPassword);
		user.setEmailId(user.getEmailId());
		user.setFirstName(firstName);
		user.setId(id);
		user.setLastName(lastName);
		user.setPassword(password);
		return user;
	}

	@Override
	public IUser getAdminDetails() {

		IUser user = new User();
		user.setBannerId("B0000000");
		user.setConfirmPassword("administrator");
		user.setEmailId("admin@gmail.com");
		user.setFirstName("AdminFname");
		user.setId(0);
		user.setLastName("AdminLname");
		user.setPassword("administrator");

		return user;
	}
	
	public IUser loadUserWithID(IUser user)
	{
		user.setBannerId(bannerId);
		user.setConfirmPassword(confirmPassword);
		user.setEmailId(emailId);
		user.setFirstName(firstName);
		user.setId(id);
		user.setLastName(lastName);
		user.setPassword(password);
		
		return user;
	}

}
