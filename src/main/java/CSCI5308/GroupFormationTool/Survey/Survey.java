package CSCI5308.GroupFormationTool.Survey;


import CSCI5308.GroupFormationTool.Common.DomainConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Date;
import java.util.ArrayList;
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
    
    private static final Logger log = LoggerFactory.getLogger(Survey.class.getName());

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

    @Override
    public boolean checkIfSurveyCreated(String courseId) {
        log.info("Checking if the survey is created for the course from the database");
        ISurveyRepository surveyRepository = SurveyInjector.instance().getSurveyRepository();
        return surveyRepository.checkIfSurveyCreated(courseId);
    }

    @Override
    public boolean checkIfSurveyPublished(String courseId) {
        log.info("Checking if the survey is published for the course from the database");
        ISurveyRepository surveyRepository = SurveyInjector.instance().getSurveyRepository();
        return surveyRepository.checkIfSurveyPublished(courseId);
    }

    @Override
    public boolean checkIfSurveyHasFormula(String courseId) {
        log.info("Checking if the survey has an algorithm for group formation for the course from the database");
        ISurveyRepository surveyRepository = SurveyInjector.instance().getSurveyRepository();
        return surveyRepository.checkIfSurveyHasFormula(courseId);
    }

    @Override
    public int checkIfGroupsCanBeFormedForSurvey(String courseId) {
        if (!checkIfSurveyCreated(courseId)) {
            return DomainConstants.surveyNotCreated;
        }
        if (!checkIfSurveyPublished(courseId)) {
            return DomainConstants.surveyNotPublished;
        }
        if (!checkIfSurveyHasFormula(courseId)) {
            return DomainConstants.surveyNotHavingAlgorithm;
        }
        return DomainConstants.surveyGroupFormationPossible;
    }

    @Override
    public boolean publishSurvey(String courseId) {
        log.info("Publishing the survey");
        ISurveyRepository surveyRepository = SurveyInjector.instance().getSurveyRepository();
        return surveyRepository.publishSurvey(courseId);
    }

    @Override
    public int getSurveyIdByCourseId(String courseId) {
        log.info("Getting survey id based on course id");
        ISurveyRepository surveyRepository = SurveyInjector.instance().getSurveyRepository();
        return surveyRepository.getSurveyIdByCourseId(courseId);
    }
	
	public String getSurveyId(String courseId) {
		surveyRepository = SurveyInjector.instance().getSurveyRepository();
		return surveyRepository.getSurveyId(courseId);
	}
	public ArrayList<IQuestion> getSurveyQuestions(String surveyId) {
		surveyRepository = SurveyInjector.instance().getSurveyRepository();
		return surveyRepository.getSurveyQuestions(surveyId);
	}
	@Override
	public boolean isSurveyPublished(String courseId) {
		surveyRepository = SurveyInjector.instance().getSurveyRepository();
		String surveyId = surveyRepository.getSurveyId(courseId);
		return surveyRepository.isSurveyPublished(surveyId);
	}
	@Override
	public boolean isSurveyCompleted(String courseId, String userId) {
		surveyRepository = SurveyInjector.instance().getSurveyRepository();
		String surveyId = surveyRepository.getSurveyId(courseId);
		return surveyRepository.isSurveyCompleted(surveyId, userId);
	}
	
}
