package CSCI5308.GroupFormationTool.Controller;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionAdminService;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerService;

@Controller
public class QuestionAdminController {

	IQuestionAdminService questionAdminService;
	
    @GetMapping("/questionManager/questionManager")
    public String questionList(Model model) {
        questionAdminService = Injector.instance().getQuestionAdminService();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailId = authentication.getPrincipal().toString();
        ArrayList<IQuestion> questionList = questionAdminService.getQuestionListForInstructor(emailId);
        model.addAttribute("questionList", questionList);
        return "questionManager/questionManager";
    }
    
    @GetMapping("/questionManager/viewQuestion")
    public String viewQuestion(@RequestParam("questionId") long questionId, Model model) {
        questionAdminService = Injector.instance().getQuestionAdminService();
        IQuestion question = questionAdminService.getQuestionById(questionId);
        model.addAttribute("question", question);
        return "questionManager/viewQuestion";
    }
    
	
    @GetMapping("/questionManager/sortQuestion")
    public String sortQuestion(@RequestParam("sortby") String sortBy, Model model) {
        questionAdminService = Injector.instance().getQuestionAdminService();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailId = authentication.getPrincipal().toString();
        ArrayList<IQuestion> questionList = questionAdminService.getSortedQuestionListForInstructor(emailId, sortBy);
        model.addAttribute("questionList", questionList);
        return "questionManager/questionManager";
    }
        
}
