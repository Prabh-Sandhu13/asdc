package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Database.DatabaseInjector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SurveyFormulaRepository implements ISurveyFormulaRepository {

    @Override
    public ArrayList<SurveyFormula> getSurveyDetailsToSetAlgo(String courseId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        ArrayList<SurveyFormula> surveyDetails = new ArrayList<SurveyFormula>();
        try {
//            log.info("Calling the stored procedure sp_getUserByEmailId to fetch user details for given emailId");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getSurveyDetailsToSetAlgo(?)");
            storedProcedure.setInputStringParameter(1, courseId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {

                while (results.next()) {
                    {
                        SurveyFormula surveyRow = new SurveyFormula();
                        surveyRow.setCourseId(results.getString("course_id"));
                        surveyRow.setSurveyId(results.getInt("survey_id"));
                        surveyRow.setQuestionId(results.getInt("question_id"));
                        surveyRow.setQuestionText(results.getString("question_text"));
                        surveyRow.setQuestionType(results.getInt("qtype_id"));
                        surveyDetails.add(surveyRow);
                    }
                }
            }

        } catch (SQLException ex) {
//            log.error("Could not execute the Stored procedure sp_getUserByEmailId" +
//                    "because of an SQL Exception " + ex.getLocalizedMessage());
            System.out.println(ex.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return surveyDetails;
    }

    @Override
    public String getAlgoIdBySurveyId(int surveyId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        String algoId = null;
        try {
//          log.info("Calling the stored procedure sp_getUserByEmailId to fetch user details for given emailId");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getAlgoIdBySurveyId(?)");
            storedProcedure.setInputIntParameter(1, surveyId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {

                while (results.next()) {
                    {
                        algoId = (results.getString("algo_id"));
                    }
                }
            }

        } catch (SQLException ex) {
//          log.error("Could not execute the Stored procedure sp_getUserByEmailId" +
//                  "because of an SQL Exception " + ex.getLocalizedMessage());
            System.out.println(ex.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }


        }
        return algoId;
    }

    @Override
    public void updateSurveyGroupSize(int groupSize, int surveyId, String courseId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_updateGroupSizeBySurveyId(?,?,?)");
            storedProcedure.setInputIntParameter(1, groupSize);
            storedProcedure.setInputIntParameter(2, surveyId);
            storedProcedure.setInputStringParameter(3, courseId);
            storedProcedure.execute();
        } catch (SQLException ex) {

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }

    }

    @Override
    public Boolean createAlgo(SurveyFormulaList surveyFormulaList, String generatedAlgoId, int surveyId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        for (SurveyFormula surveyFormula : surveyFormulaList.getSurveyRules()) {
            try {
                storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_createAlgo(?, ?, ?, ?, ?, ?, ?, ?)");
                storedProcedure.setInputStringParameter(1, generatedAlgoId);
                storedProcedure.setInputIntParameter(2, surveyId);
                storedProcedure.setInputIntParameter(3, surveyFormula.getQuestionId());
                if (surveyFormula.isCompareSimilarity()) {
                    storedProcedure.setInputIntParameter(4, 1);
                } else {
                    storedProcedure.setInputIntParameter(4, 0);
                }
                storedProcedure.setInputIntParameter(5, 0);
                if (surveyFormula.isFreeTextSimilarity()) {
                    storedProcedure.setInputIntParameter(6, 1);
                } else {
                    storedProcedure.setInputIntParameter(6, 0);
                }
                storedProcedure.setInputIntParameter(7, surveyFormula.getNumericLessThan());
                storedProcedure.setInputIntParameter(8, surveyFormula.getNumericGreaterThan());
                storedProcedure.execute();
            } catch (SQLException e) {
                System.out.println(e);
                // Logging needed
                return false;
            } finally {
                if (null != storedProcedure) {
                    storedProcedure.removeConnections();
                }
            }

        }
        return true;
    }

    @Override
    public Boolean deleteExistingAlgo(String algoId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        boolean status = true;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_deleteAlgo(?)");
            storedProcedure.setInputStringParameter(1, algoId);
            storedProcedure.execute();
            status = storedProcedure.getParameter(2);
        } catch (SQLException ex) {
            status = false;
        } finally {
            if (null != storedProcedure) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

    @Override
    public Boolean updateAlgoId(String generatedAlgoId, int surveyId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_updateAlgoIdBySurveyId(?,?)");
            storedProcedure.setInputStringParameter(1, generatedAlgoId);
            storedProcedure.setInputIntParameter(2, surveyId);
            storedProcedure.execute();
        } catch (SQLException ex) {
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return true;
    }
}
