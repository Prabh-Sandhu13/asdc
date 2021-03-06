package CSCI5308.GroupFormationTool.User;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Password.IPasswordAbstractFactory;
import CSCI5308.GroupFormationTool.Password.IPasswordHistoryManager;
import CSCI5308.GroupFormationTool.Password.IPolicy;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryptor;

public class User implements IUser {

    private IUserRepository userRepository;

    private IPasswordEncryptor encryptor;

    private IPolicy policyInstance;

    private IPasswordHistoryManager passwordHistoryManager;

    private long id;

    private String firstName;

    private String lastName;

    private String bannerId;

    private String emailId;

    private String password;

    private String confirmPassword;

    public User() {
        id = -1;

        firstName = null;

        lastName = null;

        bannerId = null;

        emailId = null;

        password = null;

        confirmPassword = null;
    }

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

    @Override
    public String createUser(IUser user) {
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        IPasswordAbstractFactory passwordAbstractFactory = Injector.instance().getPasswordAbstractFactory();
        String errorMessage = null;
        if (!checkForValues(user)) {
            errorMessage = DomainConstants.signupInvalidDetails;
        }
        policyInstance = passwordAbstractFactory.createPolicyInstance();
        String password = user.getPassword();
        String passwordSecurityError = policyInstance.passwordSPolicyCheck(password);
        if (passwordSecurityError != null) {
            errorMessage = passwordSecurityError;
            return errorMessage;
        }
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            errorMessage = DomainConstants.passwordsDontMatch;
            return errorMessage;
        }
        userRepository = Injector.instance().getUserRepository();
        passwordHistoryManager = Injector.instance().getPasswordHistoryManager();
        encryptor = Injector.instance().getPasswordEncryptor();
        user.setPassword(encryptor.encoder(user.getPassword()));
        IUser userByEmailId = userRepository.getUserByEmailId(user);
        if (userByEmailId == null) {
            userRepository.createUser(user);
            IUser userWithUserId = userRepository.getUserIdByEmailId(user);
            passwordHistoryManager.addPasswordHistory(userWithUserId, user.getPassword());
        } else {
            errorMessage = DomainConstants.userAlreadyExists
                    .replace("[[emailId]]", user.getEmailId());
        }
        return errorMessage;
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
