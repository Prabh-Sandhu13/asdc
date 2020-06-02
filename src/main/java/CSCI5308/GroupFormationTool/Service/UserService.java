package CSCI5308.GroupFormationTool.Service;

import org.springframework.security.core.context.SecurityContextHolder;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;

public class UserService implements IUserService {

	private IUserRepository userRepository;

	private IPasswordEncryptor encryptor;

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

}
