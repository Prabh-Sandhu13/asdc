package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerService;

public class QuestionManagerService implements IQuestionManagerService{

	IQuestionManagerRepository questionManagerRepository;
	
	@Override
	public ArrayList<IQuestion> getQuestionListForInstructor(String emailId) {
		
		questionManagerRepository = Injector.instance().getQuestionManagerRepository();
		
		return questionManagerRepository.getQuestionListForInstructor(emailId);
		
	}

}
