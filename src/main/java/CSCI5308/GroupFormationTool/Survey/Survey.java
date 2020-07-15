package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Question.IQuestion;

public class Survey implements ISurvey {
	
	private String surveyId;

    private String description;

    private String courseId;

    private ISurveyRepository surveyRepository;

    public Survey() {
        this.surveyId = null;
        this.description = null;
        this.courseId = null;
    }

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description =  description;		
	}

	public String courseId() {		
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;		
	}

	public ArrayList<IQuestion> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	public int createSurvey(String courseId) {
		surveyRepository = Injector.instance().getSurveyRepository();
        return surveyRepository.createSurvey(courseId);
	}
	
	public boolean addQuestionToSurvey(long questionId, long surveyId) {
		surveyRepository = Injector.instance().getSurveyRepository();
        return surveyRepository.addQuestionToSurvey(questionId, surveyId);
    }
	
	public ArrayList<IQuestion> getQuestionsForSurvey(String courseId){
		surveyRepository = Injector.instance().getSurveyRepository();
        return surveyRepository.getQuestionsForSurvey(courseId);
	}
	
	public boolean deleteQuestionFromSurvey(long questionId, long surveyId) {
		surveyRepository = Injector.instance().getSurveyRepository();
        return surveyRepository.deleteQuestionFromSurvey(questionId, surveyId);
    }
}
