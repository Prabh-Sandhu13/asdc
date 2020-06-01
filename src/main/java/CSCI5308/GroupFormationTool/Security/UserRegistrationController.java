package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.ErrorHandling.InvalidDetailsException;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class UserRegistrationController implements WebMvcConfigurer {

	private IUserService userService;

	@PostMapping("/register")
	public ModelAndView createUser(User user) {

		ModelAndView modelAndView = null;
		boolean success = false;
		try {
			userService = Injector.instance().getUserService();
			success = userService.createUser(user);

			if (success) {
				modelAndView = new ModelAndView("login");
			} else {
				modelAndView = new ModelAndView("signup");
				modelAndView.addObject("invalidDetails", "One or more mandatory fields are not entered.");
			}

		} catch (UserAlreadyExistsException uaex) {
			modelAndView = new ModelAndView("signup");
			modelAndView.addObject("userAlreadyExists", "An account with " + user.getEmailId() + " already exists.");
		} catch (PasswordException pex) {
			modelAndView = new ModelAndView("signup");
			modelAndView.addObject("passwordError", "The passwords do not match");
		}
		return modelAndView;
	}

	@GetMapping("/register")
	public String register(User user) {
		return "signup";
	}

}
