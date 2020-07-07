package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordHistoryException;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenExpiredException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Mail.IMailManager;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.User.IUser;

public class ForgotPasswordManager implements IForgotPasswordManager {

    private IForgotPasswordRepository forgotPasswordRepository;
    private ITokenGenerator tokenGenerator;
    private IPasswordHistoryManager passwordHistoryManager;
    private IMailManager mailManager;
    private IPasswordEncryptor encryptor;
    private IPolicy policyInstance;

    @Override
    public boolean notifyUser(IUser user) {

        forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
        tokenGenerator = Injector.instance().getTokenGenerator();
        mailManager = Injector.instance().getMailManager();
        encryptor = Injector.instance().getPasswordEncryptor();

        IUser userByEmailId = forgotPasswordRepository.getUserId(user);

        if (userByEmailId != null) {
            String token = forgotPasswordRepository.getToken(userByEmailId);
            if (token.equals("")) {
                // If no token exists
                token = tokenGenerator.generator();
                forgotPasswordRepository.addToken(userByEmailId, token);
            } else {
                token = tokenGenerator.generator();
                forgotPasswordRepository.updateToken(userByEmailId, token);
            }
            mailManager.sendForgotPasswordMail(userByEmailId, token);
        } else {
            throw new UserAlreadyExistsException(DomainConstants.userDoesNotExists
            		.replace("[[emailId]]", user.getEmailId()));
        }
        return true;
    }

    @Override
    public boolean updatePassword(IUser user, String token) {

    	policyInstance = Injector.instance().getPolicy();
        String password = user.getPassword();
        String passwordSecurityError = policyInstance.passwordSPolicyCheck(password);

        if (passwordSecurityError != null) {
            throw new PasswordException(passwordSecurityError);
        }

        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            throw new PasswordException(DomainConstants.passwordsDontMatch);
        }

        boolean updated = false;
        boolean isHistoryViolated = false;
        forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
        passwordHistoryManager = Injector.instance().getPasswordHistoryManager();
        encryptor = Injector.instance().getPasswordEncryptor();
        IUser userByEmailId = forgotPasswordRepository.getEmailByToken(user, token);

        if (userByEmailId == null) {
            throw new TokenExpiredException(DomainConstants.tokenExpiredMessage);
        }

        isHistoryViolated = passwordHistoryManager.isHistoryViolated(userByEmailId, user.getPassword());

        if (isHistoryViolated) {
            throw new PasswordHistoryException(DomainConstants.passwordHistoryMessage
            		.replace("[[history]]", passwordHistoryManager.getSettingValue(DomainConstants.passwordHistory)));
        }

        String encrypted_password = encryptor.encoder(user.getPassword());
        forgotPasswordRepository.updatePassword(userByEmailId, encrypted_password);
        passwordHistoryManager.addPasswordHistory(userByEmailId, encrypted_password);
        updated = forgotPasswordRepository.deleteToken(user, token);

        return updated;
    }
}
