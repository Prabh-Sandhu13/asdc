package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurvey {
	public String getSurveyId(String courseId);
	public ArrayList<IQuestion> getSurveyQuestions(String surveyId);
}
