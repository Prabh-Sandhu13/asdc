package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IQuestionManagerRepository {

	ArrayList<IQuestion> getQuestionListForInstructor(String emailId);

	long createQuestion(IQuestion question);

	IQuestion getQuestionById(long questionId);
	
	ArrayList<IChoice> getOptionsForTheQuestion(long questionId);
	
	boolean deleteQuestion(long questionId);
	
	ArrayList<IQuestion> getSortedQuestionListForInstructor(String emailId, String sortBy);

}
