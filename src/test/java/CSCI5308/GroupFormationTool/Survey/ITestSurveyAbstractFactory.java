package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.IQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ITestSurveyAbstractFactory {

    ISurvey createSurveyInstance();

    SurveyRepository createSurveyRepositoryMock();

    public IResponse createResponseInstance();

    public ResponseRepository createResponseRepositoryMock();

    public ArrayList<IResponse> createResponseListInstance();

    public ArrayList<IQuestion> createSurveyQuestionListInstance();

    ResponseDBMock createResponseDBMockInstance();

    List<String> createOptionListInstance();

    Map<String, String> createMapResponse();
}
