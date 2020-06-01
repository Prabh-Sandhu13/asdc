package CSCI5308.GroupFormationTool.Model;

import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;

public class User implements IUser {

	private long id;

	private String firstName;

	private String lastName;

	private String bannerId;

	private String emailId;

	private String password;

	private String confirmPassword;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getBannerId() {
		return bannerId;
	}

	@Override
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	@Override
	public String getEmailId() {
		return emailId;
	}

	@Override
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getConfirmPassword() {
		return confirmPassword;
	}

	@Override
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public User() {
		id = -1;

		firstName = null;

		lastName = null;

		bannerId = null;

		emailId = null;

		password = null;

		confirmPassword = null;
	}


}
