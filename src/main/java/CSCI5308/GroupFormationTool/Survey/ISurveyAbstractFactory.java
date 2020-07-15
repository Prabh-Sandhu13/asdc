package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.HashMap;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurveyAbstractFactory {

    ISurvey createSurveyInstance();

    IResponse createResponseInstance();

    ArrayList<IResponse> createResponseListInstance();

    SurveyRepository createSurveyRepositoryInstance();

    ResponseRepository createResponseRepositoryInstance();
    
    ArrayList<IQuestion> createSurveyQuestionListInstance();

    HashMap<Long, HashMap<Long, IResponse>> createAllStudentResponsesInstance();

    HashMap<Long, IResponse> createQuestionResponseInstance();

}
