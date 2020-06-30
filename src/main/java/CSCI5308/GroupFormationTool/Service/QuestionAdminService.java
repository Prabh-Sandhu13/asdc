package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IChoice;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionAdminRepository;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionAdminService;

public class QuestionAdminService implements IQuestionAdminService{
	
	IQuestionAdminRepository questionAdminRepository;

    @Override
    public ArrayList<IQuestion> getQuestionListForInstructor(String emailId) {
        questionAdminRepository = Injector.instance().getQuestionAdminRepository();
        return questionAdminRepository.getQuestionListForInstructor(emailId);

    }
    
    @Override
    public ArrayList<IQuestion> getSortedQuestionListForInstructor(String emailId, String sortBy) {
        questionAdminRepository = Injector.instance().getQuestionAdminRepository();
        return questionAdminRepository.getSortedQuestionListForInstructor(emailId, sortBy);
    }
    
    @Override
    public IQuestion getQuestionById(long questionId) {
        questionAdminRepository = Injector.instance().getQuestionAdminRepository();
        IQuestion question = questionAdminRepository.getQuestionById(questionId);
        ArrayList<IChoice> choiceList = null;

        if (question.getType() == DomainConstants.MCQMultiple || question.getType() == DomainConstants.MCQOne) {
            choiceList = questionAdminRepository.getOptionsForTheQuestion(questionId);
        }
        question.setChoices(choiceList);
        return question;
    }
}
