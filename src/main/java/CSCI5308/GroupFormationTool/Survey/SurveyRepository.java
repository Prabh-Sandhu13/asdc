package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;

import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Question.IChoice;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Question.IQuestionAdminRepository;

public class SurveyRepository implements ISurveyRepository {
	private static final Logger Log = LoggerFactory.getLogger(SurveyRepository.class.getName());
	private IQuestionAdminRepository questionAdminRepository;
	
	@Override
    public String getSurveyId(String courseId) {
		String surveyId = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getSurveyId(?)");
            storedProcedure.setInputStringParameter(1, courseId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        surveyId = (results.getString("survey_id"));
                    }
                }
            }
        } catch (SQLException ex) {
            Log.error("Could not execute the Stored procedure sp_getSurveyId" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
 
        return surveyId;
	}
	
	@Override
	public ArrayList<IQuestion> getSurveyQuestions(String surveyId) {
        IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ISurveyAbstractFactory surveyAbstractFactory = Injector.instance().getSurveyAbstractFactory();
        ArrayList<IQuestion> surveyQuestions = surveyAbstractFactory.createSurveyQuestionListInstance();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getSurveyQuestions(?)");
            storedProcedure.setInputStringParameter(1, surveyId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                    	IQuestion question = questionAbstractFactory.createQuestionInstance();
                    	question.setId(Long.parseLong(results.getString("question_id")));
                    	question.setTitle(results.getString("title"));
                    	question.setText(results.getString("question_text"));
                    	question.setType(Integer.parseInt(results.getString("qtype_id")));
                    	if(question.getType() == DomainConstants.MCQOne||
                    			question.getType() == DomainConstants.MCQMultiple) {
                            questionAdminRepository = Injector.instance().getQuestionAdminRepository();
                    		ArrayList<IChoice> choices= questionAdminRepository.getOptionsForTheQuestion(question.getId());
                    		question.setChoices(choices);
                    	}
                        surveyQuestions.add(question);
                    }
                }
            }
        } catch (SQLException ex) {
            Log.error("Could not execute the Stored procedure sp_getSurveyQuestions" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } 
        
        finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
		return surveyQuestions;
	}
	
}
