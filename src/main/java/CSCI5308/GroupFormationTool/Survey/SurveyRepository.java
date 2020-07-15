package CSCI5308.GroupFormationTool.Survey;


import CSCI5308.GroupFormationTool.Database.DatabaseInjector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Question.IChoice;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Question.IQuestionAdminRepository;
import CSCI5308.GroupFormationTool.Question.QuestionInjector;

public class SurveyRepository implements ISurveyRepository {

    private static final Logger log = LoggerFactory.getLogger(SurveyRepository.class.getName());
    private IQuestionAdminRepository questionAdminRepository;

    @Override
    public boolean checkIfSurveyCreated(String courseId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        boolean status = false;
        try {
            log.info("Calling stored procedure sp_checkIfSurveyCreated to fetch the groups for the course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_checkIfSurveyCreated(?,?)");
            storedProcedure.setInputStringParameter(1, courseId);
            storedProcedure.registerOutputParameterBoolean(2);
            storedProcedure.execute();
            status = storedProcedure.getParameter(2);

        } catch (SQLException exception) {
            log.error("Could not execute the Stored procedure sp_checkIfSurveyCreated" +
                    " because of an SQL Exception " + exception.getLocalizedMessage());
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }
	
	@Override
    public String getSurveyId(String courseId) {
		String surveyId = null;
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
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
            log.error("Could not execute the Stored procedure sp_getSurveyId" +
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
        IQuestionAbstractFactory questionAbstractFactory = QuestionInjector.instance().getQuestionAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        ISurveyAbstractFactory surveyAbstractFactory = SurveyInjector.instance().getSurveyAbstractFactory();
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
                            questionAdminRepository = QuestionInjector.instance().getQuestionAdminRepository();
                    		ArrayList<IChoice> choices= questionAdminRepository.getOptionsForTheQuestion(question.getId());
                    		question.setChoices(choices);
                    	}
                        surveyQuestions.add(question);
                    }
                }
            }
        } catch (SQLException ex) {
            log.error("Could not execute the Stored procedure sp_getSurveyQuestions" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } 
        
        finally {

            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }

        return surveyQuestions;
    }
	
	@Override
	public boolean isSurveyPublished(String surveyId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        boolean published = false;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_checkSurveyPublished(?)");
            storedProcedure.setInputStringParameter(1, surveyId);
            ResultSet results = storedProcedure.executeWithResults();
            if(results.next()) {
            	published = true;
            }
        } catch (SQLException ex) {
            log.error("Could not execute the Stored procedure sp_getSurveyQuestions" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } 
        
        finally {

            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return published;
    }
	
	@Override
	public boolean isSurveyCompleted(String surveyId, String userId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        boolean completed = false;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_checkSurveyCompleted(?,?)");
            storedProcedure.setInputStringParameter(1, surveyId);
            storedProcedure.setInputStringParameter(2, userId);
            ResultSet results = storedProcedure.executeWithResults();
            if(results.next()) {
            	completed = true;
            }
        } catch (SQLException ex) {
            log.error("Could not execute the Stored procedure sp_checkSurveyCompleted" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } 
        
        finally {

            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }

		return completed;
	}

    @Override
    public boolean checkIfSurveyPublished(String courseId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        boolean status = false;
        try {
            log.info("Calling stored procedure sp_checkIfSurveyPublished to fetch the groups for the course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_checkIfSurveyPublished(?,?)");
            storedProcedure.setInputStringParameter(1, courseId);
            storedProcedure.registerOutputParameterBoolean(2);
            storedProcedure.execute();
            status = storedProcedure.getParameter(2);
        } catch (SQLException exception) {
            log.error("Could not execute the Stored procedure sp_checkIfSurveyPublished" +
                    " because of an SQL Exception " + exception.getLocalizedMessage());
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

    @Override
    public boolean checkIfSurveyHasFormula(String courseId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        boolean status = false;
        try {
            log.info("Calling stored procedure sp_checkIfSurveyHasFormula to fetch the groups for the course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_checkIfSurveyHasFormula(?,?)");
            storedProcedure.setInputStringParameter(1, courseId);
            storedProcedure.registerOutputParameterBoolean(2);
            storedProcedure.execute();
            status = storedProcedure.getParameter(2);
        } catch (SQLException exception) {
            log.error("Could not execute the Stored procedure checkIfSurveyHasFormula" +
                    " because of an SQL Exception " + exception.getLocalizedMessage());
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

    @Override
    public boolean publishSurvey(String courseId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        boolean status = false;
        try {
            log.info("Calling stored procedure sp_publishSurvey to fetch the groups for the course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_publishSurvey(?,?)");
            storedProcedure.setInputStringParameter(1, courseId);
            storedProcedure.registerOutputParameterBoolean(2);
            storedProcedure.execute();
            status = storedProcedure.getParameter(2);
        } catch (SQLException exception) {
            log.error("Could not execute the Stored procedure sp_publishSurvey" +
                    " because of an SQL Exception " + exception.getLocalizedMessage());
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

    @Override
    public int getSurveyIdByCourseId(String courseId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        int status = -1;
        try {
            log.info("Calling stored procedure sp_getSurveyId to fetch the groups for the course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getSurveyId(?)");
            storedProcedure.setInputStringParameter(1, courseId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    status = (int) results.getLong("survey_id");
                }
            }
        } catch (SQLException exception) {
            log.error("Could not execute the Stored procedure sp_getSurveyId" +
                    " because of an SQL Exception " + exception.getLocalizedMessage());
            return -1;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

	
}
