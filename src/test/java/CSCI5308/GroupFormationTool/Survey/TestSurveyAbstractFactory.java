package CSCI5308.GroupFormationTool.Survey;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public class TestSurveyAbstractFactory implements ITestSurveyAbstractFactory {
    @Override
    public ISurvey createSurveyInstance() {
        return new Survey();
    }
    
    @Override
    public IResponse createResponseInstance() {
        return new Response();
    }

    @Override
    public SurveyRepository createSurveyRepositoryMock() {
        return mock(SurveyRepository.class);
    }
    

    @Override
    public ResponseRepository createResponseRepositoryMock() {
        return mock(ResponseRepository.class);
    }
    
    @Override
    public ArrayList<IResponse> createResponseListInstance() {
        return new ArrayList<IResponse>();
    }
    
    @Override
    public ArrayList<IQuestion> createSurveyQuestionListInstance() {
        return new ArrayList<IQuestion>();
    }
}
