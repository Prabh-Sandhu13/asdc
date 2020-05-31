package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Model.User;

public class UserService implements IUserService {

	private IUserRepository userRepository;

	private IPasswordEncryptor encryptor;

	@Override
	public boolean createUser(User user) {

		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			throw new PasswordException("The passwords do not match");
		}

		userRepository = Injector.instance().getUserRepository();

		boolean success = false;
		encryptor = Injector.instance().getPasswordEncryptor();

		user.setPassword(encryptor.encoder(user.getPassword()));

		User userByEmailId = userRepository.getUserByEmailId(user);

		if (userByEmailId == null) {
			success = userRepository.createUser(user);
		} else {
			throw new UserAlreadyExistsException("An account with " + user.getEmailId() + " already exists!!");
		}

		return success;
	}
}
