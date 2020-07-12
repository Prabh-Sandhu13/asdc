package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

public interface ISurveyAbstractFactory {

    ISurvey createSurveyInstance();

    IResponse createResponseInstance();

    ArrayList<IResponse> createResponseListInstance();

    SurveyRepository createSurveyRepositoryInstance();

    ResponseRepository createResponseRepositoryInstance();

}
