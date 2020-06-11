package CSCI5308.GroupFormationTool.DBMock;

import java.util.Calendar;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Model.User;

public class QuestionDBMock implements IQuestionRepository {

	private int id;

	private IUser instructor;

	private String title;

	private String text;

	private int type;

	private Calendar createdDate;

	public QuestionDBMock() {
		id = 1;
		instructor = new UserDBMock().loadUserWithID(new User());
		title = "Sample";
		text = "Sample question";
		type = 1;
		createdDate = Calendar.getInstance();
		createdDate.set(2020, 06, 11);
	}

	public IQuestion loadQuestion(IQuestion question) {

		question.setCreatedDate(createdDate);
		question.setId(id);
		question.setInstructor(instructor);
		question.setText(text);
		question.setTitle(title);
		question.setType(type);

		return question;
	}

}
