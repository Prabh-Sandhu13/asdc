package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordService;
import CSCI5308.GroupFormationTool.AccessControl.IMailService;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordHistoryService;
import CSCI5308.GroupFormationTool.AccessControl.ITokenGenerator;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordHistoryException;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenAlreadyExistException;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenExpiredException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Model.User;

public class ForgotPasswordService implements IForgotPasswordService{

	private IUserRepository userRepository;
	private IForgotPasswordRepository forgotPasswordRepository;
	private ITokenGenerator tokenGenerator;
	private IPasswordHistoryService passwordHistoryService;
	private IMailService mailService;
	private IPasswordEncryptor encryptor;
	
	@Override
	public boolean notifyUser(IUser user) {
		
		userRepository = Injector.instance().getUserRepository();
		forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
		tokenGenerator = Injector.instance().getTokenGenerator();
		mailService = Injector.instance().getMailService();
		encryptor = Injector.instance().getPasswordEncryptor();
		
		
		boolean success = false;
		boolean tokenPresent = false;
		IUser userByEmailId = forgotPasswordRepository.getUserId(user);

		if (userByEmailId != null) {
			String token = forgotPasswordRepository.getToken(userByEmailId);
			if (token.equals("")) {
				//If no token exists
				token = tokenGenerator.generator();
				tokenPresent = forgotPasswordRepository.addToken(userByEmailId, token);			
			}
			
			else {
				token = tokenGenerator.generator();
				tokenPresent = forgotPasswordRepository.updateToken(userByEmailId, token);
			}

			mailService.sendForgotPasswordMail(userByEmailId, token);
			
		} 
		else {
			throw new UserAlreadyExistsException("An account with " + user.getEmailId() + " not found!");
		}
	
		return success;
	}

	@Override
	public boolean updatePassword(IUser user, String token) {
		
		if (!(user.getPassword().equals(user.getConfirmPassword()))) {
			throw new PasswordException("The passwords do not match");
		}
		
		
		boolean updated = false;
		boolean isHistoryViolated = false;
		forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
		passwordHistoryService = Injector.instance().getPasswordHistoryService();
		encryptor = Injector.instance().getPasswordEncryptor();
		IUser userByEmailId = forgotPasswordRepository.getEmailByToken(user, token);
		
		
		if(null == userByEmailId) {
			throw new TokenExpiredException("Token expired");
		}
		
		isHistoryViolated = passwordHistoryService.isHistoryViolated(userByEmailId,user.getPassword());
		
		if(isHistoryViolated) {
			throw new PasswordHistoryException("Your new password cannot be same as previous " + passwordHistoryService.getSettingValue("Password History") + " passwords!");
		}
		
		String encrypted_password = encryptor.encoder(user.getPassword());
		forgotPasswordRepository.updatePassword(userByEmailId, encrypted_password);	
		passwordHistoryService.addPasswordHistory(userByEmailId, encrypted_password);
		
		updated = forgotPasswordRepository.deleteToken(user, token);
		
		return updated;
	}
}
