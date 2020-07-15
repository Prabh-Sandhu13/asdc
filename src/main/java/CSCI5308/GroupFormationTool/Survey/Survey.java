package CSCI5308.GroupFormationTool.Survey;

import java.sql.Date;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Course.ICourse;
import CSCI5308.GroupFormationTool.Question.IQuestion;

public class Survey implements ISurvey {
    private long id;

    private ICourse course;

	private String surveyDescription;

    private Date surveyStartDate;

    private Date surveyEndDate;

    private String published;
    
    private ISurveyRepository surveyRepository;
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ICourse getCourse() {
		return course;
	}
	public void setCourse(ICourse course) {
		this.course = course;
	}
	public String getSurveyDescription() {
		return surveyDescription;
	}
	public void setSurveyDescription(String surveyDescription) {
		this.surveyDescription = surveyDescription;
	}
	public Date getSurveyStartDate() {
		return surveyStartDate;
	}
	public void setSurveyStartDate(Date surveyStartDate) {
		this.surveyStartDate = surveyStartDate;
	}
	public Date getSurveyEndDate() {
		return surveyEndDate;
	}
	public void setSurveyEndDate(Date surveyEndDate) {
		this.surveyEndDate = surveyEndDate;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public ISurveyRepository getSurveyRepository() {
		return surveyRepository;
	}
	public void setSurveyRepository(ISurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}
	
	
	public String getSurveyId(String courseId) {
		surveyRepository = Injector.instance().getSurveyRepository();
		return surveyRepository.getSurveyId(courseId);
	}
	public ArrayList<IQuestion> getSurveyQuestions(String surveyId) {
		surveyRepository = Injector.instance().getSurveyRepository();
		return surveyRepository.getSurveyQuestions(surveyId);
	}
	@Override
	public boolean isSurveyPublished(String courseId) {
		surveyRepository = Injector.instance().getSurveyRepository();
		String surveyId = surveyRepository.getSurveyId(courseId);
		return surveyRepository.isSurveyPublished(surveyId);
	}
	@Override
	public boolean isSurveyCompleted(String courseId, String userId) {
		surveyRepository = Injector.instance().getSurveyRepository();
		String surveyId = surveyRepository.getSurveyId(courseId);
		return surveyRepository.isSurveyCompleted(surveyId, userId);
	}
	
}
