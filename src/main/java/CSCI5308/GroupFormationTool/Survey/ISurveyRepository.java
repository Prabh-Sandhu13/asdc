package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurveyRepository {
	
	int createSurvey(String courseId);

	boolean addQuestionToSurvey(long questionId, long surveyId);

	ArrayList<IQuestion> getQuestionsForSurvey(String courseId);
	
	boolean deleteQuestionFromSurvey(long questionId, long surveyId);
	
}
