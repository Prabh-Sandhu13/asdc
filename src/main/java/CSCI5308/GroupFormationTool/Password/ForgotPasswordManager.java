package CSCI5308.GroupFormationTool.Password;

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
    public String notifyUser(IUser user) {
        forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
        tokenGenerator = Injector.instance().getTokenGenerator();
        mailManager = Injector.instance().getMailManager();
        encryptor = Injector.instance().getPasswordEncryptor();
        String errorMessage = null;
        IUser userByEmailId = forgotPasswordRepository.getUserId(user);
        if (userByEmailId != null) {
            String token = forgotPasswordRepository.getToken(userByEmailId);
            if (token.equals("")) {
                token = tokenGenerator.generator();
                forgotPasswordRepository.addToken(userByEmailId, token);
            } else {
                token = tokenGenerator.generator();
                forgotPasswordRepository.updateToken(userByEmailId, token);
            }
            mailManager.sendForgotPasswordMail(userByEmailId, token);
        } else {
            errorMessage = DomainConstants.userDoesNotExists
                    .replace("[[emailId]]", user.getEmailId());
        }
        return errorMessage;
    }

    @Override
    public String updatePassword(IUser user, String token) {
        IPasswordAbstractFactory passwordAbstractFactory = Injector.instance().getPasswordAbstractFactory();
        policyInstance = passwordAbstractFactory.createPolicyInstance();
        String password = user.getPassword();
        String passwordSecurityError = policyInstance.passwordSPolicyCheck(password);
        String errorMessage = null;
        if (passwordSecurityError != null) {
            errorMessage = passwordSecurityError;
            return errorMessage;
        }
        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
        	errorMessage = DomainConstants.passwordsDontMatch;
            return errorMessage;
        }
        boolean isHistoryViolated = false;
        forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
        passwordHistoryManager = Injector.instance().getPasswordHistoryManager();
        encryptor = Injector.instance().getPasswordEncryptor();
        IUser userByEmailId = forgotPasswordRepository.getEmailByToken(user, token);
        if (userByEmailId == null) {
            errorMessage = DomainConstants.tokenExpiredMessage;
            return errorMessage;
        }
        isHistoryViolated = passwordHistoryManager.isHistoryViolated(userByEmailId, user.getPassword());
        if (isHistoryViolated) {
            errorMessage = DomainConstants.passwordHistoryMessage
                    .replace("[[history]]", passwordHistoryManager.
                            getSettingValue(DomainConstants.passwordHistory));
            return errorMessage;
        }
        String encrypted_password = encryptor.encoder(user.getPassword());
        forgotPasswordRepository.updatePassword(userByEmailId, encrypted_password);
        passwordHistoryManager.addPasswordHistory(userByEmailId, encrypted_password);
        return null;
    }
}
