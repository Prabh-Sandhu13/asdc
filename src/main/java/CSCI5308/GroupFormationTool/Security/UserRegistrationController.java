package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.AccessControl.IPolicy;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Controller
public class UserRegistrationController implements WebMvcConfigurer {

    private IUser userInstance;
    private IPolicy policy;

    @PostMapping("/register")
    public ModelAndView createUser(User user) {
        ModelAndView modelAndView = null;
        boolean success = false;
        try {
        	userInstance =  Injector.instance().getUser();
            success = userInstance.createUser(user);

            if (success) {
                modelAndView = new ModelAndView("login");
            } else {
                modelAndView = new ModelAndView("signup");
                modelAndView.addObject("invalidDetails", DomainConstants.signupInvalidDetails);
            }

        } catch (UserAlreadyExistsException uaex) {
            modelAndView = new ModelAndView("signup");
            modelAndView.addObject("userAlreadyExists", DomainConstants.userAlreadyExists
            		.replace("[[emailId]]",user.getEmailId()));
        } catch (PasswordException pex) {
            modelAndView = new ModelAndView("signup");
            modelAndView.addObject("passwordError", pex.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public String register(User user, Model model) {
        policy = Injector.instance().getPolicy();
        ArrayList<IPolicy> policies = policy.getPolicies();
        model.addAttribute("policies", policies);
        return "signup";
    }
}
