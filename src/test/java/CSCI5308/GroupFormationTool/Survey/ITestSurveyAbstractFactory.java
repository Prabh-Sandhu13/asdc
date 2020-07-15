package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ITestSurveyAbstractFactory {

    ISurvey createSurveyInstance();

    SurveyRepository createSurveyRepositoryMock();
    
    public IResponse createResponseInstance();
    
    public ResponseRepository createResponseRepositoryInstance();
    
    public ArrayList<IResponse> createResponseListInstance();
    
    public ArrayList<IQuestion> createSurveyQuestionListInstance();

}
