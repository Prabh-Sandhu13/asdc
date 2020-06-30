package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IQuestionAdminService {

	ArrayList<IQuestion> getQuestionListForInstructor(String emailId);
	
	IQuestion getQuestionById(long questionId);

    ArrayList<IQuestion> getSortedQuestionListForInstructor(String emailId, String sortBy);
}
