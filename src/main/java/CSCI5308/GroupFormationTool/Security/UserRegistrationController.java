package CSCI5308.GroupFormationTool.Security;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;

import CSCI5308.GroupFormationTool.Password.IPasswordAbstractFactory;
import CSCI5308.GroupFormationTool.Password.IPolicy;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
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

    @PostMapping("/register")
    public ModelAndView createUser(User user) {
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        IUser userInstance = userAbstractFactory.createUserInstance();
        ModelAndView modelAndView = null;
        String errorMessage = null;
        
            errorMessage = userInstance.createUser(user);
            if (null == errorMessage) {
                modelAndView = new ModelAndView("user/login");
            } 
            else {
            	if(errorMessage.equals(DomainConstants.signupInvalidDetails)) {
                modelAndView = new ModelAndView("user/signup");
                modelAndView.addObject("invalidDetails", errorMessage);
            	}
            	else if (errorMessage.equals(DomainConstants.userAlreadyExists
                        .replace("[[emailId]]", user.getEmailId()))) {
                    modelAndView = new ModelAndView("user/signup");
                    modelAndView.addObject("userAlreadyExists", errorMessage);
            	}
            	else{
                    modelAndView = new ModelAndView("user/signup");
                    modelAndView.addObject("passwordError", errorMessage);
            	}
            }
        return modelAndView;
    }

    @GetMapping("/register")
    public String register(User user, Model model) {
        IPasswordAbstractFactory passwordAbstractFactory = Injector.instance().getPasswordAbstractFactory();
        IPolicy policyInstance = passwordAbstractFactory.createPolicyInstance();
        ArrayList<IPolicy> policies = policyInstance.getPolicies();
        model.addAttribute("policies", policies);
        return "user/signup";
    }
}
