package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Database.DatabaseInjector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyRepository implements ISurveyRepository {

    private static final Logger log = LoggerFactory.getLogger(SurveyRepository.class.getName());

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
