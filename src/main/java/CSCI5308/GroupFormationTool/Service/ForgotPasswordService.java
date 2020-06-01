package CSCI5308.GroupFormationTool.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordService;
import CSCI5308.GroupFormationTool.AccessControl.ITokenGenerator;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenAlreadyExistException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Model.User;

public class ForgotPasswordService implements IForgotPasswordService{

	private IUserRepository userRepository;
	private IForgotPasswordRepository forgotPasswordRepository;
	private ITokenGenerator tokenGenerator;
	private IMailService mailService;
	private SimpleMailMessage msg;
	private JavaMailSender jms;
	
	@Override
	public boolean sendMail(User user) {
		
		userRepository = Injector.instance().getUserRepository();
		forgotPasswordRepository = Injector.instance().getForgotPasswordRepository();
		tokenGenerator = Injector.instance().getTokenGenerator();
		mailService = Injector.instance().getMailService();
		msg = Injector.instance().getMailMessage();
		jms = mailService.getJavaMailSender();
		
		/*TestMail
		 * 
		 * 
		 */
		
		try {
		mailService.sendEmail(jms, msg);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		boolean success = false;
		boolean tokenPresent = false;
		//System.out.println(user.getEmailId());
		User userByEmailId = forgotPasswordRepository.getUserId(user);

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
			msg.setTo(userByEmailId.getEmailId());
			msg.setSubject("Complete Password Reset!");
			msg.setFrom("noreply.group22@gmail.com");
			msg.setText("To reset your password, follow this link: "
						+token);
			mailService.sendEmail(jms, msg);
			
		} else {
			throw new UserAlreadyExistsException("An account with " + user.getEmailId() + " not found!");
		}
	
		return success;
	}

	@Override
	public boolean updatePassword() {
		// TODO Auto-generated method stub
		return false;
	}

}
