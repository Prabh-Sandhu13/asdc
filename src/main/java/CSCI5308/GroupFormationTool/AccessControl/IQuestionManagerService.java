package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;
import java.util.List;

public interface IQuestionManagerService {

	ArrayList<IQuestion> getQuestionListForInstructor(String emailId);

	long createQuestion(IQuestion question, List<String> optionText, List<String> optionValue);

	IQuestion getQuestionById(long questionId);

}
