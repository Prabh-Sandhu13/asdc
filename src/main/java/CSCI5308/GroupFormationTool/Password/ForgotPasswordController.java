package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ForgotPasswordController {

    private IForgotPasswordManager forgotPasswordManager;
    private String receivedToken;
    private IPolicy policyInstance;

    @GetMapping("/forgotPassword")
    public String register(User user) {
        return "password/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public ModelAndView sendMail(User user) {
        ModelAndView modelAndView = null;
        String errorMessage = null;
            forgotPasswordManager = Injector.instance().getForgotPasswordManager();
            errorMessage = forgotPasswordManager.notifyUser(user);
            if(null == errorMessage) {
            modelAndView = new ModelAndView("password/MailSentSuccess");
            modelAndView.addObject("Success", DomainConstants.mailSentSuccess);
            }
            else {
            modelAndView = new ModelAndView("password/forgotPassword");
            modelAndView.addObject("userAlreadyExists", DomainConstants.userDoesNotExists
                    .replace("[[emailId]]", user.getEmailId()));
            }
            return modelAndView;
    }

    @GetMapping("/resetPassword")
    public String reset(User user, @RequestParam(name = "token", required = false) String token, Model model) {
        IPasswordAbstractFactory passwordAbstractFactory = Injector.instance().getPasswordAbstractFactory();
        receivedToken = token;
        policyInstance = passwordAbstractFactory.createPolicyInstance();
        ArrayList<IPolicy> policies = policyInstance.getPolicies();
        model.addAttribute("policies", policies);
        return "password/resetPassword";
    }

    @PostMapping("/resetPassword")
    public ModelAndView updatePassword(User user) {
        ModelAndView modelAndView = null;
        String errorMessage = null;
            forgotPasswordManager = Injector.instance().getForgotPasswordManager();
           errorMessage = forgotPasswordManager.updatePassword(user, receivedToken);
           if(null == errorMessage) {
            modelAndView = new ModelAndView("password/passwordResetSuccess");
            modelAndView.addObject("Success", DomainConstants.passwordResetMessage);
           } 
           else {
        	if(errorMessage.equals(DomainConstants.tokenExpiredMessage)) {
            modelAndView = new ModelAndView("password/resetPassword");
            modelAndView.addObject("Error", errorMessage);
        	}
        	else {
        		modelAndView = new ModelAndView("redirect:/resetPassword");
                modelAndView.addObject("token", receivedToken);
                modelAndView.addObject("passwordError", errorMessage);
        	}
           }
        return modelAndView;
    }
}
