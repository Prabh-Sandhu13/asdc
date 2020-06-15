package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;

public class UserService implements IUserService {

	private IUserRepository userRepository;

	private IPasswordEncryptor encryptor;
	private String err = null;
	private int upper = 0;
	private int lower = 0;
	private int number = 0;
	private int special = 0;

	@Override
	public boolean createUser(IUser user) {

		if (!checkForValues(user)) {
			return false;
		}

		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			throw new PasswordException("The passwords do not match");
		}

		userRepository = Injector.instance().getUserRepository();

		boolean success = false;
		encryptor = Injector.instance().getPasswordEncryptor();

		user.setPassword(encryptor.encoder(user.getPassword()));

		IUser userByEmailId = userRepository.getUserByEmailId(user);

		if (userByEmailId == null) {
			success = userRepository.createUser(user);
		} else {
			throw new UserAlreadyExistsException("An account with " + user.getEmailId() + " already exists!!");
		}

		return success;
	}

	@Override
	public boolean checkCurrentUserIsAdmin(String emailId) {

		userRepository = Injector.instance().getUserRepository();
		IUser adminDetails = userRepository.getAdminDetails();

		return adminDetails.getEmailId().equalsIgnoreCase(emailId);

	}

	private boolean checkForValues(IUser user) {

		if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmailId().isEmpty()
				|| user.getPassword().isEmpty() || user.getConfirmPassword().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	private String checkPasswordSecurity(String password, ArrayList<IPolicy> policies) {
		err = null;
		upper = 0;
		lower = 0;
		number = 0;
		special = 0;
		for (int i = 0; i < password.length(); i++) {
			char ch = password.charAt(i);
			if (ch >= 'A' && ch <= 'Z') {
				upper++;
			} else if (ch >= 'a' && ch <= 'z') {
				lower++;
			} else if (ch >= '0' && ch <= '9') {
				number++;
			} else {
				special++;
			}
		}
		policies.forEach(policy -> {
			if (err != null) {
				return;
			}
			int id = policy.getId();
			String val = policy.getValue();
			int enabled = policy.getEnabled();
			if (enabled == 1) {
				switch (id) {
				// Minimum Length policy
				case 0:
					if (password.length() < Integer.parseInt(val)) {
						err = "Minimum length of password should be " + val;
					}
					break;
				// Maximum Length policy
				case 1:
					if (password.length() > Integer.parseInt(val)) {
						err = "Maximum length of password should be " + val;
					}
					break;
				// Minimum number of uppercase characters
				case 2:
					if (upper < Integer.parseInt(val)) {
						err = "Minimum number of uppercase characters in password should be " + val;
					}
					break;
				// Minimum number of lowercase characters
				case 3:
					if (lower < Integer.parseInt(val)) {
						err = "Minimum number of lowercase characters in password should be " + val;
					}
					break;
				// Minimum number of symbols or special characters
				case 4:
					if (special < Integer.parseInt(val)) {
						err = "Minimum number of symbols or special characters in password should be " + val;
					}
					break;
				// A set of characters that are not allowed
				case 5:
					if (password != null && password.contains(val)) {
						err = "" + val + " not allowed in password ";
					}
					break;
				default:
				}
			}
		});
		return err;
	}

	@Override
	public String passwordSPolicyCheck(String password) {
		userRepository = Injector.instance().getUserRepository();
		ArrayList<IPolicy> policies = userRepository.passwordSPolicyCheck(password);
		return checkPasswordSecurity(password, policies);
	}

}
