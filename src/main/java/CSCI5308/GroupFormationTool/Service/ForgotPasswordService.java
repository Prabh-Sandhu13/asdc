package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordHistoryException;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenExpiredException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Injector;

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
            throw new UserAlreadyExistsException("An account with " + user.getEmailId() + " not found!");
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
            throw new PasswordException("The passwords do not match. Please try again!");
        }

        boolean updated = false;
        boolean isHistoryViolated = false;
        forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
        passwordHistoryService = Injector.instance().getPasswordHistoryService();
        encryptor = Injector.instance().getPasswordEncryptor();
        IUser userByEmailId = forgotPasswordRepository.getEmailByToken(user, token);

        if (userByEmailId == null) {
            throw new TokenExpiredException("Token expired");
        }

        isHistoryViolated = passwordHistoryService.isHistoryViolated(userByEmailId, user.getPassword());

        if (isHistoryViolated) {
            throw new PasswordHistoryException("Your new password cannot be same as previous "
                    + passwordHistoryService.getSettingValue("Password History") + " passwords!");
        }

        String encrypted_password = encryptor.encoder(user.getPassword());
        forgotPasswordRepository.updatePassword(userByEmailId, encrypted_password);
        passwordHistoryService.addPasswordHistory(userByEmailId, encrypted_password);
        updated = forgotPasswordRepository.deleteToken(user, token);

        return updated;
    }
}
