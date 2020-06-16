package CSCI5308.GroupFormationTool.Security;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordService;
import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IPolicyService;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordHistoryException;
import CSCI5308.GroupFormationTool.ErrorHandling.TokenExpiredException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Model.User;


@Controller
public class ForgotPasswordController {
	
	private IForgotPasswordService forgotPasswordService;
	private String receivedToken;
	private IPolicyService policyService;
	
	@GetMapping("/forgotPassword")
	public String register(User user) {
		return "forgotPassword";
	}
	
	@PostMapping("/forgotPassword")
	public ModelAndView sendMail(User user) {
		ModelAndView modelAndView = null;
		
		try {
			forgotPasswordService = Injector.instance().getForgotPasswordService();
			forgotPasswordService.notifyUser(user);
			modelAndView = new ModelAndView("MailSentSuccess");
			modelAndView.addObject("Success", "An email with reset link has been succesfully sent!");
			
		}
		catch (UserAlreadyExistsException uaex) {
			modelAndView = new ModelAndView("forgotPassword");
			modelAndView.addObject("userAlreadyExists", "An account with " + user.getEmailId() + " not found");
		}
		catch(Exception e) {
			
		}
		
		return modelAndView;
	}
		
	@GetMapping("/resetPassword")
	public String reset(User user,@RequestParam(name="token", required = false) String token, Model model) {
		receivedToken = token;
		policyService = Injector.instance().getPolicyService();
		ArrayList<IPolicy> policies = policyService.getPolicies();
		model.addAttribute("policies",policies);

		return "resetPassword";
	}
	
	@PostMapping("/resetPassword")
	public ModelAndView updatePassword(User user) {
		ModelAndView modelAndView = null;
		try {
			forgotPasswordService = Injector.instance().getForgotPasswordService();
			forgotPasswordService.updatePassword(user,receivedToken);
			modelAndView = new ModelAndView("passwordResetSuccess");
			modelAndView.addObject("Success", "Your password has been reset!");		

		}
		catch(TokenExpiredException tee) {
			modelAndView = new ModelAndView("resetPassword");
			modelAndView.addObject("Error", "The renew password link has expired, please renew it again");
		}
		catch(PasswordException pex) {
			modelAndView = new ModelAndView("redirect:/resetPassword");
			modelAndView.addObject("token", receivedToken);
			modelAndView.addObject("passwordError", pex.getMessage());
		}
		catch(PasswordHistoryException phe) {
			modelAndView = new ModelAndView("redirect:/resetPassword");
			modelAndView.addObject("token", receivedToken);
			modelAndView.addObject("passwordError", phe.getMessage());
		}
		catch(Exception e) {
			modelAndView = new ModelAndView("resetPassword");
			modelAndView.addObject("Error", "Something went wrong, please try again");
		}
		
		
		return modelAndView;
	}
}
