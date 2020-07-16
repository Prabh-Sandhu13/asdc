package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

public interface ITestSurveyAbstractFactory {

    ISurvey createSurveyInstance();

    SurveyRepository createSurveyRepositoryMock();

    ResponseRepository createResponseRepositoryMock();

    IResponse createResponseInstance();

    ISurveyFormula createSurveyFormulaInstance();

    SurveyFormulaRepository createSurveyFormulaRepositoryMock();

    ArrayList<SurveyFormula> createSurveyFormulaListInstance();

    SurveyFormulaList createSurveyFormulaListObj();

}
