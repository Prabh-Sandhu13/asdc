package CSCI5308.GroupFormationTool.DBMock;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import CSCI5308.GroupFormationTool.AccessControl.IQuestion;
import CSCI5308.GroupFormationTool.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Model.Question;
import CSCI5308.GroupFormationTool.Model.User;

public class QuestionDBMock implements IQuestionManagerRepository {

	private long id;

	private IUser instructor;

	private String title;

	private String text;

	private int type;

	private Date createdDate;

	public QuestionDBMock() {
		id = 1;
		instructor = new UserDBMock().loadUserWithID(new User());
		title = "Sample";
		text = "Sample question";
		type = 1;
		createdDate = new Date(0);
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

	@Override
	public ArrayList<IQuestion> getQuestionListForInstructor(String emailId) {

		ArrayList<IQuestion> questionList = new ArrayList<>();
		IQuestion question = new Question();
		IUser user = new User();
		user.setEmailId(emailId);

		question.setCreatedDate(createdDate);
		question.setId(id);
		question.setInstructor(new UserDBMock().getUserByEmailId(user));
		question.setText(text);
		question.setTitle(title);
		question.setType(type);

		questionList.add(question);

		return questionList;

	}

}
