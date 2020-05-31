package CSCI5308.GroupFormationTool.DBMock;

import CSCI5308.GroupFormationTool.Model.User;

public class UserDBMock {

	public boolean createUser(User user) {
		user.setBannerId("B00000000");
		user.setEmailId("rhawkey@dal.ca");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		return true;
	}

	public User getUserByEmailId(User user) {

		if (user.getEmailId().equalsIgnoreCase("padmeshdonthu@gmail.com")) {
			return user;
		} else {
			return null;
		}
	}

	public User getUserByBannerId(User user) {
		user.setBannerId(user.getBannerId());
		user.setEmailId("rhawkey@dal.ca");
		user.setFirstName("Rob");
		user.setLastName("Hawkey");
		return user;
	}

}
