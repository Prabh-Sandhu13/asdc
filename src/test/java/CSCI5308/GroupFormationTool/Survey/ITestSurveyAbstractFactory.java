package CSCI5308.GroupFormationTool.Survey;

public interface ITestSurveyAbstractFactory {

    ISurvey createSurveyInstance();

    SurveyRepository createSurveyRepositoryMock();

    ResponseRepository createResponseRepositoryMock();

    IResponse createResponseInstance();

	ISurveyFormula createSurveyFormulaInstance();

	SurveyFormulaRepository createSurveyFormulaRepositoryMock();

}
