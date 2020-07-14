package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Survey implements ISurvey {

    private static final Logger log = LoggerFactory.getLogger(Survey.class.getName());
    private ISurveyAbstractFactory surveyAbstractFactory = SurveyInjector.instance().getSurveyAbstractFactory();

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
}
