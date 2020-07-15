package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Course.CourseInjector;
import CSCI5308.GroupFormationTool.Course.IUserCoursesRepository;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.ITestQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Question.TestQuestionInjector;
import CSCI5308.GroupFormationTool.User.ITestUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.TestUserInjector;
import CSCI5308.GroupFormationTool.User.UserInjector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;

public class SurveyTest {

    private ITestSurveyAbstractFactory surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
    private ISurveyRepository surveyRepository;
    private ISurvey survey;
    private ITestQuestionAbstractFactory questionAbstractFactoryTest = TestQuestionInjector.instance().
            getQuestionAbstractFactory();
    private ArrayList<IQuestion> questionList = null;

    @BeforeEach
    void init() {
        surveyRepository = surveyAbstractFactory.createSurveyRepositoryMock();
        SurveyInjector.instance().setSurveyRepository(surveyRepository);
        survey = surveyAbstractFactory.createSurveyInstance();
    }

    @Test
    void checkIfSurveyCreatedTest() {
        String courseId = "1";
        when(surveyRepository.checkIfSurveyCreated(courseId)).thenReturn(true);
        assertTrue(survey.checkIfSurveyCreated(courseId));
    }

    @Test
    void checkIfSurveyPublishedTest() {
        String courseId = "1";
        when(surveyRepository.checkIfSurveyPublished(courseId)).thenReturn(true);
        assertTrue(survey.checkIfSurveyPublished(courseId));
    }

    @Test
    void checkIfSurveyHasFormulaTest() {
        String courseId = "1";
        when(surveyRepository.checkIfSurveyHasFormula(courseId)).thenReturn(true);
        assertTrue(survey.checkIfSurveyHasFormula(courseId));
    }

    @Test
    void checkIfGroupsCanBeFormedForSurveyTest() {
        String courseId = "1";
        when(surveyRepository.checkIfSurveyCreated(courseId)).thenReturn(true);
        when(surveyRepository.checkIfSurveyPublished(courseId)).thenReturn(true);
        when(surveyRepository.checkIfSurveyHasFormula(courseId)).thenReturn(true);
        assertTrue(survey.checkIfGroupsCanBeFormedForSurvey(courseId)
                == DomainConstants.surveyGroupFormationPossible);
        when(surveyRepository.checkIfSurveyCreated(courseId)).thenReturn(false);
        assertTrue(survey.checkIfGroupsCanBeFormedForSurvey(courseId)
                == DomainConstants.surveyNotCreated);
        when(surveyRepository.checkIfSurveyCreated(courseId)).thenReturn(true);
        when(surveyRepository.checkIfSurveyPublished(courseId)).thenReturn(false);
        assertTrue(survey.checkIfGroupsCanBeFormedForSurvey(courseId)
                == DomainConstants.surveyNotPublished);
        when(surveyRepository.checkIfSurveyCreated(courseId)).thenReturn(true);
        when(surveyRepository.checkIfSurveyPublished(courseId)).thenReturn(true);
        when(surveyRepository.checkIfSurveyHasFormula(courseId)).thenReturn(false);
        assertTrue(survey.checkIfGroupsCanBeFormedForSurvey(courseId)
                == DomainConstants.surveyNotHavingAlgorithm);
    }

    @Test
    void publishSurvey() {
        String courseId = "1";
        when(surveyRepository.publishSurvey(courseId)).thenReturn(true);
        assertTrue(survey.publishSurvey(courseId));
    }

    @Test
    void getSurveyIdByCourseId() {
        String courseId = "1";
        when(surveyRepository.getSurveyIdByCourseId(courseId)).thenReturn(2);
        assertTrue(survey.getSurveyIdByCourseId(courseId) == 2);
    }
    
    @Test
    void getSurveyIdTest() {
    	String courseId = "CSCI 6509";
    	when(surveyRepository.getSurveyId(courseId)).thenReturn("2");
    	assertTrue(survey.getSurveyId(courseId).equals("2"));
    }

    @Test
    void getSurveyQuestionsTest() {
    	
        questionList = questionAbstractFactoryTest.createQuestionListInstance();
        IQuestion question = questionAbstractFactoryTest.createQuestionInstance();
        question = questionAbstractFactoryTest.createQuestionInstance();
        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setId(2);
        question.setText("Sample text");
        question.setTitle("Sample title");
        question.setType(DomainConstants.numeric);
        question.setChoices(null);
        questionList.add(question);
    	String surveyId = "CSCI 6509";
    	when(surveyRepository.getSurveyQuestions(surveyId)).thenReturn(questionList);
    	assertTrue(survey.getSurveyQuestions(surveyId).size() == 1);
    	assertFalse(survey.getSurveyQuestions(surveyId) == null);
    }

    @Test
    void isSurveyPublishedTest() {
    	String surveyId = "2";
    	when(surveyRepository.isSurveyPublished(surveyId)).thenReturn(true);
    	when(surveyRepository.getSurveyId("CSCI 6509")).thenReturn("2");
    	assertTrue(survey.isSurveyPublished("CSCI 6509"));
    }

    @Test
    void isSurveyCompletedTest() {
    	when(surveyRepository.isSurveyCompleted("2", "75")).thenReturn(false);
    	when(surveyRepository.getSurveyId("CSCI 6509")).thenReturn("2");
    	assertFalse(survey.isSurveyCompleted("CSCI 6509", "2"));
    }

    @Test
    void createSurveyTest() {
        String courseId = "1";
        when(surveyRepository.createSurvey(courseId)).thenReturn(1);
        assertTrue(survey.createSurvey(courseId) == 1);
        
        survey.setCourseId(courseId);
        survey.setDescription("This is a test survey!");
        survey.setSurveyId("1");
        
        assertFalse(survey.getDescription() == null);
        assertTrue(survey.getSurveyId().equals("1"));
        assertTrue(survey.getDescription().equals("This is a test survey!"));
        assertFalse(survey.getSurveyId().length() < 0);
    }

    @Test
    void addQuestionToSurveyTest() {
    }

    @Test
    void getQuestionsForSurveyTest() {
    }

    @Test
    void deleteQuestionFromSurveyTest() {
    }

    @Test
    void getQuestionListForSurveyTest() {
    }

}
