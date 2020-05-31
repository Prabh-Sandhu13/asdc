package CSCI5308.GroupFormationTool.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordService;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Model.User;


@Controller
public class ForgotPasswordController {
	
	private IForgotPasswordService forgotPasswordService;
	
	@GetMapping("/forgotPassword")
	public String register(User user) {
		return "forgotPassword";
	}
	
	@PostMapping("/forgotPassword")
	public ModelAndView sendMail(User user) {
		ModelAndView modelAndView = null;
		
		try {
			
			forgotPasswordService = Injector.instance().getForgotPasswordService();
			forgotPasswordService.sendMail(user);
			modelAndView = new ModelAndView("forgotPassword");
			
		}
		catch (UserAlreadyExistsException uaex) {
			modelAndView = new ModelAndView("forgotPassword");
			modelAndView.addObject("userAlreadyExists", "An account with " + user.getEmailId() + " not found");
		}
		
		return modelAndView;
	}
		
	
}
