package CSCI5308.GroupFormationTool.Controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionManagerController {

	IQuestionManagerService questionManagerService;
	private final static long invalidData = 0;
	private final static long sqlError = -1;

	@GetMapping("/questionManager/questionManager")
	public String questionList(Model model) {

		questionManagerService = Injector.instance().getQuestionManagerService();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String emailId = authentication.getPrincipal().toString();

			ArrayList<IQuestion> questionList = questionManagerService.getQuestionListForInstructor(emailId);

			model.addAttribute("questionList", questionList);
		}

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

		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			outcome = questionManagerService.createQuestion(title, text, Integer.parseInt(type), optionText,
					optionValue, authentication.getPrincipal().toString());

			if (outcome == sqlError) {

				model.addAttribute("errorMsg", "There was a problem in adding your question. Please try again!");
				return "questionManager/createQuestion";

			} else if (outcome == invalidData) {
				model.addAttribute("invalidData",
						"You have not entered data in one or more fields! Please enter and try again");
				return "questionManager/createQuestion";
			} else {
				return "redirect:/questionManager/viewQuestion?questionId=" + outcome;

			}
		} else {
			return "questionManager/createQuestion";
		}

	}

	@GetMapping("/questionManager/viewQuestion")
	public String viewQuestion(@RequestParam("questionId") long questionId, Model model) {

		questionManagerService = Injector.instance().getQuestionManagerService();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			IQuestion question = questionManagerService.getQuestionById(questionId);

			model.addAttribute("question", question);
			return "questionManager/viewQuestion";
		}
		return "questionManager/viewQuestion";

	}

}
