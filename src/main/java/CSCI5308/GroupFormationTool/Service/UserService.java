package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Injector;

public class UserService implements IUserService {

    private IUserRepository userRepository;

    private IPasswordEncryptor encryptor;

    private IPolicyService policyService;

    private IPasswordHistoryService passwordHistoryService;

    @Override
    public boolean createUser(IUser user) {
        if (!checkForValues(user)) {
            return false;
        }

        policyService = Injector.instance().getPolicyService();

        String password = user.getPassword();
        String passwordSecurityError = policyService.passwordSPolicyCheck(password);

        if (passwordSecurityError != null) {
            throw new PasswordException(passwordSecurityError);
        }

        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            throw new PasswordException("The passwords do not match. Please try again!");
        }

        userRepository = Injector.instance().getUserRepository();
        passwordHistoryService = Injector.instance().getPasswordHistoryService();
        boolean success = false;
        encryptor = Injector.instance().getPasswordEncryptor();

        user.setPassword(encryptor.encoder(user.getPassword()));

        IUser userByEmailId = userRepository.getUserByEmailId(user);

        if (userByEmailId == null) {
            success = userRepository.createUser(user);
            IUser userWithUserId = userRepository.getUserIdByEmailId(user);
            passwordHistoryService.addPasswordHistory(userWithUserId, user.getPassword());
        } else {
            throw new UserAlreadyExistsException("An account with " + user.getEmailId() + " already exists!!");
        }

        return success;
    }

    @Override
    public boolean checkCurrentUserIsAdmin(String emailId) {
        userRepository = Injector.instance().getUserRepository();
        IUser adminDetails = userRepository.getAdminDetails();
        boolean outcome = adminDetails.getEmailId().equalsIgnoreCase(emailId);
        return outcome;
    }

    private boolean checkForValues(IUser user) {

        boolean outcome = true;
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getEmailId().isEmpty()
                || user.getPassword().isEmpty() || user.getConfirmPassword().isEmpty()) {
            outcome = false;
        }
        return outcome;
    }

}
