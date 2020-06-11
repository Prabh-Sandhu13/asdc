package CSCI5308.GroupFormationTool.AccessControl;

import java.util.ArrayList;

public interface IQuestionManagerService {

	ArrayList<IQuestion> getQuestionListForInstructor(String emailId);

}
