package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.User.UserDBMock;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.User;

import java.sql.Date;
import java.util.ArrayList;

public class QuestionDBMock implements IQuestionManagerRepository, IQuestionAdminRepository {

    private long id;

    private IUser instructor;

    private String title;

    private String text;

    private int type;

    private Date createdDate;

    private ArrayList<IChoice> choices;

    public QuestionDBMock() {
        id = 1;
        instructor = new UserDBMock().loadUserWithID(new User());
        title = "Sample";
        text = "Sample question";
        type = 1;
        createdDate = new Date(0);
        choices = new ArrayList<>();
        IChoice choice = new Choice();
        choice.setText("Amateur");
        choice.setValue(1);
        choices.add(choice);
    }

    public IQuestion loadQuestion(IQuestion question) {
        question.setCreatedDate(createdDate);
        question.setId(id);
        question.setInstructor(instructor);
        question.setText(text);
        question.setTitle(title);
        question.setType(type);
        question.setChoices(choices);
        return question;
    }

    @Override
    public boolean deleteQuestion(long questionId) {
        if (questionId == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long createQuestion(IQuestion question) {
        IUser user = new User();
        question.setCreatedDate(createdDate);
        question.setId(id);
        question.setInstructor(new UserDBMock().getUserByEmailId(user));
        question.setText(text);
        question.setTitle(title);
        question.setType(type);
        question.setChoices(choices);
        return question.getId();
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
        question.setChoices(choices);
        questionList.add(question);
        return questionList;
    }


    @Override
    public IQuestion getQuestionById(long questionId) {
        IUser user = new User();
        IQuestion question = new Question();
        question.setCreatedDate(createdDate);
        question.setId(questionId);
        question.setInstructor(new UserDBMock().getUserByEmailId(user));
        question.setText(text);
        question.setTitle(title);
        question.setType(type);
        question.setChoices(choices);
        return question;
    }

    @Override
    public ArrayList<IChoice> getOptionsForTheQuestion(long questionId) {
        IUser user = new User();
        IQuestion question = new Question();
        question.setCreatedDate(createdDate);
        question.setId(questionId);
        question.setInstructor(new UserDBMock().getUserByEmailId(user));
        question.setText(text);
        question.setTitle(title);
        question.setType(type);
        question.setChoices(choices);
        return question.getChoices();
    }


    @Override
    public ArrayList<IQuestion> getSortedQuestionListForInstructor(String emailId, String sortBy) {
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
        question.setChoices(choices);
        questionList.add(question);
        return questionList;
    }

}
