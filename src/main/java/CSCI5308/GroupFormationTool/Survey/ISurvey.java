package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.IQuestion;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISurvey {

    boolean checkIfSurveyCreated(String courseId);

    boolean checkIfSurveyPublished(String courseId);

    boolean checkIfSurveyHasFormula(String courseId);

    int checkIfGroupsCanBeFormedForSurvey(String courseId);

    boolean publishSurvey(String courseId);

    int getSurveyIdByCourseId(String courseId);

    public String getSurveyId(String courseId);

    public ArrayList<IQuestion> getSurveyQuestions(String surveyId);

    public boolean isSurveyPublished(String courseId);

    public boolean isSurveyCompleted(String courseId, String userId);

    String getSurveyId();

    void setSurveyId(String surveyId);

    String getDescription();

    void setDescription(String description);

    String courseId();

    void setCourseId(String courseId);

    int createSurvey(String courseId);

    public boolean addQuestionToSurvey(long questionId, long surveyId);

    ArrayList<IQuestion> getQuestionsForSurvey(String courseId);

    public boolean deleteQuestionFromSurvey(long questionId, long surveyId);

    ArrayList<IQuestion> getQuestionListForSurvey(String emailId, int surveyId, String courseId, String questionTitle);

    ArrayList<Long> getUsersWhoTookSurvey(String courseId);

    HashMap<Long, HashMap<Long, IResponse>> getAllStudentResponses(String courseId);

    HashMap<Long, IResponse> getUserResponses(Long userId, Long surveyId, String courseId);
}
