package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class SurveyTest {

    private ITestSurveyAbstractFactory surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
    private ISurveyRepository surveyRepository;
    private ISurvey survey;

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
}
