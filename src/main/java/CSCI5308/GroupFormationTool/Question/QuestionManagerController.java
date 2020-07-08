package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.FactoryProducer;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.User;
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

    private IQuestionAbstractFactory questionAbstractFactory = FactoryProducer.
            getFactory().createQuestionAbstractFactory();

    @GetMapping("/questionManager/createQuestion")
    public String createQuestion(Model model) {
        return "question/createQuestion";
    }

    @PostMapping("/questionManager/createQuestion")
    public String saveQuestion(Model model, @RequestParam("questionTitle") String title,
                               @RequestParam("questionText") String text, @RequestParam("questionType") String type,
                               @RequestParam("optionText") List<String> optionText,
                               @RequestParam("optionValue") List<String> optionValue) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long outcome;
        IQuestion question = questionAbstractFactory.createQuestionInstance();
        IUser instructor = new User();
        question.setText(text);
        question.setTitle(title);
        question.setType(Integer.parseInt(type));
        instructor.setEmailId(authentication.getPrincipal().toString());
        question.setInstructor(instructor);
        outcome = question.createQuestion(optionText, optionValue);

        if (outcome == DomainConstants.invalidData) {
            model.addAttribute("invalidData",
                    "One or more fields have invalid/empty data! Please enter and try again");
            return "question/createQuestion";
        } else {
            return "redirect:/questionManager/viewQuestion?questionId=" + outcome;
        }
    }

    @GetMapping("/questionManager/deleteQuestion")
    public String deleteQuestion(@RequestParam("questionId") long questionId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        IQuestion question = questionAbstractFactory.createQuestionInstance();
        boolean status = question.deleteQuestion(questionId);
        if (status) {
            model.addAttribute("successMessage", "The question " + questionId + " is successfully deleted!");
        } else {
            model.addAttribute("failureMessage", "The question can not not be deleted.");
        }
        String emailId = authentication.getPrincipal().toString();
        ArrayList<IQuestion> questionList = question.getQuestionListForInstructor(emailId);
        model.addAttribute("questionList", questionList);
        return "question/questionManager";
    }
}
