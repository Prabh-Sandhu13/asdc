package CSCI5308.GroupFormationTool.Survey;

public interface ITestSurveyAbstractFactory {

    ISurvey createSurveyInstance();

    SurveyRepository createSurveyRepositoryMock();

}
