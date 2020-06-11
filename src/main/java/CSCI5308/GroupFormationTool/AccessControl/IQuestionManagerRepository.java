package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IQuestionManagerRepository {

	ArrayList<IQuestion> getQuestionListForInstructor(String emailId);

}
