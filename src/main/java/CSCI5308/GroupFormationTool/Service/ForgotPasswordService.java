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
	private IMailService mailService;
	private SimpleMailMessage msg;
	private JavaMailSender jms;
	private IPasswordEncryptor encryptor;
	
	@Override
	public boolean sendMail(IUser user) {
		
		userRepository = Injector.instance().getUserRepository();
		forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
		tokenGenerator = Injector.instance().getTokenGenerator();
		mailService = Injector.instance().getMailService();
		msg = Injector.instance().getMailMessage();
		encryptor = Injector.instance().getPasswordEncryptor();
		jms = mailService.getJavaMailSender();
		
		boolean success = false;
		boolean tokenPresent = false;
		//System.out.println(user.getEmailId());
		IUser userByEmailId = forgotPasswordRepository.getUserId(user);

		if (userByEmailId != null) {
			String token = forgotPasswordRepository.getToken(userByEmailId);
			//System.out.println(token);
			if (token.equals("")) {
				//If no token exists
				token = tokenGenerator.generator();
				tokenPresent = forgotPasswordRepository.addToken(userByEmailId, token);
				
			}
			else {
				//If token exists
				token = tokenGenerator.generator();
				tokenPresent = forgotPasswordRepository.updateToken(userByEmailId, token);
			}
			
			//Send Mail Here
			// create the email
			String URL = "http://formgroups22.herokuapp.com/"+"resetPassword"+"?token="+token;
			msg.setTo(userByEmailId.getEmailId());
			msg.setSubject("Complete Password Reset!");
			msg.setFrom("noreply.group22@gmail.com");
			msg.setText("To reset your password, follow this link: "+ URL);
			mailService.sendEmail(jms, msg);
			
		} else {
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
		forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
		encryptor = Injector.instance().getPasswordEncryptor();
		IUser userByEmailId = forgotPasswordRepository.getEmailByToken(user, token);
		
		if(null == userByEmailId) {
			throw new TokenExpiredException("Token expired");
		}
		
		if(isHistoryViolated(userByEmailId,user.getPassword())) {
			throw new PasswordHistoryException("Your new password cannot be same as previous " + forgotPasswordRepository.getSettingValue("Password History") + " passwords!");
		}
		
		forgotPasswordRepository.updatePassword(userByEmailId,encryptor.encoder(user.getPassword()));	
		updated = forgotPasswordRepository.deleteToken(user, token);
		
		
		return updated;
	}

	@Override
	public boolean isHistoryViolated(IUser user, String enteredPassword) {
		boolean violation = false;
		forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
		encryptor = Injector.instance().getPasswordEncryptor();
		String settingValue = forgotPasswordRepository.getSettingValue("Password History");
		if(null == settingValue) {
			return false;
		}
		else {
			
			String encrypted_password = encryptor.encoder(enteredPassword);
			ArrayList<String> nPasswords = forgotPasswordRepository.getNPasswords(user, settingValue);
			System.out.println("Typed: "+(encrypted_password));
			for (int listIndex = 0; listIndex < nPasswords.size() ; listIndex ++) {
			//	System.out.println(nPasswords.get(listIndex));
				if(encryptor.passwordMatch(enteredPassword, nPasswords.get(listIndex))) {
					violation = true;
					break;
				}
			}
		}
		// System.out.println("Violated? " + violation);
		return violation;
	}


}
