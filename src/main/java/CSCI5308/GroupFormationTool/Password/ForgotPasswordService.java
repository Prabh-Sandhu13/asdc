package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordHistoryException;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenExpiredException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Mail.IMailService;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.User.IUser;

public class ForgotPasswordService implements IForgotPasswordService {

    private IForgotPasswordRepository forgotPasswordRepository;
    private ITokenGenerator tokenGenerator;
    private IPasswordHistoryService passwordHistoryService;
    private IMailService mailService;
    private IPasswordEncryptor encryptor;
    private IPolicyService policyService;

    @Override
    public boolean notifyUser(IUser user) {

        forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
        tokenGenerator = Injector.instance().getTokenGenerator();
        mailService = Injector.instance().getMailService();
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
            mailService.sendForgotPasswordMail(userByEmailId, token);
        } else {
            throw new UserAlreadyExistsException(DomainConstants.userDoesNotExists
            		.replace("[[emailId]]", user.getEmailId()));
        }
        return true;
    }

    @Override
    public boolean updatePassword(IUser user, String token) {

        policyService = Injector.instance().getPolicyService();
        String password = user.getPassword();
        String passwordSecurityError = policyService.passwordSPolicyCheck(password);

        if (passwordSecurityError != null) {
            throw new PasswordException(passwordSecurityError);
        }

        if (!(user.getPassword().equals(user.getConfirmPassword()))) {
            throw new PasswordException(DomainConstants.passwordsDontMatch);
        }

        boolean updated = false;
        boolean isHistoryViolated = false;
        forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
        passwordHistoryService = Injector.instance().getPasswordHistoryService();
        encryptor = Injector.instance().getPasswordEncryptor();
        IUser userByEmailId = forgotPasswordRepository.getEmailByToken(user, token);

        if (userByEmailId == null) {
            throw new TokenExpiredException(DomainConstants.tokenExpiredMessage);
        }

        isHistoryViolated = passwordHistoryService.isHistoryViolated(userByEmailId, user.getPassword());

        if (isHistoryViolated) {
            throw new PasswordHistoryException(DomainConstants.passwordHistoryMessage
            		.replace("[[history]]", passwordHistoryService.getSettingValue(DomainConstants.passwordHistory)));
        }

        String encrypted_password = encryptor.encoder(user.getPassword());
        forgotPasswordRepository.updatePassword(userByEmailId, encrypted_password);
        passwordHistoryService.addPasswordHistory(userByEmailId, encrypted_password);
        updated = forgotPasswordRepository.deleteToken(user, token);

        return updated;
    }
}
