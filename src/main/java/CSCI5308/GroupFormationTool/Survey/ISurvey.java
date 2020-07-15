package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurvey {
	
	String getSurveyId();

    void setSurveyId(String surveyId);
    
    String getDescription();

    void setDescription(String description);

    String courseId();

    void setCourseId(String courseId);

    ArrayList<IQuestion> getQuestions();

    int createSurvey(String courseId);
    
    public boolean addQuestionToSurvey(long questionId, long surveyId);
    
    ArrayList<IQuestion> getQuestionsForSurvey(String courseId);
    
    public boolean deleteQuestionFromSurvey(long questionId, long surveyId);
}
