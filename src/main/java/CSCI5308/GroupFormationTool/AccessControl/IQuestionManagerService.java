package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;
import java.util.List;

public interface IQuestionManagerService {

	ArrayList<IQuestion> getQuestionListForInstructor(String emailId);

	long createQuestion(String title, String text, int type, List<String> optionText, List<String> optionValue,
			String emailId);

	IQuestion getQuestionById(long questionId);

}
