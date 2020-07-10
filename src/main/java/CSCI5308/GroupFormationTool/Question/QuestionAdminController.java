package CSCI5308.GroupFormationTool.Question;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Common.Injector;

import java.util.ArrayList;

@Controller
public class QuestionAdminController {

    @GetMapping("/questionManager/questionManager")
    public String questionList(Model model) {
        IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IQuestion question = questionAbstractFactory.createQuestionInstance();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailId = authentication.getPrincipal().toString();
        ArrayList<IQuestion> questionList = question.getQuestionListForInstructor(emailId);
        model.addAttribute("questionList", questionList);
        return "question/questionManager";
    }

    @GetMapping("/questionManager/viewQuestion")
    public String viewQuestion(@RequestParam("questionId") long questionId, Model model) {
        IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IQuestion question = questionAbstractFactory.createQuestionInstance();
        question = question.getQuestionById(questionId);
        model.addAttribute("question", question);
        return "question/viewQuestion";
    }

    @GetMapping("/questionManager/sortQuestion")
    public String sortQuestion(@RequestParam("sortby") String sortField, Model model) {
        IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IQuestion question = questionAbstractFactory.createQuestionInstance();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailId = authentication.getPrincipal().toString();
        ArrayList<IQuestion> questionList = question.getSortedQuestionListForInstructor(emailId, sortField);
        model.addAttribute("questionList", questionList);
        return "question/questionManager";
    }

}