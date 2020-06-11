package CSCI5308.GroupFormationTool.Controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerService;
import java.util.ArrayList;

@Controller
public class QuestionManagerController {

	IQuestionManagerService questionManagerService;

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

}
