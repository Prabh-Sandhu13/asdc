package CSCI5308.GroupFormationTool.Controller;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerService;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.Question;
import CSCI5308.GroupFormationTool.Model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionManagerController {

    IQuestionManagerService questionManagerService;

    @GetMapping("/questionManager/questionManager")
    public String questionList(Model model) {
        questionManagerService = Injector.instance().getQuestionManagerService();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailId = authentication.getPrincipal().toString();
        ArrayList<IQuestion> questionList = questionManagerService.getQuestionListForInstructor(emailId);
        model.addAttribute("questionList", questionList);
        return "questionManager/questionManager";
    }

    @GetMapping("/questionManager/createQuestion")
    public String createQuestion(Model model) {
        return "questionManager/createQuestion";
    }

    @PostMapping("/questionManager/createQuestion")
    public String saveQuestion(Model model, @RequestParam("questionTitle") String title,
                               @RequestParam("questionText") String text, @RequestParam("questionType") String type,
                               @RequestParam("optionText") List<String> optionText,
                               @RequestParam("optionValue") List<String> optionValue) {

        questionManagerService = Injector.instance().getQuestionManagerService();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long outcome;
        IQuestion question = new Question();
        IUser instructor = new User();
        question.setText(text);
        question.setTitle(title);
        question.setType(Integer.parseInt(type));
        instructor.setEmailId(authentication.getPrincipal().toString());
        question.setInstructor(instructor);
        outcome = questionManagerService.createQuestion(question, optionText, optionValue);

        if (outcome == DomainConstants.sqlError) {
            model.addAttribute("errorMsg", "There was a problem in adding your question. Please try again!");
            return "questionManager/createQuestion";
        } else if (outcome == DomainConstants.invalidData) {
            model.addAttribute("invalidData",
                    "One or more fields have invalid/empty data! Please enter and try again");
            return "questionManager/createQuestion";
        } else {
            return "redirect:/questionManager/viewQuestion?questionId=" + outcome;
        }
    }

    @GetMapping("/questionManager/viewQuestion")
    public String viewQuestion(@RequestParam("questionId") long questionId, Model model) {
        questionManagerService = Injector.instance().getQuestionManagerService();
        IQuestion question = questionManagerService.getQuestionById(questionId);
        model.addAttribute("question", question);
        return "questionManager/viewQuestion";
    }

    @GetMapping("/questionManager/deleteQuestion")
    public String deleteQuestion(@RequestParam("questionId") long questionId, Model model) {
        questionManagerService = Injector.instance().getQuestionManagerService();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean status = questionManagerService.deleteQuestion(questionId);

        if (status) {
            model.addAttribute("successMessage", "The question " + questionId + " is successfully deleted!");
        } else {
            model.addAttribute("failureMessage", "The question can not not be deleted.");
        }
        String emailId = authentication.getPrincipal().toString();
        ArrayList<IQuestion> questionList = questionManagerService.getQuestionListForInstructor(emailId);
        model.addAttribute("questionList", questionList);
        return "questionManager/questionManager";
    }

    @GetMapping("/questionManager/sortQuestion")
    public String sortQuestion(@RequestParam("sortby") String sortBy, Model model) {
        questionManagerService = Injector.instance().getQuestionManagerService();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailId = authentication.getPrincipal().toString();
        ArrayList<IQuestion> questionList = questionManagerService.getSortedQuestionListForInstructor(emailId, sortBy);
        model.addAttribute("questionList", questionList);
        return "questionManager/questionManager";
    }
}
