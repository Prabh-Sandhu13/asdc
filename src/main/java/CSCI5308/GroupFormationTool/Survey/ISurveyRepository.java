package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.IQuestion;

import java.util.ArrayList;

public interface ISurveyRepository {

    boolean checkIfSurveyCreated(String courseId);

    boolean checkIfSurveyPublished(String courseId);

    boolean checkIfSurveyHasFormula(String courseId);

    boolean publishSurvey(String courseId);

    int getSurveyIdByCourseId(String courseId);

    String getSurveyId(String courseId);

    ArrayList<IQuestion> getSurveyQuestions(String surveyId);

    boolean isSurveyPublished(String surveyId);

    boolean isSurveyCompleted(String surveyId, String userId);

    int createSurvey(String courseId);

    boolean addQuestionToSurvey(long questionId, long surveyId);

    ArrayList<IQuestion> getQuestionsForSurvey(String courseId);

    boolean deleteQuestionFromSurvey(long questionId, long surveyId);

    ArrayList<IQuestion> getSurveyQuestionListForInstructor(String emailId, int surveyId, String questionTitle);

}
