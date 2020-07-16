package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ITestSurveyAbstractFactory {

    ISurvey createSurveyInstance();

    SurveyRepository createSurveyRepositoryMock();

    ResponseRepository createResponseRepositoryMock();

    IResponse createResponseInstance();

    ResponseDBMock createResponseDBMockInstance();

    List<String> createOptionListInstance();
    
    ArrayList<IResponse> createResponseListInstance();

    Map<String,String> createMapResponse();
}
