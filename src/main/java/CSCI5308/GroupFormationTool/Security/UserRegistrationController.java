package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.Password.IPolicy;
import CSCI5308.GroupFormationTool.Password.IPolicyService;
import CSCI5308.GroupFormationTool.User.IUserService;
import CSCI5308.GroupFormationTool.ErrorHandling.PasswordException;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Controller
public class UserRegistrationController implements WebMvcConfigurer {

    private IUserService userService;
    private IPolicyService policyService;

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
                modelAndView.addObject("invalidDetails", DomainConstants.signupInvalidDetails);
            }

        } catch (UserAlreadyExistsException uaex) {
            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("userAlreadyExists", DomainConstants.userAlreadyExists
            		.replace("[[emailId]]",user.getEmailId()));
        } catch (PasswordException pex) {
            modelAndView = new ModelAndView("user/signup");
            modelAndView.addObject("passwordError", pex.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public String register(User user, Model model) {
        policyService = Injector.instance().getPolicyService();
        ArrayList<IPolicy> policies = policyService.getPolicies();
        model.addAttribute("policies", policies);
        return "user/signup";
    }
}
