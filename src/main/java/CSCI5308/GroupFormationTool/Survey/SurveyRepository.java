package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

public class SurveyRepository implements ISurveyRepository {
	private static final Logger Log = LoggerFactory.getLogger(SurveyRepository.class.getName());
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
    
}
