package CSCI5308.GroupFormationTool.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
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

}
