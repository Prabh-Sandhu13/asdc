package CSCI5308.GroupFormationTool.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.IChoice;
import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.Choice;
import CSCI5308.GroupFormationTool.Model.Question;

public class QuestionManagerRepository implements IQuestionManagerRepository {

	@Override
	public ArrayList<IQuestion> getQuestionListForInstructor(String emailId) {

		StoredProcedure storedProcedure = null;
		ArrayList<IQuestion> questionList = new ArrayList<IQuestion>();
		try {
			storedProcedure = new StoredProcedure("sp_getQuestionsForInstructor(?)");
			storedProcedure.setInputStringParameter(1, emailId);

			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						IQuestion question = new Question();
						question.setId(results.getLong("question_id"));
						question.setText(results.getString("question_text"));
						question.setType(results.getInt("qtype_id"));
						question.setTitle(results.getString("title"));
						question.setCreatedDate(results.getDate("created_date"));

						questionList.add(question);
					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return questionList;

	}

	@Override
	public long createQuestion(IQuestion question) {

		StoredProcedure storedProcedure = null;
		long questionId = -1;

		try {
			storedProcedure = new StoredProcedure("sp_createQuestion(?,?,?,?,?)");

			storedProcedure.setInputStringParameter(1, question.getTitle());
			storedProcedure.setInputStringParameter(2, question.getText());
			storedProcedure.setInputStringParameter(3, question.getInstructor().getEmailId());
			storedProcedure.setInputIntParameter(4, question.getType());

			storedProcedure.registerOutputParameterLong(5);

			storedProcedure.execute();

			questionId = storedProcedure.getParameterLong(5);

		} catch (SQLException ex) {
			return -1;
		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}

		if (question.getChoices() != null) {

			for (IChoice choice : question.getChoices()) {

				if (!saveChoice(choice, questionId)) {
					return -1;
				}
			}

		}

		return questionId;
	}

	private boolean saveChoice(IChoice choice, long questionId) {
		StoredProcedure storedProcedure = null;

		try {
			storedProcedure = new StoredProcedure("sp_saveOptions(?,?,?)");

			storedProcedure.setInputStringParameter(1, choice.getText());
			storedProcedure.setInputIntParameter(2, choice.getValue());
			storedProcedure.setInputIntParameter(3, questionId);

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

	@Override
	public IQuestion getQuestionById(long questionId) {
		StoredProcedure storedProcedure = null;
		IQuestion question = new Question();
		try {
			storedProcedure = new StoredProcedure("sp_getQuestionById(?)");
			storedProcedure.setInputIntParameter(1, questionId);

			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						question.setId(results.getLong("question_id"));
						question.setText(results.getString("question_text"));
						question.setType(results.getInt("qtype_id"));
						question.setTitle(results.getString("title"));
						question.setCreatedDate(results.getDate("created_date"));

					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}

		return question;
	}

	@Override
	public ArrayList<IChoice> getOptionsForTheQuestion(long questionId) {

		StoredProcedure storedProcedure = null;
		ArrayList<IChoice> choiceList = new ArrayList<>();
		try {
			storedProcedure = new StoredProcedure("sp_getOptionsForQuestion(?)");
			storedProcedure.setInputIntParameter(1, questionId);

			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{

						IChoice choice = new Choice();
						choice.setText(results.getString("options_text"));
						choice.setValue(results.getInt("options_value"));
						choiceList.add(choice);

					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}

		return choiceList;
	}

	public boolean deleteQuestion(long questionId) {
		
		StoredProcedure proc = null;
		boolean status = true;
		try {
			proc = new StoredProcedure("sp_deleteAQuestion(?,?)");
			proc.setInputIntParameter(1, questionId);
			proc.registerOutputParameterBoolean(2);
			proc.execute();

			status = proc.getParameter(2);

		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			if (null != proc) {
				proc.removeConnections();
			}
		}
		return status;
	}
	
	public ArrayList<IQuestion> getSortedQuestionListForInstructor(String emailId, String sortBy) {

		StoredProcedure storedProcedure = null;
		ArrayList<IQuestion> questionList = new ArrayList<IQuestion>();
		try {
			storedProcedure = new StoredProcedure("sp_getSortedQuestionsForInstructor(?,?)");
			storedProcedure.setInputStringParameter(1, emailId);
			storedProcedure.setInputStringParameter(2, sortBy);

			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {
				while (results.next()) {
					{
						IQuestion question = new Question();
						question.setId(results.getLong("question_id"));
						question.setText(results.getString("question_text"));
						question.setType(results.getInt("qtype_id"));
						question.setTitle(results.getString("title"));
						question.setCreatedDate(results.getDate("created_date"));

						questionList.add(question);
					}
				}
			}

		} catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		
		return questionList;

	}
}
